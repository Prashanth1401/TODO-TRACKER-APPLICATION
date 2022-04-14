import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AccountService } from '../account.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginDetails: any = {
    userId: "",
    password: ""
  }

  Login = new FormGroup(
    {
      userId: new FormControl('', [Validators.required]),
      password: new FormControl('', Validators.required),
    }
  );

  exists!: boolean;

  login() {
    this.loginDetails.userId = this.Login.get('userId')?.value
    this.loginDetails.password = this.Login.get('password')?.value
    this._service.login(this.loginDetails).subscribe((obj) => {
      if (obj.status == 200) {
        this._service.isLoggedIn = true;
        this._service.userName = obj.body.user.userName;
        this.exists = true;
        this.openSnackBar();
        this._service.userId = this.Login.get('userId')?.value;

        this.router.navigate(["home/task"])
      }
      else alert("Server Error Please Try after Sometime");
    }, error => {
      this.exists = false;
      this.openSnackBar();
      console.log("User not found");
      this.router.navigate(["account/register"])
    })
  }



  constructor(private _service: AccountService, private _snackBar: MatSnackBar, private router: Router) { }

  openSnackBar() {
    if (this.exists) {
      this._snackBar.openFromComponent(SuccessMessage, {
        duration: 3000,
      });
    } else {
      this._snackBar.openFromComponent(ErrorMessage, {
        duration: 3000,
      });
    }
  }

  ngOnInit(): void {
  }

}

@Component({
  selector: 'error-msg',
  template: '<div><span class="errorMsg">User Not Registered !!</span></div>',
  styles: [
    `
    div{
      display: flex;
      justify-content: center;
      
    }
    .errorMsg {
      text-align: center;
      font-size: large;
      color: red;
    }
  `,
  ],
})
export class ErrorMessage { }

@Component({
  selector: 'success-msg',
  template: '<div><span class="errorMsg">Successfully Logged In !!</span></div>',
  styles: [
    `
    div{
      display: flex;
      justify-content: center;
      
    }
    .errorMsg {
      text-align: center;
      font-size: large;
      color: green;
    }
  `,
  ],
})
export class SuccessMessage { }

