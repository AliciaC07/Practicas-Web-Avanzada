import { analyzeAndValidateNgModules } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import Message from '../models/message';
import { Client } from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import { prepareEventListenerParameters } from '@angular/compiler/src/render3/view/template';
import { prepareSyntheticListenerFunctionName } from '@angular/compiler/src/render3/util';
import { elementEventFullName } from '@angular/compiler/src/view_compiler/view_compiler';
import { Color } from '@swimlane/ngx-charts';

@Component({
  selector: 'app-graphs',
  templateUrl: './graphs.component.html',
  styleUrls: ['./graphs.component.css']
})
export class GraphsComponent implements OnInit {
  private client!: Client;
  connected: boolean = false;
  messages: Message[] = [];
  dataTVT: any = [];
  dataHVT: any = [];
  
  //ngx options
  view = [700, 300];
  legend: boolean = true;
  showLabels: boolean = true;
  animations: boolean = true;
  xAxis: boolean = true;
  yAxis: boolean = true;
  showYAxisLabel: boolean = true;
  showXAxisLabel: boolean = true;
  xAxisLabelTVT: string = 'Time';
  yAxisLabelTVT: string = 'Temperature';
  yAxisLabelHVT: string = 'Humidity';
  timeline: boolean = true;

  constructor() { }

  ngOnInit(): void {
    this.client = new Client();
    this.client.webSocketFactory = function (){
      return new SockJS("http://localhost:8080/jms-websocket");
    }
    
    this.connect();
    this.client.onConnect = (frame) =>{
      let cmp: Message[] = [];
      console.log('Conected: '+ this.client.connected + ' : ' + frame);
      this.connected = true;
    
      this.client.subscribe('/topic/data', e =>{
        cmp = JSON.parse(e.body) as Message[];
        // if Connection is established after a sensor sends data
        if(this.messages.length === 0 && cmp.length > 2){
          cmp.forEach((e: Message)=>{
            this.messages.push(e);
            this.parseInfo(e);
          })
        }
        // When client is up to date with the sensors
        else if(this.messages.length < cmp.length){
          this.messages.push(cmp[cmp.length-1]);
          this.parseInfo(this.messages[this.messages.length-1]);
          //console.log(this.dataTVT);
        }
      });
      
      this.client.publish({destination:'/app/all-data'});
    }

    this.client.onDisconnect = (frame) =>{
      console.log('Disconnected: ' + !this.client.connected + ' : '+frame);
      this.connected = false;
    }
  }

  parseInfo(messageAux: Message): void{
    let dataAuxTVT: any;
    let dataAuxHVT: any;
    let genDate: string | undefined = messageAux.dateGeneration!.split(".")[0];
    // Temperature vs Time
    dataAuxTVT = {
      name: genDate,
      value: messageAux.temperature
    }

    // Humidity vs Time
    dataAuxHVT = {
      name: genDate,
      value: messageAux.humidity
    }

    let exist: boolean = false;
    this.dataTVT.forEach((e: any)=>{
      if(e.name === messageAux.idDevice?.toString()){
        exist = true;
      }
    })

    if(exist){
      //Temperature vs Time
      this.dataTVT.forEach((element: any)=> {
        if(element.name === messageAux.idDevice?.toString()){
          element.series.push(dataAuxTVT);
        }
      });
      //Humidity vs Time
      this.dataHVT.forEach((element: any) => {
        if(element.name === messageAux.idDevice?.toString()){
          element.series.push(dataAuxHVT);
        }
      });
    }else{
      //Temperature vs Time
      let graphTVTObject = {
        name: messageAux.idDevice?.toString(),
        series:[
          dataAuxTVT
        ]
      }
      //Humidity vs Time
      let graphHVTObjects = {
        name: messageAux.idDevice?.toString(),
        series: [
          dataAuxHVT
        ]
      }

      this.dataHVT.push(graphHVTObjects);
      this.dataTVT.push(graphTVTObject);
    }   

    this.dataHVT = [...this.dataHVT];
    this.dataTVT = [...this.dataTVT]; 

  }

  connect(): void {
    this.client.activate();
  }

  disconnect(): void {
    this.client.deactivate();
  }

}
