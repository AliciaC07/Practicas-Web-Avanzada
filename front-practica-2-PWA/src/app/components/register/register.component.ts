import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
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
  constructor(private registerService: RegisterService, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      let id = params['id'];
      if(id){
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
    this.registerService.register(this.user).subscribe();
  }

  update(){
    this.registerService.updateUser(this.user).subscribe();
  }

}
