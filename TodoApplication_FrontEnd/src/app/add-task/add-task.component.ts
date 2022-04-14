import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatAccordion } from '@angular/material/expansion';
import { Router } from '@angular/router';
import { Task } from '../task';
import { TaskService } from '../task.service';


@Component({
  selector: 'app-add-task',
  templateUrl: './add-task.component.html',
  styleUrls: ['./add-task.component.css']
})
export class AddTaskComponent implements OnInit{
  checked = false;
  Tasks: any[]=[]
  task: Task = new Task();
  message: any;
  Task = new FormGroup({
    taskTitle: new FormControl('', Validators.required),
    taskDescription: new FormControl('', Validators.required),
    isImportant: new FormControl(false, Validators.nullValidator),
    category: new FormControl('', Validators.required),
    targetDate: new FormControl('', Validators.required),

  })

  addTask() {
    this.task.taskTitle = this.Task.get('taskTitle')?.value;
    this.task.taskDescription = this.Task.get('taskDescription')?.value;
    this.task.category = this.Task.get('category')?.value;
    this.task.isImportant = this.Task.get('isImportant')?.value;
    this.task.targetDate = this.Task.get('targetDate')?.value;
    this.service.taskadd(this.task).subscribe(o => {
      if (o.status == 200) {
        alert("Task Added");
        this.route.navigate(["home/task"])
      }
    })
  }

  @ViewChild(MatAccordion) accordion!: MatAccordion;

  constructor(private service: TaskService, private route:Router) { }

  ngOnInit(): void {

    this.service.getTask().subscribe(o => {
      this.Tasks = o.body;
    });
  }
  public deleteTask(taskId:String) {
    this.service.deleteTask(taskId).subscribe(o=>{
      if(o.status == 200) alert("Task deleted !")
    });
  }

  public setImp(data:String) {
    this.service.setImp(data).subscribe(o=>{
      if(o.status == 200) alert("set to Important !")
    })
  }

  public updateTask(data:any){
    this.service.task = data;
    this.route.navigate(["home/update"]);
  }

  public archive(data:String){
    this.service.archiveTask(data).subscribe(o=>{
      if(o.status == 200){
        alert("task archived !")
        this.route.navigate(["home/archive"]);
      }else alert("Server error occured !")
    })
  }

  public setCompleted(data:any){
    this.service.setCompleted(data).subscribe(o=>{
      if(o.status == 200) alert("Task Completed !")
    })
  }

}