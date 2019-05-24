package com.jazasoft.taskmanager.repository;

import com.jazasoft.taskmanager.model.Label;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LabelRepository extends ReactiveMongoRepository<Label, String> {

    @Query("{'Priority.name':?0}")
    Flux<Label> findByName(String name);
}
