import { MenuChildren } from './childmenu';

export class Menu {

  public  id : String;
	
	public name : String;
	
	public  url : String;

  public showGroup:String;

	public  children? : Array<MenuChildren>;

	 public code:String;

 public msg:String;
}