import { Component, OnInit } from '@angular/core';
import { Student } from 'src/app/models/student';

@Component({
  selector: 'app-main-menu',
  templateUrl: './main-menu.component.html',
  styleUrls: ['./main-menu.component.css']
})
export class MainMenuComponent implements OnInit {

 students: Student[] = []

  constructor() { }

  ngOnInit(): void {
    let mystud: Student = new Student();
    mystud.career="ISC";
    mystud.id = 1;
    mystud.idCollege = 2;
    mystud.lastName = "Diaz Cabrera";
    mystud.name = "Ruben Osmani";
    mystud.phone = "809-736-8658";

    this.students.push(mystud);
  }

}
