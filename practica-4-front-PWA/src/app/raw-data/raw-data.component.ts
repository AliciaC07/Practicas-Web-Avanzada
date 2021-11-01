import { Component, OnInit } from '@angular/core';
import Message from '../models/message';
import { Client } from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';

@Component({
  selector: 'app-raw-data',
  templateUrl: './raw-data.component.html',
  styleUrls: ['./raw-data.component.css']
})
export class RawDataComponent implements OnInit {
  private client!: Client;
  messages: Message[] = [];

  constructor() { }

  ngOnInit(): void {
    this.client = new Client();
    this.client.webSocketFactory = function (){
      return new SockJS("http://localhost:8080/jms-websocket");
    }

    this.connect();
    this.client.onConnect = (frame) =>{
      this.client.subscribe('/topic/data', e =>{
        let cmp = JSON.parse(e.body) as Message[];
        if(this.messages.length === 0 && cmp.length > 1){
          cmp.forEach((e:Message)=>{
            e.dateGeneration = e.dateGeneration!.split(".")[0];
          });
          this.messages = cmp;
        }else if(this.messages.length < cmp.length){
          let messageAux = cmp[cmp.length-1];
          messageAux.dateGeneration = messageAux.dateGeneration!.split(".")[0];
          this.messages.push(messageAux);
        }
      });
      this.client.publish({destination:'/app/all-data'});
    }
  }

  connect():void {
    this.client.activate();
  }

  disconnect(): void {
    this.client.deactivate();
  }

}
