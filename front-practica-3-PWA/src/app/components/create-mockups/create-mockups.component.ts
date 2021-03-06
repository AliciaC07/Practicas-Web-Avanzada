import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
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
  headers = false;
  mock: Mock = new Mock();
  contentTypes: ContentType[] = [];
  httpStatusCodes: HttpStatusCode[] = [];
  @ViewChild('headers', {static: true}) header!: ElementRef;
  constructor(private mockService: CreateMockupsService, private authService: LoginService, private router:Router, 
    private activatedRoute: ActivatedRoute, private translateService: TranslateService) { }

  ngOnInit(): void {

    this.isJson();

    this.activatedRoute.params.subscribe(params =>{
      let id = params['id'];
      if(id){
        this.mockService.getMock(id).subscribe(response =>{
          this.mock = response;
          this.header.nativeElement.value = JSON.stringify(this.mock.headers, null, '\t').replace(/[\\]/g,'').replace(/\["/g,'"')
            .replace(/"\]/g,'"').replace(/"{/g,'{').replace(/}"/g,'}');
          this.mock.headers = [];
          this.headers = true;
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

  selectLanguage(event: any){
    this.translateService.use(event.target.value);
  }

  create(){
    this.mock.user = this.authService.user.id!;
    JSON.parse(this.header.nativeElement.value!,(key,value) =>{
      if(key != ""){
        let header = new Header();
        header.keyHeader = key;
        header.value = value;

        this.mock.headers.push(header);
      }
    })
    console.log(this.mock);
    this.mockService.create(this.mock).subscribe(response =>{
      this.router.navigate(['dashboard']);
    });
  }

  update(){
    JSON.parse(this.header.nativeElement.value!,(key,value) =>{
      if(key != ""){
        let header = new Header();
        header.keyHeader = key;
        header.value = value;

        this.mock.headers.push(header);
      }
    })
    this.mockService.updateMock(this.mock).subscribe(response =>{
      this.router.navigate(['dashboard']);
    })
  }
  isJson(){
    let item = this.header.nativeElement.value;
    item = typeof item !== "string"
        ? JSON.stringify(item)
        : item;

    try {
        item = JSON.parse(item);
    } catch (e) {
        this.headers = false;
    }

    if (typeof item === "object" && item !== null) {
        this.headers = true;
    }else{
      this.headers = false;
    }
    if(this.header.nativeElement.value==='' || this.header.nativeElement.value===null){
      this.headers = true;
    }
  }

  isHeader(): boolean {
    return this.headers;
  }

}
