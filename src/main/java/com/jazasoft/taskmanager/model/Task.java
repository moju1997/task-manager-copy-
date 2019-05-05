package com.jazasoft.taskmanager.model;

import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@Data
@Document
public class Task {

    @Id
    private Long id;

    private String name;

}
