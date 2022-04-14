import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {AccountService} from 'src/app/account.service';
import { Task } from './task';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  task!:any

  empty = null;
addTask="http://localhost:9000/api/v1/add/"

deleteTaskUrl = "http://localhost:9000/api/v1/delete/"

updateUrl = "http://localhost:9000/api/v1/update/"

archiveUrl = "http://localhost:9000/api/v1/updatearchivetask/"

impTaskUrl = "http://localhost:9000/api/v1/updateimptask/"
getTaskUrl = "http://localhost:9000/api/v1/tasks/"
completedUrl = "http://localhost:9000/api/v1/setcompleted/"
getCompletedUrl = "http://localhost:9000/api/v1/completedtasks/"

deleteCompletedUrl = "http://localhost:9000/api/v1/deletecompleted/"


  constructor(private http:HttpClient, private accService :AccountService) { }
  public taskadd(task: any): Observable<any> {
    return this.http.put(this.addTask + this.accService.userId, task, { responseType: 'text' as 'json', observe: 'response' });
  }

  public setImp(data:String):Observable<any>{
    return this.http.put(this.impTaskUrl + this.accService.userId +"/"+data,this.empty,{responseType: 'text' as 'json',observe:'response'});
  }
  public getTask():Observable<any>{
    return this.http.get(this.getTaskUrl+this.accService.userId,{observe: 'response'});
  }

  public deleteTask(taskId:String):Observable<any>{
    return this.http.delete(this.deleteTaskUrl + this.accService.userId + "/" + taskId,{responseType: 'text' as 'json',observe: 'response'})
  }

  public updateTask(task:any):Observable<any>{
    return this.http.put(this.updateUrl+this.accService.userId,task,{responseType: 'text' as 'json',observe: 'response'})
  }

  public archiveTask(taskId:String):Observable<any>{
    return this.http.put(this.archiveUrl+this.accService.userId+"/"+taskId,this.empty,{responseType: 'text' as 'json',observe: 'response'})
  }

  public setCompleted(task:any):Observable<any>{
    return this.http.put(this.completedUrl+this.accService.userId,task,{responseType: 'text' as 'json',observe: 'response'})
  }

  public getCompletedTasks():Observable<any>{
    return this.http.get(this.getCompletedUrl+this.accService.userId,{observe: 'response'})
  }

  public deleteCompleted(taskId:String):Observable<any>{
    return this.http.delete(this.deleteCompletedUrl+this.accService.userId+"/"+taskId,{responseType: 'text' as 'json',observe: 'response'})
  }

}
