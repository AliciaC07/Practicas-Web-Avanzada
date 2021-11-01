import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GraphsComponent } from './graphs/graphs.component';

const routes: Routes = [
  {path: '', redirectTo:'charts', pathMatch: 'full'},
  {path: 'charts', component: GraphsComponent},
  {path: '**', redirectTo:'charts'}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
