package com.jazasoft.taskmanager.model;

import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Data
@Document
public class Task {

    @Id
    private String id;

    private String name;

    private Label label;


}
