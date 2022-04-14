import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AccountService } from './account.service';
import { Archive } from './archive';

@Injectable({
  providedIn: 'root'
})
export class ArchiveServiceService {

  
  constructor(private http:HttpClient, private accService:AccountService) { }

  public getArchiveTasks(): Observable<any>{
    return this.http.get("http://localhost:9000/api/Archieve/tasks/"+this.accService.userId,{observe:"response"});
  }

  
  public deleteTask(taskid: any){
    return this.http.delete("http://localhost:9000/api/Archieve/deletetask/"+this.accService.userId+"/"+taskid,{responseType: 'text' as 'json',observe:"response"})
  }

  public addToTaskList(task:any):Observable<any>{
    return this.http.put("http://localhost:9000/api/Archieve/totasklist/"+this.accService.userId,task,{responseType: 'text' as 'json',observe:"response"})
  }

}
