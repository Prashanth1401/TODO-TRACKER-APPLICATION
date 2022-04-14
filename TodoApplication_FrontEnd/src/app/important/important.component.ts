import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ImpService } from '../imp.service';
import { ImpTask } from '../ImpTask';
import { TaskService } from '../task.service';
@Component({
  selector: 'app-important',
  templateUrl: './important.component.html',
  styleUrls: ['./important.component.css']
})
export class ImportantComponent implements OnInit {

   ImpTasks:any
  ImpTask: ImpTask=new ImpTask("","","",true,"","");
  // ImpTasks: any[]=[]
  // Imptask: ImpTask = new ImpTask();
  message:any;
  Task=new FormGroup({
    taskId:new FormControl('',Validators.required),
    taskTitle:new FormControl('',Validators.required),
    taskDescription:new FormControl('',Validators.required),
    isImportant:new FormControl(false,Validators.required),
    category:new FormControl('',Validators.required),
    targetDate:new FormControl('',Validators.required),})

  constructor(private service:ImpService,private route:Router,private taskservice:TaskService) { }

  ngOnInit(): void {
    let resp=this.service.getTask().subscribe(o =>{
      this.ImpTasks = o.body;
      
    });
  
  }



  public updateTask(data:any){
    this.taskservice.task = data;
    this.route.navigate(["home/update"]);
  }


  public Impdelet(taskId:String){
    // let resp=this.service.Impdelet();
    // resp.subscribe();
    this.service.Impdelet(taskId).subscribe(o=>{
      if(o.status == 200) alert("Task deleted !")
    });
      }


      public archive(data:String){
        this. taskservice.archiveTask(data).subscribe(o=>{
          if(o.status == 200){
            alert("task archived !")
            this.route.navigate(["home/archive"]);
          }else alert("Server error occured !")
        })
      }

      addTask(){
        this.service.doImpsave(this.Task.value).subscribe(o=>{
          if(o.status == 200){
            alert("Task Added");
          }
        })
        }  
}
