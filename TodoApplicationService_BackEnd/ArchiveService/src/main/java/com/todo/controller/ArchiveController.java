package com.todo.controller;

import com.todo.exception.TaskNotFoundException;
import com.todo.exception.UserNotFoundException;
import com.todo.model.Task;
import com.todo.model.User;
import com.todo.service.ArchiveService;
import com.todo.service.ArchiveServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/Archieve")
public class ArchiveController {

    @Autowired
    ArchiveService archiveService;
    @GetMapping("/tasks/{userId}")
    public ResponseEntity<?> getAllTasks(@PathVariable String userId){
        return new ResponseEntity<>(archiveService.getAllTask(userId), HttpStatus.OK);
    }

    @GetMapping("/searchtask/{userid}")
    public User searchTaskById(@PathVariable String userid) throws TaskNotFoundException {
        return archiveService.searchTaskById(userid);
    }

    @DeleteMapping("/deletetask/{userid}/{taskid}")
    public Boolean deleteTaskById(@PathVariable String userid, @PathVariable String taskid) throws TaskNotFoundException {
        if(archiveService.deleteTask(userid,taskid)){
            return true;
        }
        else{
            throw new TaskNotFoundException();
        }
    }

    @PostMapping("/addtask/{userid}")
    public Boolean addTaskToList(@RequestBody Task task, @PathVariable String userid) throws UserNotFoundException {
        return archiveService.addTask(task,userid);
    }

    @PutMapping("/updatetask/{userid}")
    public Boolean updateTask(@RequestBody Task task, @PathVariable String userid) throws TaskNotFoundException {
        return archiveService.updateTask(task,userid);
    }

    @PutMapping("/totasklist/{userid}")
    public ResponseEntity<?> addToTaskList(@RequestBody Task task, @PathVariable String userid) throws TaskNotFoundException, UserNotFoundException {
        if(archiveService.addToTaskList(task,userid) && archiveService.deleteTask(userid,task.getTaskId())) {
            return new ResponseEntity<>("Task added to task list",HttpStatus.OK);
        }
        else return new ResponseEntity<>("Server error",HttpStatus.NOT_MODIFIED);
    }
}
