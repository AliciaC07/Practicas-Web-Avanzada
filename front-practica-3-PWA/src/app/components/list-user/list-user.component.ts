import { Component, OnInit } from '@angular/core';
import { User } from '../login/model/user';
import { RegisterService } from '../register/register.service';

@Component({
  selector: 'app-list-user',
  templateUrl: './list-user.component.html',
  styleUrls: ['./list-user.component.css']
})
export class ListUserComponent implements OnInit {

  users: User[] = [];
  constructor(private registerService: RegisterService) { }

  ngOnInit(): void {
    this.registerService.getAllUsers().subscribe(
      response =>{
        this.users = response;
      }
    )
  }

  delete(user: string | undefined){
    this.registerService.deleteUser(user!).subscribe(
      response =>{
        window.location.reload();
      }, error =>{
        alert(error.error);
      }
    )
  }

}
