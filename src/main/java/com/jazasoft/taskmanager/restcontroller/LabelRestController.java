package com.jazasoft.taskmanager.restcontroller;

import com.jazasoft.taskmanager.model.Label;
import com.jazasoft.taskmanager.model.Task;
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

    @GetMapping
    public Flux<Label> getTask() {
        return labelService.getAll();
    }

    @GetMapping("/{labelId}")
    public Mono<ResponseEntity<Label>> getLabelById(@PathVariable(value = "labelId") Long id) {
        return labelService.getOne(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


    @PostMapping
    public Mono<ResponseEntity<Label>> saveTask(@RequestBody Label label) {
        return labelService.save(label).map(t -> new ResponseEntity<>(t, HttpStatus.CREATED));
    }

    @PutMapping("/{labelId}")
    public Mono<ResponseEntity<Label>> updateLabel(@PathVariable(value = "labelId") Long id, @Valid @RequestBody Label labels) {
        return labelService.getOne(id).flatMap(existingLabels -> {
            existingLabels.setId(labels.getId());
            existingLabels.setName(labels.getName());
            return labelService.save(labels);
        })
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{labelId}")
    public Mono<ResponseEntity<Object>> deleteLabel(@PathVariable(value = "labelId") Long id) {
        return labelService.getOne(id).flatMap(existingLabels -> labelService.delete(existingLabels)
                .then(Mono.just(ResponseEntity.noContent().build())))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
