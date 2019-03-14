import { Component, OnInit } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Http, Headers } from '@angular/http';
import { StationMessageService } from './station-message.service';
import { StationMessage } from './station-message';
import { Router } from '@angular/router';

declare var $: any;

@Component({
  selector: 'station-message',
  templateUrl: './station-message.component.html',

  providers: [StationMessageService]
})
export class StationMessageComponent implements OnInit {

  public messageList: Array<StationMessage>;

  constructor(private router: Router, private stationMessageService: StationMessageService) { }


  // 页面初始化
  ngOnInit(): void {


    // 获取通知信息
    this.stationMessageService.getStationMessageList().then(res => {
      if (res.code == '0') {
        this.messageList = res.messageList;
      } else {
        //error
      }
    });
  }
}
