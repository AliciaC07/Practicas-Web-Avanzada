import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { LoginService } from '../login/login.service';
import { User } from '../login/model/user';
import UserDto from './models/userDto';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  private endpoint: string = `${this.authService.endPoint}/auth/signup`;
  private userEndPoint: string = `${this.authService.endPoint}/users`;
  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'});

  constructor(private authService: LoginService, private http: HttpClient) { }

  register(user: UserDto): Observable<UserDto>{
    return this.http.post<UserDto>(this.endpoint,user,{headers: this.appendAuthorization()}).pipe(
      catchError(e =>{
        return throwError(e);
      })
    );
  }

  getUser(id: number):Observable<User> {
    return this.http.get<User>(`${this.authService.endPoint}/user-id/${id}`, {headers: this.appendAuthorization()});
  }

  updateUser(user: UserDto): Observable<UserDto>{
    return this.http.put<UserDto>(`${this.authService.endPoint}/user/${user.id}`, user,{headers: this.appendAuthorization()}).pipe(
      catchError(e =>{
        return throwError(e);
      })
    )
  }

  deleteUser(user: string): Observable<User> {
    return this.http.delete<User>(`${this.authService.endPoint}/user/${user}`, {headers: this.appendAuthorization()}).pipe(
      catchError(e =>{
        return throwError(e);
      })
    )
  }

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.userEndPoint,{headers: this.appendAuthorization()});
  }

  private appendAuthorization() {
    let token = this.authService.token;
    if(token != null){
      return this.httpHeaders.append('Authorization', 'Bearer ' + token);
    }
    return this.httpHeaders;
  }
}
