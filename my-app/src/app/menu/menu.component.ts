import { Component, OnInit } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Http, Headers } from '@angular/http';
import { MenuService } from './menu.servce';
import { Menu } from './menu';
import { Router } from '@angular/router';

declare var $: any;

@Component({
  selector: 'menuList',
  templateUrl: './menu.component.html',

  providers: [MenuService]
})
export class MenuComponent implements OnInit {

  public urlSelect: String ;


  private menuList: Array<Menu>;
  // private test  : number;
  constructor(private router: Router, private menuService: MenuService) { }

  ngOnInit(): void {
    this.menuService.getMenuList().then(
      res => {
      
       // if(res.code=='0'){
        // alert(">>>>>>"+Response.prototype);
        // console.log(Response.toString)
        this.menuList = res;
      //}
        // this.test=0;
        // this.test = this.menuList[0].name;
        // alert(this.menuList[0].name);
      }
    )
    var routerUrl = this.router.url;
    var urlArray = new Array();
    urlArray = routerUrl.split('/');
    this.urlSelect = urlArray[1];
     // alert(this.urlSelect);
  }
  
  public changeSelect(selectItem: String): void {
    // $(".menu_psw li").removeClass("active");
    // $(".menu_psw li").removeAttr("ng-reflect-class-name");
    
    this.urlSelect = selectItem;
  }

  // getMyStyle(i: number): String {
  //   const delay = i * 50;
  //   return `transition-delay: ${delay}ms;`;
  // }

    getMyStyle(i: number): string {
     const delay = i * 50;
    return `${delay}`;
   }
}