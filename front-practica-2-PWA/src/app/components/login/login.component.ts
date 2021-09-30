import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from './login.service';
import { User } from './model/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user: User = new User;
  constructor(private authService: LoginService, private router: Router) { }

  ngOnInit(): void {
  }

  login(): void{
    this.authService.login(this.user).subscribe(response =>{
      this.authService.saveUserCreds(response);
      //save token too
      this.router.navigate(['landing']);
    })
  }

}
