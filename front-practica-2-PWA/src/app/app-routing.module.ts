import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateMockupsComponent } from './components/create-mockups/create-mockups.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { LandingComponent } from './components/landing/landing.component';
import { LoginComponent } from './components/login/login.component';

const routes: Routes = [
  {path:'login', component: LoginComponent},
  {path:'landing', component: LandingComponent},
  {path:'dashboard', component: DashboardComponent},
  {path:'create', component: CreateMockupsComponent},
  {path: '', pathMatch:'full', redirectTo: 'landing'},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
