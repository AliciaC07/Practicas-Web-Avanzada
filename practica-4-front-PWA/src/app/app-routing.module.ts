import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GraphsComponent } from './graphs/graphs.component';
import { RawDataComponent } from './raw-data/raw-data.component';

const routes: Routes = [
  {path: '', redirectTo:'charts', pathMatch: 'full'},
  {path: 'charts', component: GraphsComponent},
  {path:'raw-data', component:RawDataComponent},
  {path: '**', redirectTo:'charts'},
  

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
