import { Time } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { GlobalService } from 'src/app/services/global.service';
import Reserve from './models/reserve';

@Component({
  selector: 'app-reserve',
  templateUrl: './reserve.component.html',
  styleUrls: ['./reserve.component.css']
})
export class ReserveComponent implements OnInit {
  reserve: Reserve = new Reserve();
  date: string = '';
  time: string = '';

  constructor(public dialogRef: MatDialogRef<ReserveComponent>, private service: GlobalService, private router: Router) { }

  ngOnInit(): void {
  }

  closeDialog(): void {
    this.dialogRef.close()
  }

  sendInfo(): void{
    
    
    let date: Date = new Date(this.date);

    this.reserve.reservationDate = `${date.getFullYear()}-${date.getMonth()}-${date.getDate()} ${this.time}:00 a ${ +this.time + 1}:00`;
    console.log(this.reserve);
    this.service.postReservation(this.reserve).subscribe(
      () =>{
        alert('Process completed')
        location.reload();
      }, error =>{
        alert(error.message)
        console.log(error);
      }
    );
    this.closeDialog();
  }

}
