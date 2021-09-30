import { Component } from '@angular/core';
import { LoginService } from './components/login/login.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Mocky.ly';
  
  constructor(private authService: LoginService){}

  isAuth(): boolean{
    return this.authService.isAuthenticated();
  }

  logout(): void {
    this.authService.logout();
  }
}
