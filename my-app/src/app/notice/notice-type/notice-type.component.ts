import { Component, OnInit } from '@angular/core';
import { NoticeTypeService } from './notice-type.service';
import { TypeList } from './notice-type.typeList';
import { Filiter } from './notice-type.filiter';
import { Router } from '@angular/router';

declare var $:any;

@Component({
  selector: 'notice-type',
  templateUrl: './notice-type.component.html',
  styleUrls: [
      '../../../assets/css/bootstrap.min.css',
      '../../../assets/css/mystyle.css',
  		'../../../assets/css/file.css',
  		'../../../assets/css/nav.css'],
  providers:[ NoticeTypeService ]
})

export class NoticeTypeComponent implements OnInit{
  
  // 通知种类
  // typeList: Array<TypeList>;
  // typeList: TypeList[];
  typeList = [new TypeList(1,"一部"),new TypeList(2,"2部"),new TypeList(3,"3部")];

  // 追加的新类型
  typeNew:TypeList;

  // 实例化RecruitListService
  constructor(private noticeTypeService: NoticeTypeService,private router: Router) { }

  // 页面初始化
  ngOnInit(): void {

    // 获取所有
    this.noticeTypeService.getAll().then(result => {
      if(result.code == 0){
        // 请求成功
        this.typeList=result.typeList; 
      } else {
        // 请求失败
        alert(result.code + result.msg);
      }
    });
  }

  //类型追加（通知和活动）
   btnAddRow():void{
      $("table tbody").append("<tr><td></td><td><input type='text' class='form-control col-7' value=''></td><td><button type='button' class='btn btn-primary' style='top:0;'>保存</button></td><td><button type='button' class='btn btn-default' style='top:0;' (click)='delType(typeModel.typeId)'>删除</button></td></tr>");
      
      // 序号重新编号
      var num=$("tbody tr").length;
      for(var i=0;i<num;i++){
        $("tbody tr").eq(i).find("td").eq(0).text(i+1);     
      }

      this.noticeTypeService.addone(this.typeNew).then(tests => {
       // 获取所有
      this.noticeTypeService.getAll().then(result => {
        if(result.code == 0){
          // 请求成功
          this.typeList=result.typeList; 
        } else {
          // 请求失败
          alert(result.code + result.msg);
        }
      });
    });

  }

  // 通知一览删除 弹出提示框
  launchModal(nowTr):void {
    $("#myModal").modal('show'); 
   }

  // 点击删除按钮
  delType(typeId:number):void {

    // 画面上删除一行
    for(var i=0;i<this.typeList.length;i++){
      if(this.typeList[i].typeId == typeId){
        this.typeList.splice(i,1);
      }
    }

    // 请求后台删除
    this.noticeTypeService.delone(typeId).then(result => {
        
        if(result.code == 0){
          // 请求成功
          // 获取所有
          this.noticeTypeService.getAll().then(result => {
            this.typeList=result.typeList;    
          });
        } else {
          // 请求失败
          alert(result.code + result.msg);
        }
    });
  }

  // 点击保存按钮 - 更新
  saveType(typeModel:TypeList):void {
    this.noticeTypeService.updateone(typeModel).then(result => {

        // 重新获取所有
        this.noticeTypeService.getAll().then(result => {
          if(result.code == 0){
            // 请求成功
            this.typeList=result.typeList; 
          } else {
            // 请求失败
            alert(result.code + result.msg); 
          }
        });
      }); 
  }


}
  