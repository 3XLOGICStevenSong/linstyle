import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HomeService } from './home.service';
import { Home } from './home';
import { RootService } from '../root/root.service';
declare var $: any;

@Component({
  selector: 'home-root',
  templateUrl: './home.component.html',
  providers: [HomeService]
})

export class HomeComponent implements OnInit {

  noticeList: Array<Home>;

  count: String;

  constructor(
    private router: Router, private homeService: HomeService, private rootService: RootService) { }

  // 跳转到公司活动
  goToAct(): void {
    //  this.router.navigate(['menu/noticeType']);
  }
  forwardRoot(item: String): void {

    this.router.navigate([item]);
    // alert(this.router.url) ;
  }
  gotoRoot(item: String): void {
    this.router.navigate(['root/:item']);

    // let id = this.route.snapshot.paramMap.get('id');
    // let idd = Number(id);
  }

  // 页面初始化
  ngOnInit(): void {

    // 获取通知信息
    this.homeService.getHomeNoticeInfo().then(notice => {
      if (notice.code == '0') {
        this.noticeList = notice.noticeList;
        this.count=notice.count;
      } else {
        //error
      }
    });
  }
}
