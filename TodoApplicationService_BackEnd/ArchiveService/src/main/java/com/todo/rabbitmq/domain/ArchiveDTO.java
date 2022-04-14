package com.todo.rabbitmq.domain;

import com.todo.model.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ArchiveDTO {

    private String userId;
    private Task task;
}
