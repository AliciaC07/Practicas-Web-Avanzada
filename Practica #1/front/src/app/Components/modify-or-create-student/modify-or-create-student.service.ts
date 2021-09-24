import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, throwError } from 'rxjs';
import { Student } from 'src/app/models/student';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ModifyOrCreateStudentService {
  private urlEndPoint: string ="http://localhost:8080/student";
  private urlEndpointgetSingle: string = "http://localhost:8080/student-mat"

  private httpHeaders = new HttpHeaders({'Content-Type':'application/json'})
  constructor(private http: HttpClient, private router: Router) { }

  createStudent(student:Student):Observable<Student> {
    return this.http.post<Student>(this.urlEndPoint,student,{headers:this.httpHeaders});
  }

  getStudent(id:number): Observable<Student>{
    return this.http.get<Student>(`${this.urlEndpointgetSingle}/${id}`,{headers: this.httpHeaders});
  }

  updateStudent(student: Student):Observable<Student>{
    return this.http.put<Student>(`${this.urlEndPoint}/${student.id}`, student,{headers: this.httpHeaders}).pipe(
        catchError(e =>{
          return throwError(e);
        }
      )
    )
  }

  deleteStudent(id:number):Observable<Student>{
    return this.http.delete<Student>(`${this.urlEndPoint}/${id}`,{headers: this.httpHeaders}).pipe(
      catchError(e=>{
        return throwError(e);
      })
    );
  }
}

