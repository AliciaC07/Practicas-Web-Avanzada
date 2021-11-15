import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateMockupsComponent } from './components/create-mockups/create-mockups.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { LandingComponent } from './components/landing/landing.component';
import { ListUserComponent } from './components/list-user/list-user.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { AuthGuard } from './guards/auth-guard/auth.guard';
import { RoleGuard } from './guards/role-guard/role.guard';

const routes: Routes = [
  {path:'login', component: LoginComponent},
  {path:'landing', component: LandingComponent},
  {path:'dashboard', component: DashboardComponent, canActivate: [AuthGuard]},
  {path:'create', component: CreateMockupsComponent, canActivate: [AuthGuard]},
  {path:'update-mock/:id', component: CreateMockupsComponent, canActivate: [AuthGuard]},
  {path:'register', component: RegisterComponent, canActivate: [RoleGuard]},
  {path:'modify-user/:id', component: RegisterComponent, canActivate: [RoleGuard]},
  {path:'list-user', component:ListUserComponent, canActivate: [RoleGuard]},
  {path: '', pathMatch:'full', redirectTo: 'landing'},
  {path: '**', component: LandingComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
