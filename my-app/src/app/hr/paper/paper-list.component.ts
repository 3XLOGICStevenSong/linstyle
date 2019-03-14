import { PaperListService } from './paper-list.service';
import { Paper } from './paper-list.paper';
import { Router } from '@angular/router';
import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { FileUploader } from 'ng2-file-upload';

declare var $: any;
@Component({
  selector: 'paperlist-root',
  templateUrl: './paper-list.component.html',
  styleUrls: [
    './paper-list.component.css',
    '../../../assets/css/file.css',
    '../../../assets/css/mystyle.css',
    '../../../assets/css/nav.css'],
  providers: [PaperListService]
})

export class PaperListComponent implements OnInit {

  // 筛选的关键字
  searchKey: string;
  // 列表显示的模型类
  paperList: Array<Paper>;

  paperNameCommit: string;

  paperIntroCommit: string;

  fileName: string = "未选择任何文件";

  paperId: number;
  // 实例化 PaperListService
  constructor(private paperListService: PaperListService, private router: Router) { }

  // 点击考卷上传按钮
  launchPaperInput() {
    // 弹出考卷上传提示框
    $("#paperInput").modal('show');
    // 提交考卷弹出框的确定按钮的点击事件
    $(".commitPaperOkBtn").click(function () {

    })
  }

  // 检索按钮的点击事件
  searchOnClicked() {
    this.getAllPapers();
  }

  // 页面初始化
  ngOnInit(): void {
    this.getAllPapers();
  }

  // 获取所有考卷
  public getAllPapers(): void {
    // 获取所有考卷
    if (this.searchKey == null || this.searchKey.length == 0) {
      this.searchKey = "";
    }
    this.paperListService.getAllPapers(this.searchKey).then(result => {
      if (result.code == 0) {
        // 请求成功
        this.paperList = result.paperList;
      } else {
        // 请求失败
        $.MsgAlert.AlertTure("考卷一览", result.msg);
      }
    });
  }

  // 下载
  public onDown(paperUrl: string): void {
    window.open(paperUrl);
  }

  // 删除
  public onDel(paperId: number): void {
    // 确定删除提示框
    $("#myModal").modal('show');

    // 确定删除
    this.paperId = paperId;
  }

  // 删除 确定按钮点击事件
  paperDel(): void {
    var that = this;
    this.paperListService.delone(this.paperId).then(result => {
      if (result.code == 0) {
        // 数据源删掉
        for (var i = 0; i < that.paperList.length; i++) {
          if (that.paperList[i].paperId == that.paperId) {
            that.paperList.splice(i, 1);
          }
        }
        $.MsgAlert.AlertTure("删除考卷", "删除考卷成功");
        // 隐藏删除提示框
        $("#myModal").modal('hide');
      } else {
        // 请求失败
        $.MsgAlert.AlertTure("删除考卷", result.msg);
      }
    });
  }

  // 上传试卷
  paper: Paper = {
    paperId: 0,
    paperName: this.paperNameCommit,
    paperIntroduce: this.paperIntroCommit,
    paperUrl: "",
    paperScore: ""
  }
  public uploader: FileUploader = new FileUploader({
    url: "~resource/examPaperUpload",
    method: "POST",
    itemAlias: "file",
    additionalParameter: this.paper
  });

  refreshInput(): void {
    this.uploader.clearQueue();
  }

  selectChange(): void {
    var files = this.uploader.queue;
    console.log(this.fileName);
    var allName = "";
    if (files && files.length > 0) {
      for (var i = 0; i < files.length; i++) {
        if (i == files.length - 1) {
          allName += files[i].file.name;
        } else {
          allName += files[i].file.name + "|";
        }
      }
      this.fileName = allName;
    }
  }
  // C: 定义事件，选择文件
  selectedFileOnChanged(event: any) {
    try {
      this.paper.paperName = this.paperNameCommit;
      this.paper.paperIntroduce = this.paperIntroCommit;
      this.uploader.queue[this.uploader.queue.length - 1]._file;
  
      // 打印文件选择名称
      this.uploadFile();
    } catch (err) {

    }
  }
  // D: 定义事件，上传文件
  uploadFile() {
    var that = this;
    // 上传
    this.uploader.queue[this.uploader.queue.length - 1].onSuccess = function (response, status, headers) {
      //上传文件后获取服务器返回的数据
      let tempRes = JSON.parse(response);
      // 上传文件成功
      if (status == 200) {
        // 弹出考卷上传提示框
        $.MsgAlert.AlertTure("考卷上传", "考卷上传成功");
        $("#paperInput").modal('hide');
        that.getAllPapers();
      } else {
        // 上传文件后获取服务器返回的数据错误
        $.MsgAlert.AlertTure("考卷上传", "考卷上传失败");
      }
    };
    this.uploader.uploadAll(); // 开始上传
  }
}
