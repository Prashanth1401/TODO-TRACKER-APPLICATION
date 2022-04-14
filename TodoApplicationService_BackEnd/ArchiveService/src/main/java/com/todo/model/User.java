package com.todo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document
@Getter
@Setter
public class User {

    @Id
    private String userId;
    private String userName;
    private String password;
    private List<Task> task;
    private List<Task> archive;
    private List<Task> completed;
}
