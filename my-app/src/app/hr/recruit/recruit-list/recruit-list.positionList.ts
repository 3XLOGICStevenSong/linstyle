// 招聘职位的列表Model
export class PositionList {

    // 1.recruitId(int):职位ID
    // 2.language(string):语种 (中文：0，日文：1)
    // 3.title(string):标题 
    // 4.createTime(string):发布时间（yyyyMMhh hh:mm:ss）

    public recruitId: number;
    public language: string;
    public title : string;
	public createTime: string; 
}