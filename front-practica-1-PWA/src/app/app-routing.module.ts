import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainMenuComponent } from './Components/main-menu/main-menu.component';
import { ModifyOrCreateStudentComponent } from './Components/modify-or-create-student/modify-or-create-student.component';

const routes: Routes = [
  {path: '',pathMatch: 'full', redirectTo: 'main'},
  {path: 'create', component: ModifyOrCreateStudentComponent},
  {path: 'modify/:id', component: ModifyOrCreateStudentComponent},
  {path: 'main', component: MainMenuComponent},
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
