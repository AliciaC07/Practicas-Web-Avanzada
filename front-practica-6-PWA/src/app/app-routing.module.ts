import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainComponent } from './components/main/main.component';
import { PastRegistriesComponent } from './components/past-registries/past-registries.component';

const routes: Routes = [
  {path:'main', component:MainComponent},
  {path:'pastregistries', component: PastRegistriesComponent},
  {path:'', pathMatch:'full', redirectTo:'main'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
