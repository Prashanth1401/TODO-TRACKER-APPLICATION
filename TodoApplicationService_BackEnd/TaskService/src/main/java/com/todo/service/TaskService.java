package com.todo.service;

import com.todo.exception.TaskNotFoundException;
import com.todo.exception.UserNotFoundException;
import com.todo.model.Task;
import com.todo.model.User;

import java.util.List;

public interface TaskService {
    User register(User user);
    boolean addTask(Task task, String userId) throws UserNotFoundException;
    boolean deleteTask(String userId, String taskId) throws TaskNotFoundException;
    boolean updateTask(Task task, String userId) throws TaskNotFoundException;
    List<Task> getAllTask(String userId);

    List<Task> getCompletedTask(String userId);

    boolean updateImpTask(String taskId, String userId);

    boolean updateArchiveTask(String taskId, String userId) throws TaskNotFoundException;

    boolean addCompletedTask(Task task, String userId) throws UserNotFoundException, TaskNotFoundException;

    boolean deleteCompletedTask(String userId, String taskId) throws TaskNotFoundException;
}
