package com.jazasoft.taskmanager.repository;

import com.jazasoft.taskmanager.model.Label;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface LabelRepository extends ReactiveMongoRepository<Label,Long> {
}
