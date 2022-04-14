package com.todo.controller;

import com.todo.exception.TaskNotFoundException;
import com.todo.exception.UserNotFoundException;
import com.todo.model.Task;
import com.todo.model.User;
import com.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class TaskController {

    @Autowired
    TaskService service;

    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody User user){
        service.register(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<?> updateTask(@RequestBody Task task, @PathVariable String userId) throws TaskNotFoundException {
        if(service.updateTask(task,userId)) {
            return new ResponseEntity<>("task updated",HttpStatus.OK);
        }
        else return new ResponseEntity<>("task not updated",HttpStatus.NOT_MODIFIED);
    }

    @GetMapping("/tasks/{userId}")
    public ResponseEntity<?> getAllTasks(@PathVariable String userId){
        return new ResponseEntity<>(service.getAllTask(userId),HttpStatus.OK);
    }

    @PutMapping("/add/{userId}")
    public ResponseEntity<?> addTask(@RequestBody Task task, @PathVariable String userId) throws UserNotFoundException {

        service.addTask(task,userId);
        return new ResponseEntity<>("Task added",HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userId}/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable("userId") String userId, @PathVariable("taskId") String taskId) throws TaskNotFoundException {
        service.deleteTask(userId,taskId);
        return new ResponseEntity<>("Task deleted",HttpStatus.OK);
    }

    @PutMapping("/updateimptask/{userId}/{taskId}")
    public ResponseEntity<?> updateImpTask(@PathVariable("taskId") String taskId, @PathVariable("userId") String userId) throws TaskNotFoundException {
        if(service.updateImpTask(taskId,userId)) {
            return new ResponseEntity<>("task updated",HttpStatus.OK);
        }
        else return new ResponseEntity<>("task not updated",HttpStatus.NOT_MODIFIED);
    }

    @PutMapping("/updatearchivetask/{userId}/{taskId}")
    public ResponseEntity<?> updateArchiveTask(@PathVariable("taskId") String taskId, @PathVariable("userId") String userId) throws TaskNotFoundException {
        if(service.updateArchiveTask(taskId,userId)) {
            return new ResponseEntity<>("task archived",HttpStatus.OK);
        }
        else return new ResponseEntity<>("task not archived",HttpStatus.NOT_MODIFIED);
    }

    @PutMapping("/setcompleted/{userId}")
    public ResponseEntity<?> setCompleted(@RequestBody Task task, @PathVariable("userId") String userId) throws TaskNotFoundException, UserNotFoundException {
        if(service.addCompletedTask(task,userId) && service.deleteTask(userId, task.getTaskId())) {
            return new ResponseEntity<>("task Completed",HttpStatus.OK);
        }
        else return new ResponseEntity<>("task not Completed",HttpStatus.NOT_MODIFIED);
    }

    @GetMapping("/completedtasks/{userId}")
    public ResponseEntity<?> getCompletedTasks(@PathVariable String userId){
        return new ResponseEntity<>(service.getCompletedTask(userId),HttpStatus.OK);
    }

    @DeleteMapping("/deletecompleted/{userId}/{taskId}")
    public ResponseEntity<?> deleteCompletedTask(@PathVariable("userId") String userId, @PathVariable("taskId") String taskId) throws TaskNotFoundException {
        if(service.deleteCompletedTask(userId, taskId)){
            return new ResponseEntity<>("Task deleted",HttpStatus.OK);
        } else return new ResponseEntity<>("Not deleted",HttpStatus.NOT_MODIFIED);
    }

}
