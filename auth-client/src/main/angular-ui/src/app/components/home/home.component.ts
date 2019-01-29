import { Component, OnInit } from '@angular/core';
import {HttpClient,HttpHeaders,HttpParams} from '@angular/common/http';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private http:HttpClient) { }

  greeting = "hey there";

  ngOnInit() {
    console.log('loading home page');
    this.http.get('/api/hello').subscribe();
  }

}
