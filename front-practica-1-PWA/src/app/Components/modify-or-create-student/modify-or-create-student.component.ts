import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Student } from 'src/app/models/student';
import { ModifyOrCreateStudentService } from './modify-or-create-student.service';

@Component({
  selector: 'app-modify-or-create-student',
  templateUrl: './modify-or-create-student.component.html',
  styleUrls: ['./modify-or-create-student.component.css']
})
export class ModifyOrCreateStudentComponent implements OnInit {
  @ViewChild('Phone', {static: false}) private inputEl?: ElementRef; 

  student: Student = new Student();
  constructor(private activatedRoute: ActivatedRoute,private modifyOrCreate: ModifyOrCreateStudentService,private route: Router) { }

  ngOnInit(): void {
    this.student.idCollege = Number();
    this.activatedRoute.params.subscribe(params =>{
      let id = params['id']
      if(id){
        this.modifyOrCreate.getStudent(id).subscribe(
          response =>{
            this.student = response;
          }
        )
      }
    })
  }
  
  create() {
    this.student.phone = this.inputEl?.nativeElement.value;
    this.modifyOrCreate.createStudent(this.student).subscribe(
      response =>{
        this.route.navigate(['main'])
      },error =>{
        alert(error.error);
      }
    )
  }

  modify(){
    this.modifyOrCreate.updateStudent(this.student).subscribe(
      response =>{
        this.route.navigate(['main'])
      }, error =>{
        alert(error.error);
      }
    )
  }

}
