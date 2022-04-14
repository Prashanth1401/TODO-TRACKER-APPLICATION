import { Component, OnInit } from '@angular/core';
import { Archive } from '../archive';
import { ArchiveServiceService } from '../archive-service.service';
import { ImpTask } from '../ImpTask';
import { User } from '../user';

@Component({
  selector: 'app-archive',
  templateUrl: './archive.component.html',
  styleUrls: ['./archive.component.css']
})
export class ArchiveComponent implements OnInit {

  user!:User;
  archives:Archive[]=[];
  ArchieveTasks:any
  ArchieveTask: ImpTask=new ImpTask("","","",true,"","");

  message:any;
  constructor(private service:ArchiveServiceService) { }

  // deleteTask(taskId:String){
  //   this.archive.deleteArchiveTasks(taskId).subscribe((data)=>{
  //     alert("task deleted !");
  //   })
  //   document.location.reload();
  // }
  public deleteTask(taskId:String) {
    this.service.deleteTask(taskId).subscribe(o=>{
      if(o.status == 200) alert("Task deleted !")
    });
  }

  public addToTask(data:any){
    this.service.addToTaskList(data).subscribe(o=>{
      if(o.status == 200) alert("Added to task list !");
      else alert("Server Error !");
    })
  }

  ngOnInit(): void {
    this.service.getArchiveTasks().subscribe((data)=>{
      this.archives = data.body;
      
    })
  }

}
