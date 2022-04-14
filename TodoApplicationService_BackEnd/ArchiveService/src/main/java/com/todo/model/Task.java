package com.todo.model;


import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Task {


    String taskId;
    String taskTitle;
    String taskDescription;
    boolean isImportant;
    String category;
    String targetDate;

}
