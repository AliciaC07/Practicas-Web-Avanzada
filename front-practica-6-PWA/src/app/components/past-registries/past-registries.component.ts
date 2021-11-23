import { Component, OnInit } from '@angular/core';
import { GlobalService } from 'src/app/services/global.service';
import Reserve from '../reserve/models/reserve';

@Component({
  selector: 'app-past-registries',
  templateUrl: './past-registries.component.html',
  styleUrls: ['./past-registries.component.css']
})
export class PastRegistriesComponent implements OnInit {

  reservations: Reserve[] = [];
  constructor(private service: GlobalService) { }

  ngOnInit(): void {
    this.service.getAllReserve().subscribe(
      response => {
        this.reservations= this.service.filterReservesOld(response);
        console.log(this.reservations);
      }
    );
  }

}
