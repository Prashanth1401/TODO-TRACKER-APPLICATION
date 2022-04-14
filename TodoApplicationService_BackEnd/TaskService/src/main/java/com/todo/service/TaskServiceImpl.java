package com.todo.service;

import com.todo.config.Producer;
import com.todo.exception.TaskNotFoundException;
import com.todo.exception.UserNotFoundException;
import com.todo.model.Task;
import com.todo.model.User;
import com.todo.proxy.Proxy;
import com.todo.rabbitmq.domain.ArchiveDTO;
import com.todo.rabbitmq.domain.ImpServiceDTO;
import com.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository repository;

    @Autowired
    Proxy proxy;

    @Autowired
    Producer producer;

    @Override
    public User register(User user) {
        proxy.saveUser(user);
        repository.save(user);
        return user;
    }

    @Override
    public boolean addTask(Task task, String userId) throws UserNotFoundException {
        if(repository.findById(userId).isEmpty()){
            throw new UserNotFoundException();
        }
        User user = repository.findById(userId).get();
        List<Task> tasks = user.getTask();
        if(tasks == null) {
            tasks = new ArrayList<>();
        }
        task.setTaskId(UUID.randomUUID().toString());
        tasks.add(task);
        user.setTask(tasks);
        repository.save(user);
        return true;
    }

    @Override
    public boolean deleteTask(String userId, String taskId) throws TaskNotFoundException {
        User user = repository.findById(userId).get();
        List<Task> tasks = user.getTask();
        Task task = tasks.stream()
                .filter(obj -> taskId.equals(obj.getTaskId()))
                .findAny().orElse(null);
        if(tasks == null || !tasks.contains(task)){
              throw new TaskNotFoundException();
        }
        tasks.remove(task);
        user.setTask(tasks);
        repository.save(user);
        return true;
    }

    @Override
    public boolean updateTask(Task task, String userId) throws TaskNotFoundException {
        User user = repository.findById(userId).get();
        List<Task> tasks = user.getTask();
        Task taskObj = tasks.stream()
                .filter(obj -> task.getTaskId().equals(obj.getTaskId()))
                .findAny().orElse(null);
        if(tasks == null){
            throw new TaskNotFoundException();
        }
        tasks.remove(taskObj);
        tasks.add(task);
        user.setTask(tasks);
        repository.save(user);
        return true;
    }

    @Override
    public List<Task> getAllTask(String userId) {
        List<Task> tasks = repository.findById(userId).get().getTask();

        return tasks;
    }

    @Override
    public List<Task> getCompletedTask(String userId) {
        List<Task> tasks = repository.findById(userId).get().getCompleted();

        return tasks;
    }

    @Override
    public boolean updateImpTask(String taskId, String userId) {
        ImpServiceDTO impServiceDTO = new ImpServiceDTO(userId,taskId);
        producer.sendMessageToRabbitTemplate(impServiceDTO);
        return true;
    }

    @Override
    public boolean updateArchiveTask(String taskId, String userId) throws TaskNotFoundException {
        User user = repository.findById(userId).get();
        List<Task> tasks = user.getTask();
        Task task = tasks.stream()
                .filter(obj -> taskId.equals(obj.getTaskId()))
                .findAny().orElse(null);
        if(tasks == null || !tasks.contains(task)){
            throw new TaskNotFoundException();
        }
        tasks.remove(task);
        user.setTask(tasks);
        repository.save(user);
        ArchiveDTO archiveDTO = new ArchiveDTO(userId,task);
        producer.sendMessageToRabbitTemplate2(archiveDTO);
        return true;
    }

    @Override
    public boolean addCompletedTask(Task task, String userId) throws UserNotFoundException {

        if(repository.findById(userId).isEmpty()){
            throw new UserNotFoundException();
        }
        User user = repository.findById(userId).get();
        List<Task> completedtasks = user.getCompleted();
        if(completedtasks == null) {
            completedtasks = new ArrayList<>();
        }
        completedtasks.add(task);
        user.setCompleted(completedtasks);
        repository.save(user);
        return true;
    }

    @Override
    public boolean deleteCompletedTask(String userId, String taskId) throws TaskNotFoundException {
        User user = repository.findById(userId).get();
        List<Task> tasks = user.getCompleted();
        Task task = tasks.stream()
                .filter(obj -> taskId.equals(obj.getTaskId()))
                .findAny().orElse(null);
        if(tasks == null || !tasks.contains(task)){
            throw new TaskNotFoundException();
        }
        tasks.remove(task);
        user.setCompleted(tasks);
        repository.save(user);
        return true;
    }

}
