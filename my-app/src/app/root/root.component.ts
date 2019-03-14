import { Component, OnInit } from '@angular/core';
import { RootService } from './root.service';
import { Role } from './root.role';


import { Router } from '@angular/router';
//import { Menu } from './root.menu';

@Component({
  selector: 'root-root',
  templateUrl: './root.component.html',
  providers:[RootService]
})

export class RootComponent  implements OnInit{ 
  homeSelect:String;

  constructor( private router: Router,private rootService :RootService) { }
 
  ngOnInit(): void {
  //  this.menuService.getMenuList().then(
    //   this.router.params['id'];
    // this.test = this.menuList[0].name;
      // alert(this.menuList[0].name);
   this.homeSelect=this.rootService.secItem;
   // alert(this.router.url) ;
      }
   // )
  
  // // 角色
  // roles: Array<Role>;

  // // 菜单
  // menus: Array<Menu>;

  // 初始化RootService
 // constructor(private rootService: RootService) { }
}
  // // 页面初始化
  // ngOnInit(): void {

  //   // 获取角色
  //   this.rootService.getRoles().then(roles => {
  //        this.roles=roles;   
  //        // alert(this.roles);
  //   });

  //   // 获取菜单
  //   this.rootService.getMenu().then(menus => {
  //       this.menus = menus;
  //       // alert(this.menus);
  //   });

  // }
//}
