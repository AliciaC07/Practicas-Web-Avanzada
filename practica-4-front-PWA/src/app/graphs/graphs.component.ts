import { analyzeAndValidateNgModules } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import Message from '../models/message';
import { Client } from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';

@Component({
  selector: 'app-graphs',
  templateUrl: './graphs.component.html',
  styleUrls: ['./graphs.component.css']
})
export class GraphsComponent implements OnInit {
  private client!: Client;
  messages: Message[] = []
  data: any = [];

  constructor() { }

  ngOnInit(): void {
    this.client = new Client();
    this.client.webSocketFactory = function (){
      return new SockJS("http://localhost:8080/");
    }
  }

}
