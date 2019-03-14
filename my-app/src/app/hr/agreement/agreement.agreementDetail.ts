
export class AgreementDetail {


	public aiId: number;	 				//协议ID		

	public aiName: string;	 				//协议名称		

	public aiType: string;					//协议类型    0：赴日:1：其他		

	public aiPeriod: string;				//协议期限（年）		

	public paymentType: string; 			//核算方式	

	public employeeId: number;				//签协议人ID	

	public codeNo: string;					//签协议人员工号

	public employeeName: string;			//签协议人姓名

	public signLevel: string;				//签协议时级别

	public businessDays: number;			//赴日天数

	public subTotal: number;				//补助金额

	public jAgreementTotal: number; 		//协议金额（日元）

	public jPaymentTotal: number;			//缴纳费用（日元）

	public cAgreementTotal: number;			//协议金额（人民币）

	public cPaymentTotal: number;			//缴纳费用（人民币）

	public jRewardTotal: number;			//奖励金额（日元）

	public cRewardTotal: number;			//奖励金额（人民币）

	public remainingDays: number;			//协议剩余天数

	public signDate: string;				//协议签订日期

	public status: string;					//状态 0:未结束，1:已结束

	public startDate: string;				//协议开始日期

	public endDate: string;				//协议结束日期

	public goAbroadDate: string;			//出国日期

	public backAbroadDate: string;    		//回国日期

	public createCardNo: string;			//创建人员工号

	public createTime: string;				//创建时间

	public updateCardNo: string;			//更改人员工号

	public updateTime: string;				//更新时间

	public planEndDate: string;         	 //结束日期
}