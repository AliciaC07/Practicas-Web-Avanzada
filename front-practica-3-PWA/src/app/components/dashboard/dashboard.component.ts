import { Component, OnInit } from '@angular/core';
import { LoginService } from '../login/login.service';
import { DashboardService } from './dashboard.service';
import Mock from './models/Mock';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  mocks: Mock[] = [];
  constructor(private dashboardService: DashboardService, private authService: LoginService) { }

  ngOnInit(): void {
    this.dashboardService.getMocks(this.authService.user.id!).subscribe(
      response =>{
        this.mocks = response;
      }
    )
  }

  getDate(mock: any): string {
    let dateString = mock.toString();
    let date = new Date(dateString);

    dateString = `${date.getDate()}/${date.getMonth()}/${date.getFullYear()} ${date.getHours()}:${date.getMinutes()}`;
    return dateString;
  }

  isExpired(mock: any): boolean{
    let dateString = mock.toString();
    let date = new Date(dateString);
    let now = new Date();
    if(date < now){
      return true
    }
    return false;
  }

  deleteMock(id: number){
    this.dashboardService.deleteMock(id).subscribe(response =>{
      window.location.reload();
    })
  }

}
