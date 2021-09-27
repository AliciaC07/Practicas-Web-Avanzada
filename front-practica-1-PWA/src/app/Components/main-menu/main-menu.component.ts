import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Student } from 'src/app/models/student';
import { ModifyOrCreateStudentService } from '../modify-or-create-student/modify-or-create-student.service';
import { MainMenuService } from './main-menu.service';

@Component({
  selector: 'app-main-menu',
  templateUrl: './main-menu.component.html',
  styleUrls: ['./main-menu.component.css']
})
export class MainMenuComponent implements OnInit {

 students: Student[] = []

  constructor(private mainMenuService: MainMenuService,private modifyOrCreate: ModifyOrCreateStudentService,private route: Router) { }

  ngOnInit(): void {
    this.mainMenuService.getStudents().subscribe(response=>{
      this.students = response;

    })
  }

  delete(student: Student): void {
    this.modifyOrCreate.deleteStudent(student.id).subscribe(
      response =>{
        window.location.reload();
      }
    )
  }

}
