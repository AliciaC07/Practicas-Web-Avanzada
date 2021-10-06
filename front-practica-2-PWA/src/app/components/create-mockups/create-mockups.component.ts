import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import Mock from '../dashboard/models/Mock';
import { LoginService } from '../login/login.service';
import { CreateMockupsService } from './create-mockups.service';
import ContentType from './models/ContentType';
import Header from './models/Header';
import HttpStatusCode from './models/HttpStatusCode';

@Component({
  selector: 'app-create-mockups',
  templateUrl: './create-mockups.component.html',
  styleUrls: ['./create-mockups.component.css']
})
export class CreateMockupsComponent implements OnInit {

  mock: Mock = new Mock();
  contentTypes: ContentType[] = [];
  httpStatusCodes: HttpStatusCode[] = [];
  @ViewChild('headers', {static: true}) header!: ElementRef;
  constructor(private mockService: CreateMockupsService, private authService: LoginService, private router:Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {

    this.activatedRoute.params.subscribe(params =>{
      let id = params['id'];
      if(id){
        this.mockService.getMock(id).subscribe(response =>{
          this.mock = response;
          this.header.nativeElement.value = JSON.stringify(this.mock.headers, null, '\t').replace(/[\\]/g,'').replace(/\["/g,'"')
            .replace(/"\]/g,'"').replace(/"{/g,'{').replace(/}"/g,'}');
          this.mock.headers = [];
        })
      }
    })

    this.mockService.getContentTypes().subscribe( response =>{
      this.contentTypes = response;
    })

    this.mockService.getStatusCodes().subscribe(response => {
      this.httpStatusCodes = response;
    })
  }

  create(){
    this.mock.user = this.authService.user.id!;
    JSON.parse(this.header.nativeElement.value!,(key,value) =>{
      if(key != ""){
        let header = new Header();
        header.key = key;
        header.value = value;

        this.mock.headers.push(header);
      }
    })
    this.mockService.create(this.mock).subscribe(response =>{
      this.router.navigate(['dashboard']);
    });
  }

  update(){
    JSON.parse(this.header.nativeElement.value!,(key,value) =>{
      if(key != ""){
        let header = new Header();
        header.key = key;
        header.value = value;

        this.mock.headers.push(header);
      }
    })
    this.mockService.updateMock(this.mock).subscribe(response =>{
      this.router.navigate(['dashboard']);
    })
  }

}
