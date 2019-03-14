// 招聘职位的列表Model
export class PositionList {
	// 1.positionId(int):职位ID
    public positionId: number;
    // 2.languageId(int):语种 (中文：0，日文：1)
    public languageId: number;
    // 3.title(string):标题
    public title : string;
    // 4.publishTime(string):发布时间（yyyyMMhh hh:mm:ss）]
	public publishTime: string; 
}