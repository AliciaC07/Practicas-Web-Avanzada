import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginService } from '../login/login.service';
import Mock from './models/Mock';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  private dashboardEndpoint = `${this.authService.endPoint}/mocky`;

  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'});

  constructor(private authService: LoginService, private http: HttpClient) { }

  getMocks(userId: number): Observable<Mock[]>{
    return this.http.get<Mock[]>(`${this.dashboardEndpoint}/user/${userId}`, {headers: this.appendAuthorization()});
  }

  private appendAuthorization() {
    let token = this.authService.token;
    if(token != null){
      return this.httpHeaders.append('Authorization', 'Bearer ' + token);
    }
    return this.httpHeaders;
  }
}
