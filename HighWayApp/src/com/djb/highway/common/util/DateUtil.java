/*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 *
 * System Name        : ユーティリティー
 * Description        : 日付のフォーマットを変換する。
 *
 *++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

package com.djb.highway.common.util;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <H3>日付のフォーマットを変換するユーティリティークラス.</H3>
 * 
 * <PRE>
 * </PRE>
 * 
 * @author Wangjiquan
 */
public class DateUtil {
	/** 画面に表示するための日付パターン */
	public static final String PATTERN_PRINT_SCREEN = "yyyy-M-d";

	/** DBに登録するための日付パターン */
	public static final String PATTERN_DB_REGIST_DATE = "yyyyMMdd";

	/** DBに登録するための日付パターン */
	public static final String PATTERN_DB_REGIST_DATE6 = "yyMMdd";

	/** DBに登録するための時刻パターン */
	public static final String PATTERN_DB_REGIST_TIME = "HHmmss";

	/** DBに登録するための日付パターン */
	public static final String PATTERN_DB_REGIST_DATE_YYYYMM = "yyyyMM";

	/** DBに登録するための日付パターン（２０年の補充） */
	public static final String PATTERN_DB_REGIST_DATE_20YYMMDD = "20";

	/** DBに登録するための日付パターン（１９年の補充） */
	public static final String PATTERN_DB_REGIST_DATE_19YYMMDD = "19";

	/** DBに登録するための日付パターン */
	public static final String PATTERN_DB_REGIST_DATE_YYMM00 = "00";

	/** DBに登録するための日付パターン */
	public static final String PATTERN_DATE_YYMM01 = "01";

	/** 日終了位置 */
	private static final int DAY_END = 8;
	/** 時終了位置 */
	private static final int HOUR_END = 10;
	/** 分終了位置 */
	private static final int MINUTE_END = 12;
	/** 秒終了位置 */
	private static final int SECOND_END = 14;
	/**
	 * 61 = 1961 60 = 1960 59 = 2059
	 */
	public static final int PATTERN_YEARKB = 60;

