import { Component, OnInit } from '@angular/core';
import Mock from './models/Mock';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  mocks: Mock[] = [];
  constructor() { }

  ngOnInit(): void {
    
  }

}
