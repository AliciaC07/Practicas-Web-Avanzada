import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, ObservableLike, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { CreateMockupsService } from '../create-mockups/create-mockups.service';
import { LoginService } from '../login/login.service';
import Mock from './models/Mock';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  private dashboardEndpoint = `${this.authService.endPoint}/mocky`;

  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'});

  constructor(private authService: LoginService, private http: HttpClient, private mockService: CreateMockupsService) { }

  getMocks(userId: number): Observable<Mock[]>{
    return this.http.get<Mock[]>(`${this.dashboardEndpoint}/user/${userId}`, {headers: this.appendAuthorization()});
  }

  deleteMock(id: number): Observable<Mock>{
    return this.http.delete<Mock>(`${this.dashboardEndpoint}/delete/${id}`, {headers: this.appendAuthorization()}).pipe(
      catchError(e =>{
        return throwError(e);
      })
    );

  }
  private appendAuthorization() {
    let token = this.authService.token;
    if(token != null){
      return this.httpHeaders.append('Authorization', 'Bearer ' + token);
    }
    return this.httpHeaders;
  }
}
