package com.todo.rabbitmq.domain;

import com.todo.model.Task;

public class ArchiveDTO {
    private String userId;
    private Task task;

    public ArchiveDTO() { }

    public ArchiveDTO(String userId, Task task) {
        this.userId = userId;
        this.task = task;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "ArchiveDTO{" +
                "userId='" + userId + '\'' +
                ", task=" + task +
                '}';
    }
}
