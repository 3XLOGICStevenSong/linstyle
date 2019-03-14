import { Component, OnInit } from '@angular/core';
import { HeadService } from './head.service';
import { User } from './user';
import { Router } from '@angular/router';
declare var $:any;
@Component({
  selector: 'head-top',
  templateUrl: './head.component.html',
  providers: [HeadService]
})

export class HeadComponent implements OnInit {

  // 当前用户
  // user: User;
  loginCode: string;
  //员工姓名
  userName: string;
  //部门名称
  depName: string;

  public urlSelect: String;
  // 初始化RootService
  constructor(private headtService: HeadService, private router: Router) { }

  // 页面初始化
  ngOnInit(): void {
    // // 获取登录用户信息
    this.headtService.getCurrentUser().then(user => {
      this.loginCode = user.loginCode;
      this.depName = user.depName;
      this.userName = user.userName
    });
  }
  public loginOut() {

    this.headtService.loginOut().then(res => {
      location.reload();
    }
    );
  }
  home(): void {
    this.router.navigate(['home']);
  }
  changeps(): void {
    this.router.navigate(['setting/password']);
  }

  //////
  info():void{
    this.router.navigate(['personnel/employee/info']);
    var menuName="我的个人信息";
    for(var i=0;i<$(".menu_psw .mList").length;i++){
      var $subUl= $(".menu_psw .mList").eq(i).find(".submenu");
      for(var k=0;k<$subUl.find("a").length;k++){
        var name=$subUl.find("a").eq(k).text();
        if(name == menuName){
          $subUl.find("a").eq(k).parent().trigger("click");
        }
      }
    }
  }

  psw():void{
    this.router.navigate(['setting/password']);

    var menuName="修改密码";

    for(var i=0;i<$(".menu_psw .mList").length;i++){
      var $subUl= $(".menu_psw .mList").eq(i).find(".submenu");
      for(var k=0;k<$subUl.find("a").length;k++){
        var name=$subUl.find("a").eq(k).text();
        if(name == menuName){
          $subUl.find("a").eq(k).parent().trigger("click");
        }
      }
    }

    // $(".menu_psw li").removeClass("active");
    // $(".menu_psw li").removeAttr("ng-reflect-class-name");
    
    // var menuName = "系统设置";
    // console.log("length = "+$(".menu_psw li").length);
    // for(var i=0;i< $(".menu_psw li").length;i++){
    //   if($(".menu_psw li").eq(i).find("p").text()==menuName){
    //     console.log(".eq(i).text()==menuName");
    //     $(".menu_psw li").eq(i).addClass("active");
    //     // $(".menu_psw li").eq(i).attr("ng-reflect-class-name","active");
    //   }
    // }
    
  }
}
