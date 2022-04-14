import { Component, OnInit } from '@angular/core';
import { TaskService } from '../task.service';

@Component({
  selector: 'app-completed',
  templateUrl: './completed.component.html',
  styleUrls: ['./completed.component.css']
})
export class CompletedComponent implements OnInit {

  Tasks:any

  constructor(private service:TaskService) { }

  deleteTask(taskId:String){
    this.service.deleteCompleted(taskId).subscribe(o=>{
      if(o.status == 200) alert("Task Deleted !")
      else alert("Server Error !");
    })
  }

  ngOnInit(): void {
    this.service.getCompletedTasks().subscribe(o=>{
      if(o.status == 200) this.Tasks = o.body
    })
  }

}
