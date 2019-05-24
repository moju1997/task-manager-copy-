package com.jazasoft.taskmanager.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@Data
@Document

public class Label {

    @Id
    private String id;

    private String name;

    private List<Task> tasks;

    private Priority priority;

}
