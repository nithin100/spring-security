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
    if (this.isAuthenticated()) {
      this.http.get('/stems/api/hello').subscribe((res: Response) => {
        if (res) {
          console.log(res);
        }
      }, (err: Response) => {
        console.log('error occured please login')
      });
    } else {
      console.log('Unauthenticated request');
      console.log('Uncomment the below code for handling redirections');
      //window.location.href='/login';
    }
  }

  isAuthenticated(): Boolean {
    const that = this;
    this.http.get('/stems/user').subscribe((res: Response) => {
      if (res) {
        that.authentication = true;
      }
    }, (err: Response) => {
      that.authentication = false;
    });

    return that.authentication;
  }

}
