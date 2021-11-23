import { Component } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog'; 
import { ReserveComponent } from './components/reserve/reserve.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'practica6';

  constructor(public dialog: MatDialog){}

  openDialog(): void{
    const dialogRef = this.dialog.open(ReserveComponent, {
      width: '500px',
      height:'600px'
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('Dialog has closed');
    });
  }
}

// formato de fecha aaaa/MM/dd hh:00
