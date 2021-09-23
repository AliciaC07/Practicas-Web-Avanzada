import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Student } from 'src/app/models/student';

@Component({
  selector: 'app-modify-or-create-student',
  templateUrl: './modify-or-create-student.component.html',
  styleUrls: ['./modify-or-create-student.component.css']
})
export class ModifyOrCreateStudentComponent implements OnInit {

  student: Student = new Student();
  constructor(private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.student.idCollege = Number();
    this.activatedRoute.params.subscribe(params =>{
      let id = params['id']
      if(id){
        console.log(id, "id que llega");
        console.log(this.student.id, "id del objeto");
      }
    })
  }
  
  create() {
    console.log(this.student);
  }

  modify(){
    console.log("tried to modify");
  }

}
