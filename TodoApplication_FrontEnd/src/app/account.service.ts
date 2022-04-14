import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  isLoggedIn =false;
  userId="gsouvik@gmail.com";
  userName = "Login"
  constructor(private http: HttpClient) { }
  loginUrl = "http://localhost:9000/api/v2/login";
  registerUrl="http://localhost:9000/api/v1/register"
  public login(user: any): Observable<any> {
    return this.http.post(this.loginUrl, user, { observe: 'response' });
  }
  public register(user: any): Observable<any> {
    return this.http.post(this.  registerUrl, user, { observe: 'response' });
  }
}
