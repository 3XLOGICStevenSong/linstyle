import { Component } from '@angular/core';
import { Router } from '@angular/router';
declare var jQuery: any;
declare var $: any;
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})

export class AppComponent {

  // title = 'app';

  // firstLogin = true;

  // loginSuccess = false;

  // constructor(
  //   private router: Router) { }

  // logSuc():void {
  //   this.firstLogin = false;
  //   this.loginSuccess = true;
  // }
  constructor(private router: Router) { }
  // 跳转到公司活动
 
}
