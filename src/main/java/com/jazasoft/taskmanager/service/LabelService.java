package com.jazasoft.taskmanager.service;

import com.jazasoft.taskmanager.model.Label;
import com.jazasoft.taskmanager.model.Task;
import com.jazasoft.taskmanager.repository.LabelRepository;
import com.jazasoft.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class LabelService {

    private LabelRepository labelRepository;
    public LabelService(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    public Flux<Label> getAll() {
        return labelRepository.findAll();
    }

    public Mono<Label> save(Label label) {
        return labelRepository.save(label);
    }

    public Mono<Label> getOne(String id){
        return labelRepository.findById(id);
    }

    public Mono<Void> delete(Label label){
        return labelRepository.delete(label);
    }
}
