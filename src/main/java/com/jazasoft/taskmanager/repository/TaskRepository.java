package com.jazasoft.taskmanager.repository;

import com.jazasoft.taskmanager.model.Task;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TaskRepository extends ReactiveMongoRepository<Task, String> {

}
