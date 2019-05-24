package com.jazasoft.taskmanager.restcontroller;

import com.jazasoft.taskmanager.model.Label;
import com.jazasoft.taskmanager.model.Task;
import com.jazasoft.taskmanager.repository.LabelRepository;
import com.jazasoft.taskmanager.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("api/labels")
public class LabelRestController {

    @Autowired
    LabelService labelService;

    @Autowired
    LabelRepository labelRepository;

    @GetMapping
    public Flux<Label> getTask() {
        return labelService.getAll();
    }

    @GetMapping("/{labelId}")
    public Mono<ResponseEntity<Label>> getLabelById(@PathVariable(value = "labelId") String id) {
        return labelService.getOne(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/priority/{priority}")
    public Flux<Label> getByPriority(@PathVariable (value = "priority")String name){
        return labelRepository.findByName(name);
    }


    @PostMapping
    public Mono<ResponseEntity<Label>> saveTask(@RequestBody Label label) {
        return labelService.save(label).map(t -> new ResponseEntity<>(t, HttpStatus.CREATED));
    }

    @PutMapping("/{labelId}")
    public Mono<ResponseEntity<Label>> updateLabel(@PathVariable(value = "labelId") String id, @Valid @RequestBody Label label) {
        return labelService.getOne(id).flatMap(existingLabels -> {
            existingLabels.setId(label.getId());
            existingLabels.setName(label.getName());
            return labelService.save(label);
        })
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{labelId}")
    public Mono<ResponseEntity<Object>> deleteLabel(@PathVariable(value = "labelId") String id) {
        return labelService.getOne(id).flatMap(existingLabels -> labelService.delete(existingLabels)
                .then(Mono.just(ResponseEntity.noContent().build())))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
