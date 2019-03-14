// 招聘职位的列表Model
export class AgreementType {

	public aiId: 			string;  	//协议ID  
	public aiName: 			string;  	//协议名称
	public aiTotal:    		string; 	//协议金额
	public aiPeriod:   		string; 	//协议期限（年）
	public paymentType: 	string; 	//核算方式
	public aiType: 			string; 	//协议类型( 0：赴日:1：其他)
	public status: 			string;		//协议使用状态（0：暂停使用，1：使用中）

}