import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import Mock from '../dashboard/models/Mock';
import { LoginService } from '../login/login.service';
import ContentType from './models/ContentType';
import HttpStatusCode from './models/HttpStatusCode';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CreateMockupsService {

  private CTEndPoint: string = `${this.authService.endPoint}/content-type`;
  private HSCEndPoint: string = `${this.authService.endPoint}/httpstatuscode`;
  private urlEndpoint: string = `${this.authService.endPoint}/mocky`;

  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'});



  constructor(private authService: LoginService, private http: HttpClient) { }

  getStatusCodes():Observable<HttpStatusCode[]> {
    return this.http.get<HttpStatusCode[]>(this.HSCEndPoint, {headers: this.appendAuthorization()});
  }

  getContentTypes(): Observable<ContentType[]> {
    return this.http.get<ContentType[]>(this.CTEndPoint, {headers: this.appendAuthorization()});
  }

  create(mock: Mock):Observable<Mock>{
    return this.http.post<Mock>(this.urlEndpoint,mock,{headers: this.appendAuthorization()}).pipe(
      catchError(e => {
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
