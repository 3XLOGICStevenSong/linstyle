import { Component, ViewChild, OnInit } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Http, Headers } from '@angular/http';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';

import { PasswordService } from './password.service';
import { PermissionsService } from '../../common/permissions.service';
import { MenuComponent } from '../../menu/menu.component';

declare var $: any;

declare var sha512: any;

@Component({
  selector: 'password',
  templateUrl: './password.component.html',
  providers: [PasswordService, PermissionsService],

  // providers:[ MenuService ]
})
export class PasswordComponent implements OnInit {

  public password: String;

  public oldPassword: String;

  public confirmPassword: String;

  public permission: Set<String>;

  public btnFlag: Boolean;

  public sepassword: String;

  public seoldPassword: String;

  @ViewChild('passwordForm') passwordForm: NgForm;

  constructor(private router: Router, private passwordService: PasswordService, private permissionsService: PermissionsService) { }

  //menuComponent=new MenuComponent(this.router , null);

  public ngOnInit(): void {
    this.permissionsService.getPermissionList().then(
      res => {
        this.permission = res;

        let permissArray = this.permission;

        this.btnFlag = false;
        //  this.passwordFlag = false;
        //遍历Set 查找权限
        for (let temp in permissArray) {
          if (permissArray[temp] == "password:update") {
            this.btnFlag = true;
            break;
          }
        }

        // this.menuComponent.urlSelect="setting";
        // console.log(this.menuComponent.urlSelect);
        //this.menuComponent.changeSelect("setting");
        // alert(">>>>>>"+this.btnFlag);
      })
  }
  changePassword(): void {
    console.log(this.passwordForm);
    if (!this.passwordForm.valid && this.passwordForm.controls) {
      for (const name in this.passwordForm.controls) {
        if (this.passwordForm.controls.hasOwnProperty(name)) {
          this.passwordForm.controls[name].markAsDirty();
          // alert(">>>>");
        }
      }

    } else if (this.password != this.confirmPassword) {
      // alert(this.password +">>>>"+this.confirmPassword);
      //$("#comPass").css("display","block")//({"display":"block","color":"#f00"})
      document.getElementById("comPass").style.display = "block";
    } else {
      if (this.password == this.confirmPassword) {
        // alert(this.password +">>>>"+this.confirmPassword);
        document.getElementById("comPass").style.display = "none";
      }
      //加密密码
      this.seoldPassword = sha512(this.oldPassword);
      //加密密码
      this.sepassword = sha512(this.password);

      this.passwordService.changePassword(this.seoldPassword, this.sepassword).then(
        res => {
          if (res.code == '0') {
            $.MsgAlert.AlertTure("修改密码", "修改密码成功");
            //location.reload();
          } else {
            $.MsgAlert.AlertTure("修改密码", res.msg);
          }
        }
      )
    }
  }
  cancelPassword(): void {
    //alert("cancel ")
    document.getElementById("comPass").style.display = "none";
  }
}