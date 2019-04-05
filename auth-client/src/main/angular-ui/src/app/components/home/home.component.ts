import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private http: HttpClient) { }

  greeting = "hey there";

  authentication: Boolean;

  ngOnInit() {
    console.log('loading home page');
    console.log('isAuthenticated? '+this.isAuthenticated());
  }

   helloWorld(){

    this.isAuthenticated().then((resolved)=>{
      this.http.get('/stems/api/hello',{responseType: 'text' }).subscribe(
        (res) => {
          // console.log(resolved);
          console.log(res);
      }, (err: any) => {
        console.log(resolved);
        console.log(err);
        console.log('error occured please login')
      });
     },(rejected)=>{
      console.log('Unauthenticated request');
      console.log('Redirecting to authorizations-server\'s login page');
      // console.log(rejected);
      window.location.href='/stems/login';
     })
  }

   isAuthenticated() {
    const that = this;
    console.log('requesting user endpoint');
    return new Promise(function(resolve,reject){
      that.http.get('/stems/user').subscribe((res: Response) => {
        if (res) {
          console.log(res);
         resolve(res);
        }
      }, (err: any) => {
        console.log(err);
        reject(err);
      });
    })    
  }

}
