import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, filter, Observable, throwError } from 'rxjs';
import Reserve from '../components/reserve/models/reserve';

@Injectable({
  providedIn: 'root'
})
export class GlobalService {

  urlEndpoint: string = 'https://8fsl46w8z8.execute-api.us-east-1.amazonaws.com/test2-cors/reservations-get';

  constructor(private http: HttpClient) { }

  getAllReserve(): Observable<Reserve[]> {
    return this.http.get<Reserve[]>(this.urlEndpoint);
  }

  postReservation(reservation: Reserve): Observable<Reserve>{
    return this.http.post<Reserve>(this.urlEndpoint, reservation).pipe(
      catchError(e => {
        return throwError(() => e);
      })
    );
  }
  /*
    let date = new Date(this.reservations[0].reservationDate.split(' ')[0]);
      let time = this.reservations[0].reservationDate.split(' ')[1];
      date.setHours(+time.split(':')[0]);
      console.log(time);
      console.log(date);
      console.log(new Date());
  */ 

  filterReservesNew(response: Reserve[]): Reserve[]{
    let filtered: Reserve[] = [];
    response.forEach(res =>{
      let date = new Date(res.reservationDate.split(' ')[0]);
      let time = res.reservationDate.split(' ')[1];
      date.setHours(+time.split(':')[0]+1);
      date.setDate(date.getDate()+1);
      date.setMonth(date.getMonth()+1);
      
      let today = new Date()
      /*console.log(res.name)
      console.log(date.toISOString(),'date');
      console.log(today.toISOString(),'today');
      console.log('today greater than date?',today.toISOString() > date.toISOString());
      */

      let todayString = today.toISOString().split("T")[0];
      let dateString = today.toISOString().split("T")[0];

      /*console.log(todayString, "today String");
      console.log(dateString, "date String");*/
      if(today.getTime() < date.getTime()){//la puta el filtro no quiere na coño 
        filtered.push(res);
      }
    })
    return filtered;
  }
  
  filterReservesOld(response: Reserve[]): Reserve[]{
    let filtered: Reserve[] = [];
    response.forEach(res =>{
      let date = new Date(res.reservationDate.split(' ')[0]);
      let time = res.reservationDate.split(' ')[1];
      date.setHours(+time.split(':')[0]+1);
      date.setDate(date.getDate()+1);
      date.setMonth(date.getMonth()+1);
      
      let today = new Date()
      /*console.log(res.name)
      console.log(date.toISOString(),'date');
      console.log(today.toISOString(),'today');
      console.log('today greater than date?',today.toISOString() > date.toISOString());
      */

      /*let todayString = today.toISOString().split("T")[0];
      let dateString = today.toISOString().split("T")[0];

      console.log(todayString, "today String");
      console.log(dateString, "date String");*/
      if(today.getTime() > date.getTime()){//la puta el filtro no quiere na coño 
        filtered.push(res);
      }
    })
    return filtered;
  }
}
