// 面试详情Model
import {ScoreInfo} from './interview-detail.scoreInfo'
import {RecruitEmployee} from './interview-detail.recruitEmployee'
export class InterviewDetail {
    constructor(
        public interId: number,
        public interTime:Date,
        public interName: string,
        public recommendName: string,
        public recruitId: number,
        public resumeName: string,
        public resumeUrl: string,
        public title: string,
        public employeeNames: string,
        public interDate: Date,
        public interDateStr: string,
        public interResult: string,
        public acceptJapan: string,
        public assistanceCompany: string,
        public assistanceFlag: string,
        public bindFlag: number,
        public cardNo: string,
        public code: string,
        public compamyCode: string,
        public condition: string,
        public email: string,
        public emailFlag: string,
        public goodsSkills: string,
        public interAddress: string,
        public interSex: string,
        public interTel: string,
        public japanExp: string,
        public japanTimes: string,
        public status: string,
        public teamFlag: string,
        public teamSize: string,
        public telFlag: string,
        public part: number,
        public scoreInfos: Array<ScoreInfo>,
        public recruitEmployees: Array<RecruitEmployee>
    ) { }
}