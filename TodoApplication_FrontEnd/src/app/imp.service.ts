import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ImpTask } from './ImpTask';
import {AccountService} from 'src/app/account.service';
@Injectable({
  providedIn: 'root'
})
export class ImpService {
  [x: string]: any;
  task!:any
  userId = "";
  taskId = "";

  loginUrl = "http://localhost:9000/api/v2/login";
  registerUrl = "http://localhost:9000/api/v1/register";
  addTaskUrl = "http://localhost:9000/api/imp/add/";
  getTaskUrl = "http://localhost:9000/api/imp/imptasks/";
  deleteUrl="http://localhost:9000/api/imp/delete/";
  updateUrl = "http://localhost:9000/api/imp/update/"
  constructor(private http: HttpClient, private accService:AccountService) { }

  public doImpsave(ImpTask: ImpTask): Observable<any> {
    return this.http.put(this.addTaskUrl + this.accService.userId, ImpTask, { responseType: 'text' as 'json', observe: 'response' });
  }

  public getTask(): Observable<any> {
    return this.http.get(this.getTaskUrl + this.accService.userId, { observe: 'response' });
  }

  public login(user: any): Observable<any> {
    return this.http.post(this.loginUrl, user, { observe: 'response' });
  }

  public register(user: any): Observable<any> {
    return this.http.post(this.registerUrl, user, { observe: 'response' });
  }
  public Impdelet(taskId:String): Observable<any>{
    return this.http.delete(this.deleteUrl+this.accService.userId+"/"+taskId,{observe:"response"});
  }
  public updateTask(task:any):Observable<any>{
    return this.http.put(this.updateUrl+this.accService.userId,task,{responseType: 'text' as 'json',observe: 'response'})
  }
}