	/**
	 * <H3>指定されたパターンで現在のシステム日付を取得する.</H3>
	 * 
	 * <PRE>
	 * 
	 * パターンについては、SimpleDateFormatクラスを参照のこと.
	 * 
	 * </PRE>
	 * 
	 * @param strPattern
	 *            パターン（例：yyyyMMdd
	 * @return 現在日付
	 * @see SimpleDateFormat
	 */
	public static String nowDate(String strPattern) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strPattern);
		return simpleDateFormat.format(new Date());
	}

	/**
	 * 
	 * <H3>画面に表示する形で現在のシステム日付を取得する.</H3>
	 * 
	 * <PRE>
	 * 
	 * 2010/7/25日現在、yyyy-M-d
	 * 
	 * </PRE>
	 * 
	 * @return 画面に表示する形にフォーマットされたシステム日付
	 */
	public static String nowDatePrintScreen() {
		return nowDate(PATTERN_PRINT_SCREEN);
	}

	/**
	 * <H3>指定されたパターンで現在のシステム日付を取得する.</H3>
	 * 
	 * <PRE>
	 * 
	 * 数値として日付を返す. パターンについては、SimpleDateFormatクラスを参照のこと.
	 * 
	 * </PRE>
	 * 
	 * @param strPattern
	 *            パターン（例：yyyyMMdd
	 * @return int 現在日付
	 * @see SimpleDateFormat
	 */
	public static int nowDateInt(String strPattern) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strPattern);
		return Integer.parseInt(simpleDateFormat.format(new Date()));
	}

	/**
	 * <H3>指定されたパターンで現在の時刻を取得する(HHmmss)</H3>
	 * 
	 * <PRE>
	 * 
	 * 数値として時刻を返す. パターンについては、SimpleDateFormatクラスを参照のこと.
	 * 
	 * </PRE>
	 * 
	 * @param strPattern
	 *            パターン（例：HHmmss
	 * @return int 現在時刻
	 * @see SimpleDateFormat
	 */
	public static int nowTimeInt(String strPattern) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strPattern);
		return Integer.parseInt(simpleDateFormat.format(new Date()));
	}

	/**
	 * <H3>システム日付ミリ秒.</H3>
	 * 
	 * <PRE>
	 * 
	 * 1970 年１月１日 00:00:00 GMT からのミリ秒数を返します。
	 * 
	 * </PRE>
	 * 
	 * @return long ミリ秒
	 */
	public static long nowTime() {
		Date date = new Date();
		return date.getTime();
	}

	/**
	 * <H3>数値（YYYYMMDD）を日付形式(YYYY/MM/DD)へ変換する</H3>
	 * 
	 * @param intDate
	 *            変換する数値
	 * @return String 変換後の日付形式の文字列
	 */
	public static String Num2Date(int intDate) {

		if (intDate == 0) {
			return "";
		}

		StringBuffer stbDate = new StringBuffer(Integer.toString(intDate));
		if (stbDate.length() == 8) {
			stbDate.insert(4, "/");
			stbDate.insert(7, "/");
		} else {
			return Integer.toString(intDate);
		}
		return stbDate.toString();
	}

	/**
	 * <H3>文字列（YYYYMMDD）を日付形式(YYYY/MM/DD)へ変換する</H3>
	 * 
	 * @param strDate
	 *            変換する文字列
	 * @return String 変換後の日付形式の文字列
	 */
	public static String Str2Date(String strDate) {

		if (strDate != null && strDate.equals("0")) {
			return "";
		}

		StringBuffer stbDate = new StringBuffer(strDate);
		if (stbDate.length() == 8) {
			stbDate.insert(4, "/");
			stbDate.insert(7, "/");
		} else {
			return strDate;
		}
		return stbDate.toString();
	}

	/**
	 * 日付形式（YYYY/MM/DD、YYYY/M/DD、YYYY/MM/D、YYYY/M/D）を数値（YYYYMMDD）へ変換
	 * 
	 * @param strDate
	 *            変換したいデータ
	 * @return int 変換されたデータ
	 */
	public static int Date2Num(String strDate) {

		StringBuffer strbuffDate = new StringBuffer();
		StringBuffer strbuffInputDate = new StringBuffer(strDate + "/");

		// 8桁無ければ無条件にエラー
		if (strbuffInputDate.length() < 8) {
			return 0;
		}
		if (!strbuffInputDate.substring(4, 5).equals("/")) {
			return 0;
		}

		strbuffDate.append(strbuffInputDate.substring(0, 4));
		strbuffInputDate.delete(0, 5);

		for (int i = 0; i < 2; i++) {
			if (!strbuffInputDate.substring(1, 2).equals("/")) {
				if (!strbuffInputDate.substring(2, 3).equals("/")) {
					return 0;
				} else {
					strbuffDate.append(strbuffInputDate.substring(0, 2));
					strbuffInputDate.delete(0, 3);
				}
			} else {
				strbuffDate.append("0");
				strbuffDate.append(strbuffInputDate.substring(0, 1));
				strbuffInputDate.delete(0, 2);
			}
		}

		for (int i = 0; i < strbuffDate.length(); i++) {
			char charData = strbuffDate.charAt(i);
			if ((charData < '0') || (charData > '9')) {
				return 0;
			}
		}
		return Integer.parseInt(strbuffDate.toString());
	}

	/**
	 * java.sql.Timestampを文字列に変換する。(yyyy'/'MM'/'dd' 'HH':'mm)
	 * 
	 * @param timestamp
	 *            変換したいTimestamp
	 * @return String 変換された内容
	 */
	public static String toString(Timestamp timestamp) {
		if (timestamp != null) {
			Date date = new Date(timestamp.getTime());
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy'/'MM'/'dd' 'HH':'mm");

			return formatter.format(date);

		} else {
			return null;
		}
	}

	/**
	 * java.sql.Timestampを文字列に変換する。(yyyy'/'MM'/'dd')
	 * 
	 * @param timestamp
	 *            変換したいTimestamp
	 * @return String 変換された内容
	 */
	public static String TimestampToDate(Timestamp timestamp) {
		if (timestamp != null) {
			Date date = new Date(timestamp.getTime());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy'/'MM'/'dd'");

			return formatter.format(date);

		} else {
			return null;
		}
	}

	/**
	 * 日付形式（YYYY/MM/DD、YYYY/M/DD、YYYY/MM/D、YYYY/M/D）を日付形式（YYYY/MM/DD）へ変換
	 * 
	 * @param strDate
	 *            変換したいデータ
	 * @return String 変換されたデータ
	 */
	public static String fixDate(String strDate) {

		StringBuffer strbuffDate = new StringBuffer();
		StringBuffer strbuffInputDate = new StringBuffer(strDate + "/");

		// 8桁無ければ無条件にエラー
		if (strbuffInputDate.length() < 8) {
			return null;
		}
		if (!strbuffInputDate.substring(4, 5).equals("/")) {
			return null;
		}

		strbuffDate.append(strbuffInputDate.substring(0, 4));
		strbuffInputDate.delete(0, 5);

		for (int i = 0; i < 2; i++) {
			if (!strbuffInputDate.substring(1, 2).equals("/")) {
				if (!strbuffInputDate.substring(2, 3).equals("/")) {
					return null;
				} else {
					strbuffDate.append(strbuffInputDate.substring(0, 2));
					strbuffInputDate.delete(0, 3);
				}
			} else {
				strbuffDate.append("0");
				strbuffDate.append(strbuffInputDate.substring(0, 1));
				strbuffInputDate.delete(0, 2);
			}
		}

		for (int i = 0; i < strbuffDate.length(); i++) {
			char charData = strbuffDate.charAt(i);
			if ((charData < '0') || (charData > '9')) {
				return null;
			}
		}
		strbuffDate.insert(4, "/");
		strbuffDate.insert(7, "/");
		return strbuffDate.toString();
	}

	/**
	 * <H3>文字列（YYYYMMDD）を日付形式(YY/MM/DD)へ変換する</H3>
	 * 
	 * @param strDate
	 *            変換する文字列
	 * @return String 変換後の日付形式の文字列
	 */
	public static String Str2Date2(String strDate) {

		if (strDate != null && strDate.equals("0")) {
			return "";
		}

		// 2文字未満の場合、エラーとなるため修正
		// StringBuffer stbDate = new StringBuffer(strDate.substring(2));
		//
		// if (stbDate.length() == 6) {
		// stbDate.insert(2, "/");
		// stbDate.insert(5, "/");
		// } else {
		// return strDate;
		// }
		// return stbDate.toString();

		if (strDate != null && strDate.length() == 8) {
			StringBuffer stbDate = new StringBuffer(strDate.substring(2));

			stbDate.insert(2, "/");
			stbDate.insert(5, "/");
			strDate = stbDate.toString();

		}
		return strDate;

	}

	/**
	 * 日付変換を設定する
	 * 
	 * @param strData
	 * @return newDataString --使用例-- 050825 ⇒ 20050825
	 * 
	 */
	public static String setDateString(String strDate) {
		String newDateString = "";

		if (strDate != null && strDate.length() == 6) {
			// newDateString = DateUtil.PATTERN_DB_REGIST_DATE_20YYMMDD +
			// strDate;
			// 半角数字チェック
			if (CheckUtil.isHalfNumber(strDate)) {
				int intYearkb = PATTERN_YEARKB;
				int intYear = Integer.parseInt(strDate.substring(0, 2));
				if (intYear >= intYearkb) {
					newDateString = DateUtil.PATTERN_DB_REGIST_DATE_19YYMMDD
							+ strDate;
				} else {
					newDateString = DateUtil.PATTERN_DB_REGIST_DATE_20YYMMDD
							+ strDate;
				}
			}
		} else {
			return strDate;
		}

		return newDateString;
	}

	/**
	 * <H3>日付変換を設定する</H3>
	 * 
	 * @param strDate
	 *            変換する文字列 20050825 ⇒ 05/08/25 050825 ⇒ 05/08/25
	 * 
	 * @return String 変換後の日付形式の文字列
	 */
	public static String Str2Date3(String strDate) {

		if (strDate == null || strDate.equals("")) {
			return "";
		}
		StringBuffer stbDate = null;

		if (strDate.length() == 6) {
			stbDate = new StringBuffer(strDate);
			stbDate.insert(2, "/");
			stbDate.insert(5, "/");
		} else if (strDate.length() == 8) {
			strDate = strDate.substring(2);
			stbDate = new StringBuffer(strDate);
			stbDate.insert(2, "/");
			stbDate.insert(5, "/");
		} else {
			stbDate = new StringBuffer("");
		}
		strDate = stbDate.toString();

		return strDate;
	}

	/**
	 * 日付形式（YY/MM/DD YYYY/MM/DD）を文字列（YYMMDD）へ変換
	 * 
	 * @param strDate
	 *            変換したいデータ
	 * @return int 変換されたデータ
	 */
	public static String Date3Str(String strDate) {

		StringBuffer strbuffDate = new StringBuffer();
		StringBuffer strbuffInputDate = new StringBuffer(strDate + "/");

		String strDate3 = "0";

		if (strbuffInputDate.length() == 10
				|| strbuffInputDate.substring(4, 5).equals("/")) {
			strbuffInputDate.delete(0, 2);
		}
		// 6桁無ければ無条件にエラー
		if (strbuffInputDate.length() == 8
				|| strbuffInputDate.substring(2, 3).equals("/")) {

			strbuffDate.append(strbuffInputDate.substring(0, 2));
			strbuffInputDate.delete(0, 3);
			strbuffDate.append(strbuffInputDate.substring(0, 2));
			strbuffInputDate.delete(0, 3);
			strbuffDate.append(strbuffInputDate.substring(0, 2));

			for (int i = 0; i < strbuffDate.length(); i++) {
				char charData = strbuffDate.charAt(i);
				if ((charData < '0') || (charData > '9')) {
					strDate3 = "0";
				}
			}
			strDate3 = strbuffDate.toString();
		}
		return strDate3;
	}

	/**
	 * 月をロールします
	 * 
	 * @return ロール後の値
	 * @param String
	 *            strDate(yyyyMMdd)
	 * @param int intMonth ロール値 月後 = + , 月前 = -
	 * @return String ロール後の値
	 */
	public static String rollMonth(String strDate, int intMonth) {

		String strDay = "0";
		if (strDate != null && strDate.length() == 8) {

			int year = Integer.parseInt(strDate.substring(0, 4));
			int month = Integer.parseInt(strDate.substring(4, 6)) - 1;
			int day = Integer.parseInt(strDate.substring(6, 8));

			Calendar c = Calendar.getInstance();
			DecimalFormat df = new DecimalFormat("00");
			c.set(Calendar.YEAR, year);
			c.set(Calendar.MONTH, month);
			c.set(Calendar.DATE, day);
			c.add(Calendar.MONTH, intMonth);
			strDay = c.get(Calendar.YEAR)
					+ df.format(c.get(Calendar.MONTH) + 1)
					+ df.format(c.get(Calendar.DATE));
		}
		return strDay;
	}

	/**
	 * <H3>日付変換を設定する</H3>
	 * 
	 * @param strDate
	 *            変換する文字列 20050825 ⇒ 050825
	 * 
	 * @return String 変換後の日付形式の文字列
	 */
	public static String Str2Date4(String strDate) {

		if (strDate == null || strDate.equals("")) {
			return "";
		}

		if (strDate.length() == 8) {
			strDate = strDate.substring(2);
		} else {
			strDate = "";
		}

		return strDate;
	}

	/**
	 * 日付変換を設定する
	 * 
	 * @param strData
	 *            日付
	 * @param userInfo
	 *            ユーザー情報
	 * @return newDataString --使用例-- 0508 ⇒ 200508
	 * 
	 */
	public static String setDateString2(String strDate) {
		String strNewDate = "";

		if (strDate != null && strDate.length() == 4) {
			// 半角数字チェック
			if (CheckUtil.isHalfNumber(strDate)) {
				int intYearkb = PATTERN_YEARKB;
				int intYear = Integer.parseInt(strDate.substring(0, 2));
				if (intYear >= intYearkb) {
					strNewDate = DateUtil.PATTERN_DB_REGIST_DATE_19YYMMDD
							+ strDate;
				} else {
					strNewDate = DateUtil.PATTERN_DB_REGIST_DATE_20YYMMDD
							+ strDate;
				}
			}
		}
		return strNewDate;
	}

	/**
	 * <H3>文字列（YYYYMM）/（YYYYMMDD）を日付形式(YY/MM)へ変換する</H3>
	 * 
	 * @param strDate
	 *            変換する文字列
	 * @return String 変換後の日付形式の文字列
	 */
	public static String Str2Date5(String strDate) {
		if (strDate == null || strDate.equals("") || strDate.equals("0")) {
			return "";
		}
		if (strDate.length() == 8) {
			strDate = strDate.substring(0, 6);
		}
		if (strDate.length() == 6) {
			StringBuffer stbDate = new StringBuffer(strDate.substring(2));

			stbDate.insert(2, "/");
			strDate = stbDate.toString();

		} else {
			return strDate;
		}

		return strDate;
	}

	/**
	 * <H3>文字列（YY/MM）を日付形式(YYYYMM00)へ変換する</H3>
	 * 
	 * @param strDate
	 *            変換する文字列
	 * @return String 変換後の日付形式の文字列
	 */
	public static String Str2Date6(String strDate) {

		if (strDate == null || strDate.equals("")) {
			return "";
		}

		String year = strDate.substring(0, 2);
		String month = strDate.substring(3, 5);

		String date = year + month + PATTERN_DB_REGIST_DATE_YYMM00;
		// 6桁の日付ー＞ 8桁の日付
		if (date.length() == 6) {
			date = setDateString(date);
		}

		return date;
	}

	/**
	 * 日付形式（YY/MM/DD）を文字列（YYYYMMDD）へ変換
	 * 
	 * @param strDate
	 *            変換したいデータ
	 * @return int 変換されたデータ
	 */
	public static String Date4Str(String strDate) {

		String strDate4 = strDate;
		if (strDate4 != null && !"".equals(strDate4)) {
			// 6桁無ければ無条件にエラー
			if (strDate4.length() == 8 && strDate4.substring(2, 3).equals("/")) {
				strDate4 = Date3Str(strDate4);
			}
			// 6桁の日付ー＞ 8桁の日付
			if (strDate4.length() == 6) {
				strDate4 = setDateString(strDate4);
			}
		}
		return strDate4;
	}

	/**
	 * 日付変換を設定する
	 * 
	 * @param strData
	 *            　日付
	 * 
	 * @return newDataString --使用例-- 200508 ⇒ 20050800
	 * 
	 * 
	 */
	public static String addDateString(String strDate) {

		String strNewDate = "";
		if (strDate != null && strDate.length() == 6) {
			// 半角数字チェック
			if (CheckUtil.isHalfNumber(strDate)) {
				strNewDate = strDate + PATTERN_DB_REGIST_DATE_YYMM00;
			} else {
				return strDate;
			}
		} else {
			return strDate;
		}
		return strNewDate;
	}

	/**
	 * <H3>文字列（YY/MM）を日付形式(YYYYMM)へ変換する</H3>
	 * 
	 * @param strDate
	 *            変換する文字列
	 * @return String 変換後の日付形式の文字列
	 */
	public static String Str2Date7(String strDate) {

		if (strDate == null || strDate.equals("")) {
			return "";
		}

		if (strDate.length() != 5) {
			return strDate;
		}

		String year = strDate.substring(0, 2);
		String month = strDate.substring(3, 5);
		String date = year + month;
		if (CheckUtil.isHalfNumber(date)) {
			int intYear = Integer.parseInt(year);
			if (intYear >= PATTERN_YEARKB) {
				date = DateUtil.PATTERN_DB_REGIST_DATE_19YYMMDD + date;
			} else {
				date = DateUtil.PATTERN_DB_REGIST_DATE_20YYMMDD + date;
			}
		}

		return date;
	}

	/**
	 * 日付変換を設定する
	 * 
	 * @param strDate
	 *            日付
	 * @return newDataString --使用例-- 20050828 ⇒ 0508 050828 ⇒ 0508
	 */
	public static String setDateToYyMm(String strDate) {
		String strNewDate = "";

		if (strDate != null && strDate.length() >= 6) {
			// 半角数字チェック
			if (CheckUtil.isHalfNumber(strDate)) {
				strNewDate = strDate.substring(2, 6);
			}
		}
		return strNewDate;
	}

	/**
	 * <H3>当月のFirstDayを取得する。</H3>
	 * 
	 * @param なし
	 * 
	 * @return 当月のFirstDay
	 * 
	 */
	public String getMonthFirstDay() {
		String strDay = "";
		Calendar cal = Calendar.getInstance();
		strDay = CommonUtil.fillInLeft(
				String.valueOf(cal.getActualMinimum(Calendar.DAY_OF_MONTH)),
				'0', 2);
		strDay = DateUtil.nowDate(DateUtil.PATTERN_DB_REGIST_DATE6).substring(
				0, 4)
				+ strDay;
		return strDay;
	}

	/**
	 * <H3>当月のLastDayを取得する。</H3>
	 * 
	 * @param なし
	 * 
	 * @return 当月のLastDay
	 * 
	 */
	public String getMonthLastDay() {
		String strDay = "";

		Calendar cal = Calendar.getInstance();
		strDay = CommonUtil.fillInLeft(
				String.valueOf(cal.getActualMaximum(Calendar.DAY_OF_MONTH)),
				'0', 2);
		strDay = DateUtil.nowDate(DateUtil.PATTERN_DB_REGIST_DATE6).substring(
				0, 4)
				+ strDay;
		return strDay;
	}

	/**
	 * 日付変換を設定する
	 * 
	 * @param intDate
	 *            日付
	 * @return newDataString --使用例-- 200508 ⇒ 05/08
	 */
	public static String int2Date1(int intDate) {
		String strNewDate = "";

		if (intDate != 0) {
			strNewDate = Str2Date5(String.valueOf(intDate));
		}
		return strNewDate;
	}

	/**
	 * <H3>文字列（YYYYMM）を日付形式(YYYY/MM)へ変換する</H3>
	 * 
	 * @param strDate
	 *            変換する文字列
	 * @return String 変換後の日付形式の文字列
	 */
	public static String Str2Date8(String strDate) {

		if (strDate == null || strDate.equals("")) {
			return "";
		}

		if (strDate.length() == 6) {

			StringBuffer strTemp = new StringBuffer(strDate);
			strTemp.insert(4, "/");
			strDate = strTemp.toString();

		} else {
			return strDate;
		}

		return strDate;
	}

	/**
	 * <H3>月末日の取得</H3>
	 * 
	 * @param strYearMonth
	 *            年月(200509 / 20050910)
	 * @return String 変換後の日付形式の文字列
	 */
	public static String getMaxDay(String strYearMonth) {
		String strDate = "";
		if (strYearMonth != null && strYearMonth.length() >= 6) {
			int year = Integer.parseInt(strYearMonth.substring(0, 4));
			int month = Integer.parseInt(strYearMonth.substring(4, 6));
			Calendar c = Calendar.getInstance();
			DecimalFormat df = new DecimalFormat("00");
			c.set(Calendar.YEAR, year);
			c.set(Calendar.MONTH, month);
			c.set(Calendar.DATE, 1);
			c.add(Calendar.DATE, -1);
			strDate = c.get(Calendar.YEAR)
					+ df.format(c.get(Calendar.MONTH) + 1)
					+ df.format(c.get(Calendar.DATE));
		}
		return strDate;
	}

	/**
	 * <H3>文字列（YY/MM）を日付形式(YYMM)へ変換する</H3>
	 * 
	 * @param strDate
	 *            変換する文字列
	 * @return String 変換後の日付形式の文字列
	 */
	public static String Str4Date4(String strDate) {

		if (strDate == null || strDate.equals("")) {
			return "";
		}

		if (strDate.length() != 5) {
			return strDate;
		}

		String year = strDate.substring(0, 2);
		String month = strDate.substring(3, 5);
		String date = year + month;
		if (!CheckUtil.isHalfNumber(date)) {
			return "";
		}
		return date;
	}

	/**
	 * <H3>指定日数後（前）の日付取得</H3>
	 * 
	 * @param strDate
	 *            　処理日付(20050910/050910)
	 * @param rollDay
	 *            　指定日数(n)プラス、マイナス数字
	 * 
	 * @return String 指定日数後(前)の日付形式の文字列
	 */
	public static String rollDay(String strDate, int rollDay) {
		String date = "";
		String strDate2 = "";
		if (strDate == null || (strDate.length() != 6 && strDate.length() != 8)) {
			return "";
		}
		if (!CheckUtil.isHalfNumber(date)) {
			return "";
		}
		if (strDate.length() == 6) {
			strDate2 = strDate;
			strDate = setDateString(strDate);
		}
		int year = Integer.parseInt(strDate.substring(0, 4));
		int month = Integer.parseInt(strDate.substring(4, 6)) - 1;
		int day = Integer.parseInt(strDate.substring(6, 8));
		if (rollDay != 0) {
			Calendar c = Calendar.getInstance();
			DecimalFormat df = new DecimalFormat("00");
			c.set(Calendar.YEAR, year);
			c.set(Calendar.MONTH, month);
			c.set(Calendar.DATE, day);
			c.add(Calendar.DATE, rollDay);
			date = c.get(Calendar.YEAR) + df.format(c.get(Calendar.MONTH) + 1)
					+ df.format(c.get(Calendar.DATE));
		}
		if (strDate2.length() == 6) {
			date = date.substring(2);
		}
		return date;
	}

	/**
	 * <H3>二つの日付の間の月数を計算する</H3>
	 * 
	 * @param strDateFrom
	 *            　処理開始日付(20050910/200509)
	 * @param strDateTo
	 *            　　処理終了日付(20051030/200510)
	 * 
	 * @return int 二つの日付の間の月数 -1:エラー、>=0:正常
	 */
	public static int compareMonth(String strDateFrom, String strDateTo) {
		int intReturn = -1;
		int intFromYear = 0;
		int intFromMonth = 0;
		int intToYear = 0;
		int intToMonth = 0;
		int intMonth = 0;
		String strChkDateFrom = null;
		String strChkDateTo = null;
		if (strDateFrom == null || strDateTo == null) {
			return intReturn;
		}
		if (strDateFrom.length() < 6 || strDateTo.length() < 6) {
			return intReturn;
		}
		if (strDateFrom.length() == 6) {
			strChkDateFrom = Str2Date(strDateFrom + "01");
		} else {
			strChkDateFrom = Str2Date(strDateFrom);
		}
		if (strDateTo.length() == 6) {
			strChkDateTo = Str2Date(strDateTo + "01");
		} else {
			strChkDateTo = Str2Date(strDateTo);
		}

		if (!CheckUtil.checkDateConsistency(strChkDateFrom)
				|| !CheckUtil.checkDateConsistency(strChkDateTo)) {
			return intReturn;
		}
		/** 開始日付年月日 **/
		intFromYear = Integer.parseInt(strDateFrom.substring(0, 4));
		intFromMonth = Integer.parseInt(strDateFrom.substring(4, 6));

		/** 終了日付年月日 **/
		intToYear = Integer.parseInt(strDateTo.substring(0, 4));
		intToMonth = Integer.parseInt(strDateTo.substring(4, 6));
		/*
		 * intReturn = intToYear - intFromYear; if (intReturn < 0) { intReturn =
		 * intReturn * -1; intMonth = intFromMonth - intToMonth; } else {
		 * intMonth = intToMonth - intFromMonth; } if (intMonth < 0) { intMonth
		 * = intMonth * -1; } intReturn = intReturn * 12 + intMonth;
		 */
		if (Integer.parseInt(strDateFrom) > Integer.parseInt(strDateTo)) {
			intReturn = intFromYear - intToYear;
			intMonth = intFromMonth - intToMonth;
		} else {
			intReturn = intToYear - intFromYear;
			intMonth = intToMonth - intFromMonth;
		}
		intReturn = intReturn * 12 + intMonth + 1;
		return intReturn;

	}

	/**
	 * <H3>文字列（YYMM)/(YYMMDD）を日付形式(YYYY/MM/DD)へ変換する</H3>
	 * 
	 * @param strDate
	 *            変換する文字列
	 * @return String 変換後の日付形式の文字列
	 */
	public static String Str2Date9(String strDate) {

		if (strDate == null || strDate.equals("")) {
			return "";
		}

		if (strDate.length() != 4 && strDate.length() != 6) {
			return strDate;
		}
		String year = strDate.substring(0, 2);
		String month = strDate.substring(2, 4);
		String date = year + month;
		if (strDate.length() == 4) {
			date = date + PATTERN_DATE_YYMM01;
		} else {
			date = date + strDate.substring(4, 6);
		}

		if (CheckUtil.isHalfNumber(date)) {
			int intYear = Integer.parseInt(year);
			if (intYear >= PATTERN_YEARKB) {
				date = DateUtil.PATTERN_DB_REGIST_DATE_19YYMMDD + date;
			} else {
				date = DateUtil.PATTERN_DB_REGIST_DATE_20YYMMDD + date;
			}
			date = date.substring(0, 4) + "/" + date.substring(4, 6) + "/"
					+ date.substring(6, 8);

		} else {
			return "";
		}

		return date;
	}

	// 方法：和当前日期比较；当返回为0时，两个时间相同
	public static boolean isToday(Date date) {
		Date currDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		String currTemp = sdf.format(currDate);
		String dateTemp = sdf.format(date);
		if (date != null && currTemp.equals(dateTemp)) {
			return true;
		} else {
			return false;
		}

	}

}
