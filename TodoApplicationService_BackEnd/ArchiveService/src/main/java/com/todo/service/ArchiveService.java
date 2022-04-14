package com.todo.service;

import com.todo.exception.TaskNotFoundException;
import com.todo.exception.UserNotFoundException;
import com.todo.model.Task;
import com.todo.model.User;

import java.util.List;

public interface ArchiveService {
    public User searchTaskById(String userId) throws TaskNotFoundException;
    public boolean addTask(Task task, String userId) throws UserNotFoundException;
    public boolean deleteTask(String userId, String taskId) throws TaskNotFoundException;
    public boolean updateTask(Task task, String userId) throws TaskNotFoundException;
    public   List<Task> getAllTask(String userId);
    boolean updateImpTask(String userId, String taskId);

    boolean addToTaskList(Task task, String userId) throws UserNotFoundException;
}
