package com.todo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class User {

    @Id
    private String userId;
    private String userName;
    private String password;
    private List<Task> task;
    private List<Task> archive;
    private List<Task> completed;

    public User() { }

    public User(String userId, String userName, String password, List<Task> task, List<Task> archive, List<Task> completed) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.task = task;
        this.archive = archive;
        this.completed = completed;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Task> getTask() {
        return task;
    }

    public void setTask(List<Task> task) {
        this.task = task;
    }

    public List<Task> getArchive() {
        return archive;
    }

    public void setArchive(List<Task> archive) {
        this.archive = archive;
    }

    public List<Task> getCompleted() {
        return completed;
    }

    public void setCompleted(List<Task> completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", task=" + task +
                ", archive=" + archive +
                '}';
    }
}
