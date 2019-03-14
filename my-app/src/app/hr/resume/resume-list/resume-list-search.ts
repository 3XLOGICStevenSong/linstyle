// 简历一栏的搜索模型类
export class ResumeSearchInfo {
    constructor(
        public depName: string,
        public jobStatus: string,
        public cardNo: string,
        public name: string,
        public pageNum: number,
        public pageSize: number) { }
}