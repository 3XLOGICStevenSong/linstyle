import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';

declare var $: any;

@Component({
  selector: 'resume-search',
  templateUrl: './resume-search.component.html',
  styleUrls: [
    './resume-search.component.css'],
  providers: []
})

export class ResumeSearchComponent implements OnInit {

  // 实例化RecruitListService
  constructor(
    private route: ActivatedRoute,
    private router: Router) { }

  // 页面初始化
  ngOnInit(): void {
    

  }

  
}
