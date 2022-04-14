import { Component, Input, OnInit } from '@angular/core';
import { MatDrawer } from '@angular/material/sidenav/drawer';
import { AccountService } from '../account.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  @Input()
  drawers!: MatDrawer;

  @Input() userName !: String

  constructor(private accService:AccountService) {
  }

  logout(){
    this.accService.isLoggedIn = false;
    this.accService.userName = this.userName = "Login";
  }

  ngOnInit(): void {
    this.userName = this.accService.userName;
  }

}
