// 简历的模型类
export class ResumeInfo {
    constructor(
        private employeeId: number, // 员工ID 

        private cardNo: string, // 员工卡号

        private name: string,

        private depName: string,

        private graduationTime: Date,

        private education: string,

        private lastTime: number,

        private createTime: Date,

        private updateTime: Date,

        private workTime: number,

        private workYear: number,

        private notUpdateDate: number,

        private jobStatus: string, ) { }
}