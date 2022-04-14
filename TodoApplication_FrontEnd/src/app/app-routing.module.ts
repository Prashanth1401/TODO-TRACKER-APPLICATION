import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccountGuard } from './account.guard';
import { AccountComponent } from './account/account.component';
import { AddTaskComponent } from './add-task/add-task.component';
import { ArchiveComponent } from './archive/archive.component';
import { CompletedComponent } from './completed/completed.component';
import { ContentGuard } from './content.guard';
import { HomeComponent } from './home/home.component';
import { ImportantComponent } from './important/important.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { UpdateTaskComponent } from './update-task/update-task.component';


const routes: Routes = [
  {
    path: "", redirectTo: '/home/task', pathMatch: "full"
  },

  {
    path:'account',component:AccountComponent,canActivate:[AccountGuard],
    children:[
      {
        path:'login',component:LoginComponent
      },
      {
        path:"register",component:RegisterComponent
      }
    ]
  },


  {
    path: "home",
    component: HomeComponent,
    canActivate:[ContentGuard],
    children: [
      {
        path: "task",
        component: AddTaskComponent
      },
      {
        path: "archive",
        component: ArchiveComponent,
      },
      {
        path: "important",
        component: ImportantComponent,
      },
      {
        path:"update",
        component:UpdateTaskComponent
      },
      {
        path:"completed",
        component:CompletedComponent
      }
     
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
