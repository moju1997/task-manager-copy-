package com.jazasoft.taskmanager.restcontroller;

import com.jazasoft.taskmanager.model.Task;
import com.jazasoft.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("api/tasks")
public class TaskRestController {

    @Autowired
    TaskService taskService;

    @GetMapping
    public Flux<Task> getTask() {
        return taskService.getAll();
    }

    @GetMapping("/{taskId}")
    public Mono<ResponseEntity<Task>> getTaskById(@PathVariable(value = "taskId") Long id) {
        return taskService.getOne(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<Task>> saveTask(@RequestBody Task task) {
        return taskService.save(task).map(t -> new ResponseEntity<>(t, HttpStatus.CREATED));
    }


    @PutMapping("/{taskId}")
    public Mono<ResponseEntity<Task>> updateTask(@PathVariable(value = "taskId") Long id, @Valid @RequestBody Task tasks) {
        return taskService.getOne(id).flatMap(existingTask -> {
            existingTask.setId(tasks.getId());
            existingTask.setName(tasks.getName());
            return taskService.save(tasks);
        })
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{taskId}")
    public Mono<ResponseEntity<Object>> deleteTask(@PathVariable(value = "taskId") Long id) {
        return taskService.getOne(id).flatMap(existingTask -> taskService.delete(existingTask)
                .then(Mono.just(ResponseEntity.noContent().build())))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
