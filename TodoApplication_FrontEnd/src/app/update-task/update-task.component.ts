import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Task } from '../task';
import { TaskService } from '../task.service';

@Component({
  selector: 'app-update-task',
  templateUrl: './update-task.component.html',
  styleUrls: ['./update-task.component.css']
})
export class UpdateTaskComponent implements OnInit {

  task: Task = new Task();

  Task = new FormGroup({
    taskTitle: new FormControl('', Validators.required),
    taskDescription: new FormControl('', Validators.required),
    category: new FormControl('', Validators.required),
    targetDate: new FormControl('', Validators.required),

  })

  constructor(private service:TaskService, private route:Router) {
    this.task = this.service.task
  }

  updateTask(){
    this.task.taskTitle = this.Task.get('taskTitle')?.value;
    this.task.taskDescription = this.Task.get('taskDescription')?.value;
    this.task.category = this.Task.get('category')?.value;
    this.task.targetDate = this.Task.get('targetDate')?.value;
    this.service.updateTask(this.task).subscribe(o=>{
      if(o.status == 200){
        alert("Task updated !");
        this.route.navigate(["home/task"]);
      } else {
        alert("Server error occured !");
      }
    })
  }

  ngOnInit(): void {
  }

}
