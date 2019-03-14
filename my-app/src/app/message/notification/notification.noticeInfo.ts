import { NoticeFile} from './notification.noticeFile';

export class NoticeInfo { 

    public  btnStatus: number;      //按钮的显示状态  0不显示，1 显示删除， 2显示审核

	public  noticeId: number;       // 通知ID
  
    public  typeId: number;         // 通知类型
  
    public  typeName: String;       // 通知类型名称
  
    public  title: String;          // 标题
  
    public  content: String;        // 内容

    public  appendixName:String;    //附件名称
  
    public  statusFlag: String;     // 发布状态 0：未删除1：删除
  
    public  importantFlg: String;   // 是否置顶 0:不置顶1：置顶
  
    public  pushFlg: String;        // 是否推送 0：不推送1：推送
  
    public  acceptFlg: String;      // 审核标识 0：未审核 1：审核通过 2:审核未通过（分角色）

    public  acceptFlgStr: String;      // 审核标识 0：未审核 1：审核通过 2:审核未通过（分角色）
  
    public  readLevel: String;      // 浏览级别 0：公司内 ,1：项目内 ,2：部门内,3：项目经理,4：部门经理,5：总经理
  
    public  createCardNo: String;   // 发布人员工号
  
    public  createTime: String;     // 创建时间
  
    public  updateCardNo: String;   // 更改人员工号
  
    public  updateTime: String;     // 更新时间
  
    public  notificationFile: Array<NoticeFile>; // 通知文件列表
  
    public  startDate: String       // 开始日期
  
    public  endDate: String;        // 结束日期
  
    public  creater: String;        // 创建人
  
    public  updater: String;        // 更新人
 

}