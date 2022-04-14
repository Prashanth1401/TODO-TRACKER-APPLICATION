import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomeComponent } from './home/home.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { MaterialModule } from './material-module';
import { ArchiveComponent } from './archive/archive.component';
import { AddTaskComponent } from './add-task/add-task.component';


import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatInputModule } from '@angular/material/input';

import { NgxSpinnerModule } from 'ngx-spinner';
import { ImportantComponent } from './important/important.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AccountComponent } from './account/account.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { UpdateTaskComponent } from './update-task/update-task.component';
import { CompletedComponent } from './completed/completed.component';



@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavBarComponent,
    ArchiveComponent,
    AddTaskComponent,
    ImportantComponent,
    AccountComponent,
    LoginComponent,
    RegisterComponent,
    UpdateTaskComponent,
    CompletedComponent,
    
    
  

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatInputModule,
    HttpClientModule,
    NgxSpinnerModule,
    ReactiveFormsModule,
    FormsModule,
  ],
  providers: [ MatDatepickerModule],
  bootstrap: [AppComponent],
})
export class AppModule { }
