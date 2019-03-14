import { ActivityFile } from './activity-file';
import { ActivityRegister } from './activity-register';

export class Activity {


	public activeId: Number;

	public typeId: Number;

	public activeTitle: String;

	public activeContent: String;

	public signStartTime: Date;

	public signEndTime: Date;

	public activeStartTime: Date;

	public activeTime: Date[];

	public signTime: Date[];

	public activeEndTime: Date;

	public pushFlag: String;

	public optionItem: String;

	public activeStatusName: String;

	public typeName: String;

	public typeCode: String;

	public activeStatus: String;

	public name: Boolean;

	public sex: Boolean;

	public card: Boolean;

	public tel: Boolean;

	public address: Boolean;

	public activityFileList: Array<ActivityFile>;

	public signFlag: String;

	public activityRegisterList: Array<ActivityRegister>;
}