import { analyzeAndValidateNgModules } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import Message from '../models/message';
import { Client } from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import { prepareEventListenerParameters } from '@angular/compiler/src/render3/view/template';

@Component({
  selector: 'app-graphs',
  templateUrl: './graphs.component.html',
  styleUrls: ['./graphs.component.css']
})
export class GraphsComponent implements OnInit {
  private client!: Client;
  connected: boolean = false;
  messages: Message[] = []
  data: any = [];

  constructor() { }

  ngOnInit(): void {
    this.client = new Client();
    this.client.webSocketFactory = function (){
      return new SockJS("http://localhost:8080/jms-websocket");
    }
    this.client.onConnect = (frame) =>{
      console.log('Conected: '+ this.client.connected + ' : ' + frame);
      this.connected = true;
      console.log('trying to subscribe');
      this.client.subscribe('/topic/data', e =>{
        console.log(e.body);
      });
      //this.client.publish({destination:'/app/all-data'});
    }

    this.client.onDisconnect = (frame) =>{
      console.log('Disconnected: ' + !this.client.connected + ' : '+frame);
      this.connected = false;
    }
  }

  connect(): void {
    this.client.activate();
  }

  disconnect(): void {
    this.client.deactivate();
  }

}
