import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { GlobalService } from 'src/app/services/global.service';
import Reserve from '../reserve/models/reserve';
import { ReserveComponent } from '../reserve/reserve.component';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  reservations: Reserve[] = [];

  constructor(public dialog: MatDialog, private service: GlobalService){}

  openDialog(): void{
    const dialogRef = this.dialog.open(ReserveComponent, {
      width: '500px',
      height:'600px'
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('Dialog has closed');
    });
  }


  ngOnInit(): void {

    this.service.getAllReserve().subscribe(response => {
      this.reservations = this.service.filterReservesNew(response);
    });
  }

}
