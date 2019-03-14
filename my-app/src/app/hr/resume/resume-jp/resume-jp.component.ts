import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';

declare var $: any;

@Component({
  selector: 'resume-jp',
  templateUrl: './resume-jp.component.html',
  styleUrls: [
    './resume-jp.component.css'],
  providers: []
})

export class ResumeJpComponent implements OnInit {

  // 实例化RecruitListService
  constructor(
    private route: ActivatedRoute,
    private router: Router) { }

  // 页面初始化
  ngOnInit(): void {
    
    // UI初始化
    this.showInit();
  }

  // UI

  // 页面初始化
  showInit(): void {
    $(".personInput").hide();
    // $(".personDetail").hide();
    $(".personPrompt").hide();
    $(".updateBtn").click(function () {
      $(this).hide();
      $(this).parents(".info").find(".personPrompt").hide();
      $(this).parents(".info").find(".personDetail").hide();
      $(this).parents(".info").find(".personInput").show();
      $(".updateBtn").attr("disabled", "disabled");
    });
    $(".saveBtn").click(function () {
      $(this).parents(".info").find(".personDetail").show();
      $(this).parents(".info").find(".updateBtn").show();
      $(this).parents(".info").find(".personInput").hide();
      $(".updateBtn").removeAttr("disabled");
    });
    $(".cancelBtn").click(function () {
      $(this).parents(".info").find(".personPrompt").show();
      $(this).parents(".info").find(".updateBtn").show();
      $(this).parents(".info").find(".personInput").hide();
      $(".updateBtn").removeAttr("disabled");
    });
  }
}
