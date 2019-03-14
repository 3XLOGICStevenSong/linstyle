
export class AddAgreement {


	public aiId: number;	 				//协议ID		

	public aiName: string;	 				//协议名称		

	public aiType: string;					//协议类型    0：赴日:1：其他		

	public aiPeriod: string;				//协议期限（年）		

	public  cAgreementTotal: string; 		//协议金额
	
	public  signDate: string;				//协议签订日期
	
	public  goAbroadDate: string;			//赴日日期(yyyyMMhh)
	
	public  backAbroadDate: string;			//回国日期(yyyyMMhh)
	
	public  startDate: string;				//协议开始日期(yyyyMMhh)
	
	public  endDate: string;				//协议结束日期(yyyyMMhh)
	
	public  subTotal: string;				//补助金额（日元/天）

	public  employeeList: string[]; 		//员工Id,员工号
}