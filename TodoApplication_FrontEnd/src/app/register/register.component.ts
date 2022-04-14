import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AccountService } from '../account.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  RegisterDetails: any = {
    userId: "",
    userName:"",
    password: ""
  }
  Register= new FormGroup(
    {
      userId: new FormControl('', [Validators.required]),
      userName: new FormControl('', [Validators.required]),
      password: new FormControl('', Validators.required),
    }
  );
  constructor(private service:AccountService, private router: Router) { }

  ngOnInit(): void {
    
  }
  register(){
    this.RegisterDetails.userId = this.Register.get('userId')?.value
    this.RegisterDetails.userName = this.Register.get('userName')?.value
    this.RegisterDetails.password = this.Register.get('password')?.value
    this.service.register(this.RegisterDetails).subscribe((o)=>{
      if(o.status==201)
      {
        this.router.navigate(["account/login"])
      }
    })
  }

}
