import { Component, OnInit } from '@angular/core';
import { RecruitUpDataService } from './recruit-updata.service';
import { PositionList } from '../recruit-input/recruit-input.positionList';
import { Interviewer } from '../recruit-detail/recruit-detail.interviewer';
import { Paper } from '../recruit-detail/recruit-detail.paper';
import { Filiter } from '../recruit-input/recruit-input.filiter';
import { RecruitDetailService } from '../recruit-detail/recruit-detail.service';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';

declare var $:any;

@Component({
  selector: 'recruitlist-updata',
  templateUrl: './recruit-updata.component.html',
  styleUrls: [
      '../../../../assets/css/bootstrap.min.css',
  		'../../../../assets/css/file.css',
  		'../../../../assets/css/mystyle.css',
      '../../../../assets/css/popup.css',
  		'../../../../assets/css/nav.css'],  

  providers:[ RecruitUpDataService,RecruitDetailService ]
})

export class RecruitUpdataComponent implements OnInit{

  // 选择框 - 语言筛选
  lans = ['全部','日语','中文'];

  // 接口返回参数  
  //   1.positionId(int):招聘职位ID
  recruitId:number;
  //   2.languageId(int):语种 (中文：0，日文：1)
  language:string;
  //   3.title(string):标题
  title:string;
  //   4.content(string):发布内容
  content:string;
  //   5.paperList:
  //   [
  //    1.paperId(int)：考卷Id  
  //    2.paperName(string)：考卷名称
  //    3.paperUrl(string):考卷下载链接
  //   ]
  paperList:Array<Paper>;
  //   6.interviewerList:
  //   [
  //    1.userId(int):用户Id 
  //    2.userName（string）：姓名
  //   ]
  empList:Array<Interviewer>;

  // 实例化RecruitListService
  constructor(private route: ActivatedRoute,
    private router: Router,private recruitUpDataService: RecruitUpDataService,
    private recruitDetailService: RecruitDetailService) { }

  // 页面初始化
  ngOnInit(): void {

    // 跳转来的招聘id
    let id = this.route.snapshot.paramMap.get('id');
    let idd = Number(id);
    this.recruitId = idd;
    
    // 获取详情
    this.recruitDetailService.getAll(idd).then(result => {
      if(result.code == 0){   

      // 通过id判断中文和日语
      if(result.language == "0"){
        this.language = "中文";
      } else if(result.language == "1"){
        this.language = "日语";
      } else{}

      // 赋值
      this.title = result.title;
      this.content = result.content;
      this.paperList = result.paperList; 
      this.empList = result.recruitEmployeeList;

      // 显示paperList和empList
      if(this.paperList.length >= 1){

          $("#paperback").html("");
          for(let i=0;i<this.paperList.length;i++){
            $("#paperback_3").find("#ppName").html(this.paperList[i].paperName);
            $("#paperback").append($("#paperback_3").html());
          }

          $("#empback").html("");
          for(let i=0;i<this.empList.length;i++){
            $("#empback_2").find("#empName").html(this.empList[i].name);
            $("#empback").append($("#empback_2").html());
          }

      }

      } else {
        // 请求失败
        alert(result.code + result.msg);
      }
    });
  }

  // 增加考卷
  addPaper():void {
    // 添加
    $("#paperback").append($("#paperback_1").html());
    $(".delPaper").click(function(){
      if($(this).parent().parent().parent().children().length > 1){
        $(this).parent().parent().remove();
      }
    });
  }

  // 添加面试官
  addTea():void {
    // 添加
    $("#empback").append($("#empback_1").html());

    // 删除面试官
    $(".delTea").click(function(){
      if($(this).parent().parent().parent().children().length > 1){
        $(this).parent().parent().remove();
      }
    });
  }

  // 修改信息
  submitInfo():void{

    if(this.language == "中文"){
      this.language = "0";
    } else if(this.language == "日语"){
      this.language = "1";
    } else {
      this.language = null;
    }

    // 赋值给 this.paperList,this.empList
    // $("#chosePaper").each(function(index,item){
    //   alert(item.html());
    // });
    
    // 网络请求
    this.recruitUpDataService.updataRecruitment(this.recruitId,this.language,this.title,this.content,this.paperList,this.empList).then(result => {
      alert(result.code + result.msg);
    });
  }

  // 选择面试官
  selTea():void{
    $("#paperInput").modal('show');
  }

  // 获取所有考卷
 
  // 获取所有面试官

}
  