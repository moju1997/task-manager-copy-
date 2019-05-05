package com.jazasoft.taskmanager.service;

import com.jazasoft.taskmanager.model.Task;
import com.jazasoft.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Flux<Task> getAll() {
        return taskRepository.findAll();
    }

    public Mono<Task> save(Task task) {
        return taskRepository.save(task);
    }

    public Mono<Task> getOne(Long id){
        return taskRepository.findById(id);
    }

    public Mono<Void> delete(Task task){
          return taskRepository.delete(task);
    }
}
