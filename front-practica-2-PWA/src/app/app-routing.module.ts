import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateMockupsComponent } from './components/create-mockups/create-mockups.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { LandingComponent } from './components/landing/landing.component';
import { ListUserComponent } from './components/list-user/list-user.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';

const routes: Routes = [
  {path:'login', component: LoginComponent},
  {path:'landing', component: LandingComponent},
  {path:'dashboard', component: DashboardComponent},
  {path:'create', component: CreateMockupsComponent},
  {path:'update-mock/:id', component: CreateMockupsComponent},
  {path:'register', component: RegisterComponent},
  {path:'modify-user/:id', component: RegisterComponent},
  {path:'list-user', component:ListUserComponent},
  {path: '', pathMatch:'full', redirectTo: 'landing'},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
