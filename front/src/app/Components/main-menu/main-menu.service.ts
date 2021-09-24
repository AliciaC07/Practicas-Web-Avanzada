import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Student } from 'src/app/models/student';

@Injectable({
  providedIn: 'root'
})
export class MainMenuService {
  private urlEndPoint: string ="http://localhost:8080/students";

  private httpHeaders = new HttpHeaders({'Content-Type':'application/json'})
  constructor(private http: HttpClient, private router: Router) { }

  getStudents(): Observable<Student[]>{
    return this.http.get<Student[]>(this.urlEndPoint, {headers: this.httpHeaders});
  }
}
