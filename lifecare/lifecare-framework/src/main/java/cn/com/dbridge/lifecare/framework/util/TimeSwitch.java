package cn.com.dbridge.lifecare.framework.util;

public class TimeSwitch {
	public static void main(String[] args) {
		String time1 = secondToTime(85959);
		System.out.println(time1);
	}

	/**
	 * 将秒数转换为日时分秒，
	 * 
	 * @param second
	 * @return
	 */
	public static String secondToTime(long second) {
		long days = second / 86400; // 转换天数
		second = second % 86400; // 剩余秒数
		long hours = second / 3600; // 转换小时
		second = second % 3600; // 剩余秒数
		long minutes = second / 60; // 转换分钟
		second = second % 60; // 剩余秒数
		if (days > 0) {
			return days + "天" + hours + "小时" + minutes + "分";
		} else {
			return hours + "小时" + minutes + "分";
		}
	}
}
