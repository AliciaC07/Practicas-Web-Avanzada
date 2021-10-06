import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../login/model/user';
import UserDto from './models/userDto';
import { RegisterService } from './register.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  user: UserDto = new UserDto();
  title: string = "Register User"
  constructor(private registerService: RegisterService, private activatedRoute: ActivatedRoute, private route: Router) { }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      let id = params['id'];
      if(id){
        this.title = "Modify User";
        this.registerService.getUser(id).subscribe(
          response =>{
            this.user.name = response.name;
            this.user.lastName = response.lastName;
            this.user.id = response.id;
            this.user.password = '';
            this.user.email = response.email;
            this.user.role = response.role?.name;
            this.user.username = response.username;
          }
        )
      }
    })
  }

  register(){
    this.registerService.register(this.user).subscribe(
      response =>{
        this.route.navigate(['list-user']);
      }, error =>{
        alert(error.error);
      }
    );
  }

  update(){
    this.registerService.updateUser(this.user).subscribe(
      response =>{
        this.route.navigate(['list-user']);
      }, error =>{
        alert(error.error);
      }
    );
  }

}
