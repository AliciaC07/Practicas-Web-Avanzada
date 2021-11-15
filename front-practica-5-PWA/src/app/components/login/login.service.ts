import { Injectable } from '@angular/core';
import { User } from './model/user';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  //public endPoint: string = 'http://localhost:8081';
  public endPoint : string = 'https://mockys.traki-tech.games';
  private _user?: User;
  private _token?: string;

  constructor(private http: HttpClient, private router: Router) { }

  public get user(): User{
    if(this._user != undefined){
      return this._user;
    }else if(this._user == undefined && sessionStorage.getItem('user') != null){
      this._user = JSON.parse(sessionStorage.getItem('user')!) as User;
      return this._user;
    }
    return new User();
  }

  public get token(): string{
    if(this._token != undefined){
      return this._token;
    } else if(this._token == undefined && sessionStorage.getItem('token') != null){
      this._token = sessionStorage.getItem('token')!;
      return this._token;
    }
    return '';
  }

  login(user: User): Observable<any>{
    const urlEndpoint =`${this.endPoint}/auth/login`;

    let params = {
      username: `${user.username}`,
      password: `${user.password}`
    }

    return this.http.post<any>(urlEndpoint,params);
  }

  saveUserCreds(response: any): void {
    this._user = new User();
    this._user.id = response.id;
    this._user.name = response.name;
    this._user.lastName = response.lastName;
    this._user.email = response.email;
    this._user.role = response.role;

    this._token = response.token;

    sessionStorage.setItem('user', JSON.stringify(this._user));
    sessionStorage.setItem('token', this._token!);
  }

  isAuthenticated(): boolean{
    let payload = this.user;
    if(payload != null && payload.name && payload.name.length > 0){
      return true;
    }
    return false;
  }

  logout(): void{
    this._token = undefined;
    this._user = undefined;
    sessionStorage.clear();
    this.router.navigate(['landing']);
  }

  isAdmin(): boolean{
    if(this.isAuthenticated()){
      if(this.user.role?.name === 'Admin'){
        return true;
      }
    }
    return false;
  }
}
