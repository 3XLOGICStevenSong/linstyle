/*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * 
 * System Name        : Checkロジック
 * Description        : 
 * 
 *+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
package com.djb.highway.common.util;

import java.io.UnsupportedEncodingException;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * <H3>ユーティリティクラス</H3>
 * 
 * <PRE>
 * 
 * チェックのユーティリティ・クラスです。
 * 
 * </PRE>
 * 
 * @author	Wangjiquan
 */
public class CheckUtil {

	public static final String ENCODING = "shift_jis";

	/**
	 * <H3>文字列比較チェック.</H3>
	 * 
	 * <PRE>
	 * 
	 * 第二引数の文字が第一引数の文字より大きいかをチェックします。 <br>
	 * 大文字小文字の区別なしで、2 つの文字列を辞書式に比較します。 <br>
	 * 第一引数 &lt; 第二引数 : true 第一引数 &gt; 第二引数 : false
	 * 
	 * </PRE>
	 * 
	 * @param fromStr
	 *            java.lang.String
	 * @param toStr
	 *            java.lang.String
	 * @return boolean
	 */
	public static boolean isBig(String fromStr, String toStr) {

		if (isEmpty(fromStr)) {
			fromStr = "";
		}
		if (isEmpty(toStr)) {
			return false;
		}
		if (toStr.compareToIgnoreCase(fromStr) < 0) {
			return false;
		}

		return true;
	}

	/**
	 * <H3>null・空文字チェック.</H3>
	 * 
	 * <PRE>
	 * 
	 * 文字列が以下の条件に合う場合は、trueを返します。 <br>
	 * <ul>
	 * <li>prmStringがnull。
	 * <li>prmStringの長さが0。
	 * </ul>
	 * 
	 * </PRE>
	 * 
	 * @param prmString
	 *            チェック対象文字列
	 * @return 文字列が null もしくは空の場合は true、それ以外は false
	 */
	public static boolean isEmpty(String prmString) {

		// 入力文字のnullチェック
		if (prmString == null) {
			return true;
		}

		// 入力文字の長さチェック
		if (prmString.length() == 0) {
			return true;
		}

		return false;
	}

	/**
	 * <H3>半角数字チェック.</H3>
	 * 
	 * <PRE>
	 * 
	 * 半角数字のチェックをします。 <br>
	 * 対象文字 ： 数字、英大文字、英小文字（全て半角） <br>
	 * 以下の条件に合う場合、trueを返します。 <br>
	 * <ul>
	 * <li>prmStrがnull、もしくは長さが0。
	 * <li>prmStrの各文字が、'0' ～ '9'の場合。
	 * </ul>
	 * 
	 * 以下の条件に合う場合、falueを返します <BR>
	 * <ul>
	 * <li>prmStrの各文字が、'0' ～ '9'でない場合。
	 * </ul>
	 * 
	 * </PRE>
	 * 
	 * @return 対象文字であれば true、それ以外は false
	 * @param prmStr
	 *            チェック対象文字列
	 */
	public static boolean isHalfNumber(String prmStr) {

		if (prmStr == null || prmStr.length() == 0) {
			//元ソース---------------------
			//return false;

			return true;

		}

		StringCharacterIterator sci = new StringCharacterIterator(prmStr);

		for (char c = sci.first(); c < CharacterIterator.DONE; c = sci.next()) {
			if (c >= '0' && c <= '9') { //半角数字
			} else {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * <H3>半角英数チェック.</H3>
	 * 
	 * <PRE>
	 * 
	 * 半角英数のチェックをします。 <br>
	 * 対象文字 ： 数字、英大文字、英小文字（全て半角） <br>
	 * 以下の条件に合う場合、trueを返します。 <br>
	 * <ul>
	 * <li>prmStrがnull、もしくは長さが0。
	 * <li>prmStrの各文字が、'0' ～ '9'、'A' ～ 'Z'、'a' ～ 'z'の場合。
	 * </ul>
	 * 
	 * 以下の条件に合う場合、falseを返します。 <BR>
	 * <ul>
	 * <li>prmStrの各文字が、'0' ～ '9'、'A' ～ 'Z'、'a' ～ 'z'のいずれでもない場合。
	 * </ul>
	 * 
	 * </PRE>
	 * 
	 * @return 対象文字であれば true、それ以外は false
	 * @param prmStr
	 *            チェック対象文字列
	 */
	public static boolean isHalfStroke(String prmStr) {

		if (prmStr == null || prmStr.length() == 0) {
			//元ソース---------------------
			//return false;

			return true;

		}

		StringCharacterIterator sci = new StringCharacterIterator(prmStr);

		for (char c = sci.first(); c < CharacterIterator.DONE; c = sci.next()) {
			if ((c >= '0' && c <= '9') || //半角数字
					(c >= 'A' && c <= 'Z') || //半角英大文字
					(c >= 'a' && c <= 'z')) { //半角英小文字
			} else {
				return false;
			}
		}
		return true;
	}

	/**
	 * <H3>文字列のバイト数をチェックする.</H3>
	 * 
	 * <PRE>
	 * [null]もしくは空文字の場合は、trueを返します。
	 * </PRE>
	 * 
	 * @param strInput
	 *            チェック対象の文字列
	 * @param intMaxLength
	 *            最大バイト数
	 * @return boolean 正しければ true そうでなければ false
	 */
	public static boolean checkLength(String strInput, int intMaxLength) {
		int intLen = 0;

		if (strInput == null || strInput.length() == 0) {
			return true;
		}

		try {
			byte[] bytstr = strInput.getBytes(ENCODING);
			intLen = bytstr.length;
		} catch (Exception e) {
			// e.printStackTrace();
		}

		if (intLen <= intMaxLength) {
			return true;
		}

		return false;
	}

	/**
	 * 半角数字で、かつ指定されたバイト数以内かを判断する. <BR>
	 * 
	 * <PRE>
	 * nullもしくは空文字の場合は、trueを返します。
	 * 小数点が存在する場合はfalseを返します。
	 * マイナス符号は、trueを返します。
	 *  
	 * 例)             
	 * 1.1  : false
	 * -11  : true
	 * </PRE>
	 * 
	 * @param strInput
	 *            チェック対象の文字列
	 * @param intMaxLength
	 *            最大バイト数
	 * @return boolean 指定された条件内ならば true そうじゃなければ false
	 */
	public static boolean checkNumberByte(String strInput, int intMaxLength) {

		if (strInput == null || strInput.length() == 0) {
			return true;
		}

		if (!checkStringSingle(strInput) || !checkNumber(strInput)
				|| !checkLength(strInput, intMaxLength)) {
			return false;
		}

		return true;
	}

	/**
	 * 半角で、かつ指定されたバイト数以内かを判断する. <br>
	 * 
	 * <PRE>
	 * nullもしくは空文字の場合は、trueを返します。
	 * </PRE>
	 * 
	 * @param strInput
	 *            チェック対象の文字列
	 * @param intMaxLength
	 *            最大バイト数
	 * @return boolean 指定された条件内ならば true そうじゃなければ false
	 */
	public static boolean checkStringSingleByte(String strInput, int intMaxLength) {

		if (strInput == null || strInput.length() == 0) {
			return true;
		}

		if (!checkStringSingle(strInput) || !checkLength(strInput, intMaxLength)) {
			return false;
		}

		return true;
	}

	/**
	 * 全角で、かつ指定されたバイト数以内かを判断する. <br>
	 * 
	 * <PRE>
	 * nullもしくは空文字の場合は、trueを返します。
	 * </PRE>
	 * 
	 * @param strInput
	 *            チェック対象の文字列
	 * @param intMaxLength
	 *            最大バイト数
	 * @return boolean 指定された条件内ならば true そうじゃなければ false
	 */
	public static boolean checkString2byteByte(String strInput, int intMaxLength) {

		if (strInput == null || strInput.length() == 0) {
			return true;
		}

		if (!checkString2byte(strInput) || !checkLength(strInput, intMaxLength)) {
			return false;
		}

		return true;
	}

	/**
	 * 文字列が数値のみで構成されているかをチェックする
	 * 
	 * <PRE>
	 * nullもしくは空文字の場合は、trueを返します。
	 * </PRE>
	 * 
	 * @param strInput
	 *            チェック対象の文字列
	 * @return boolean 数値のみなら true そうでなければ false
	 */
	public static boolean checkNumber(String strInput) {

		if (strInput == null || strInput.length() == 0) {
			return true;
		}

		if (!checkStringSingle(strInput)) {
			return false;
		}
		try {
			Long.parseLong(strInput);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	/**
	 * 文字列中が全て半角文字であるかチェックする
	 * 
	 * <PRE>
	 * nullもしくは空文字の場合は、trueを返します。
	 * </PRE>
	 *  
	 * @param strInput
	 *            チェック対象の文字列
	 * @return boolean 半角文字のみなら true そうでなければ false
	 */
	public static boolean checkStringSingle(String strInput) {

		if (strInput == null || strInput.length() == 0) {
			return true;
		}

		// 全角の場合の対処も考慮して、何バイト分の長さであるかを取得
		int blength = 0;
		int length = 0;
		try {
			blength = strInput.getBytes(ENCODING).length;
			length = strInput.length();
		} catch (UnsupportedEncodingException e) {
			// e.printStackTrace();
		}
		if (blength != length) { // バイト数が文字数と等しくない場合
			return false;
		}
		return true;

	}

	/**
	 * 文字列が半角英数のみで構成されているかをチェックする
	 * 
	 * <PRE>
	 * nullもしくは空文字の場合は、trueを返します。
	 * </PRE>
	 * 
	 * @param strInput
	 *            チェック対象の文字列
	 * @return boolean 半角英数のみなら true そうでなければ false
	 */
	public static boolean checkAlphabet(String strInput) {
		if (strInput == null || strInput.length() == 0) {
			return true;
		}

		for (int i = 0; i < strInput.length(); i++) {
			char c = strInput.charAt(i);
			if ((c < '0' || c > '9') && // 数字でない
					(c < 'a' || c > 'z') && // 小文字アルファベットでない
					(c < 'A' || c > 'Z') // 大文字アルファベットでない
			) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 文字列中が全て全角文字であるかチェックする
	 * 
	 * <PRE>
	 * nullもしくは空文字の場合は、trueを返します。
	 * </PRE>
	 * 
	 * @param strInput
	 *            チェック対象の文字列
	 * @return boolean 全角文字（2バイト文字）のみなら true そうでなければ false
	 */
	public static boolean checkString2byte(String strInput) {
		if (strInput == null || strInput.length() == 0) {
			return true;
		}

		// 全角の場合の対処も考慮して、何バイト分の長さであるかを取得
		int blength = 0;
		int length = 0;
		try {
			blength = strInput.getBytes(ENCODING).length;
			length = strInput.length();
		} catch (UnsupportedEncodingException e) {
			// e.printStackTrace();
		}
		if (blength / 2 != length) { // バイト数/2が文字数と等しくない場合
			return false;
		}
		return true;
	}

	/**
	 * YYYY/MM/DDで受けとった日付が正しいかどうかチェックする <BR>
	 * 例外として、9999/99/99はtrueを返す。
	 * 
	 * <PRE>
	 * nullもしくは空文字の場合は、trueを返します。
	 * </PRE>
	 * 
	 * @param strInput
	 *            チェック対象の文字列
	 * @return boolean 正しければ true そうでなければ false
	 */
	public static boolean checkDateConsistency(String strInput) {
		boolean blnFlag = false;
		int intYear = 999;
		int intMonth = 999;
		int intDay = 999;

		if (strInput == null || strInput.length() == 0) {
			return true;
		}

		if (strInput.equals("9999/99/99")) {
			return true;
		}

		if (strInput.length() == 10) {
			if (strInput.substring(4, 5).equals("/") && strInput.substring(7, 8).equals("/")) {
				try {
					intYear = Integer.parseInt(strInput.substring(0, 4));
					intMonth = Integer.parseInt(strInput.substring(5, 7));
					intDay = Integer.parseInt(strInput.substring(8, 10));
				} catch (NumberFormatException e) {
					return false;
				}

				Calendar cal = GregorianCalendar.getInstance();

				cal.set(intYear, (intMonth - 1), intDay);

				if (intYear == cal.get(Calendar.YEAR) && (intMonth - 1) == cal.get(Calendar.MONTH)
						&& intDay == cal.get(Calendar.DATE)) {
					blnFlag = true;
				}
			}
		}
		return blnFlag;
	}

	/**
	 * 小数の整数部分桁数と小数部分桁数のチェック <BR>
	 * 
	 * <PRE>
	 * nullもしくは空文字の場合は、trueを返します。
	 * </PRE>
	 * 
	 * @param str
	 *            チェック対象の文字列
	 * @param num1
	 * 				整数部桁数
	 * 
	 * @param num2
	 * 				小数部桁数
	 * 
	 * @return チェック結果。TrueならNullまたはブランクでない FalseならNullまたはブランク
	 */
	public static boolean checkDecimal(String str, int num1, int num2) {

		if (str == null || str.length() == 0) {
			return true;
		}
		
		int idx = 0;
		String strNum = "";
		String strDec = "";
		idx = str.indexOf(".", 0);
		

		if (idx <= 0) {
			//数値チェック
			if(!CheckUtil.checkNumber(str)){
				return false;	
			}
			if (str.length() > num1) {
				return false;
			} else {
				return true;
			}
		} else {
			strNum = str.substring(0, idx);
			strDec = str.substring(idx + 1);
			//数値チェック
			if((CheckUtil.checkNumber(strNum) && CheckUtil.isHalfNumber(strDec))==false){
				return false;
			}

			
			if (strNum.length() > num1 || strDec.length() > num2) {
				return false;
			} else {
				return true;
			}
		}
	}

	/**
	 * YYYYMMDDで受けとった日付が正しいかどうかチェックする <BR>
	 * 例外として、99999999はtrueを返す。
	 * 
	 * @param strInput
	 *            チェック対象の文字列
	 * @return boolean 正しければ true そうでなければ false
	 */
	public static boolean checkNumDateConsistency(String strInput){
		boolean blnFlag = false;
		int intYear = 999;
		int intMonth = 999;
		int intDay = 999;
		
		if (strInput == null || "".equals(strInput)) {
			return true;
		}else{
			strInput=DateUtil.setDateString(strInput);
		}

		if (strInput.length() == 8) {

			try {
				intYear = Integer.parseInt(strInput.substring(0, 4));
				intMonth = Integer.parseInt(strInput.substring(4, 6));
				intDay = Integer.parseInt(strInput.substring(6, 8));
			} catch (NumberFormatException e) {
				return false;
			}

			Calendar cal = GregorianCalendar.getInstance();

			cal.set(intYear, (intMonth - 1), intDay);

			if (intYear == cal.get(Calendar.YEAR)
				&& (intMonth - 1) == cal.get(Calendar.MONTH)
				&& intDay == cal.get(Calendar.DATE)) {
				blnFlag = true;
			}
		}
		return blnFlag;
	}
	
	/**
	 * <H3>金額チェック.</H3>
	 * 
	 * <PRE>
	 * 
	 * 以下の条件に合う場合、trueを返します。 <br>
	 * 9999 +9999 -9999 +9,999 -9,999 9,999 99,999,999<br>
	 * 
	 * </PRE>
	 * 
	 * @return 対象文字であれば true、それ以外は false
	 * @param strMoney
	 *            チェック対象文字列
	 */
		public static boolean checkMoney(String strMoney) {
		
		if (strMoney == null || strMoney.length() == 0) {
			
			return true;
		}
		if(strMoney.startsWith("0") && strMoney.length() > 1){
			return false;
		}
		if(strMoney.startsWith("+") && strMoney.indexOf("0") == 1){
			return false;
		}
		if(strMoney.startsWith("-") && strMoney.indexOf("0") == 1){
			return false;
		}
		if(strMoney.indexOf("+") > 0 && !(strMoney.startsWith("-"))){
			return false;
		}
		if(strMoney.indexOf("-") > 0 && !(strMoney.startsWith("+"))){
			return false;
		}
		if(strMoney.startsWith(",")){
			return false;
		}
		StringCharacterIterator sci = new StringCharacterIterator(strMoney);
		int flag = 0;
		for (char c = sci.first(); c < CharacterIterator.DONE; c = sci.next()) {
			if ((c >= '0' && c <= '9') || c == ',' || c == '+' || c == '-') { //半角数字
				if(c == '+' || c == '-'){
					flag ++ ;
				}
				if(flag > 1){
					return false;
				}
				
			} else {
				return false;
			}
		}
		
		if(strMoney.lastIndexOf(",") < 0 ){
			return true;
		}

		
		char [] strChar = strMoney.toCharArray();
		char [] tempChar = new char[strChar.length];
		int j=0;
		for(int i=strChar.length-1; i >= 0;i--){
				
			tempChar[j] = strChar[i];
			j++;
		}
		for(int i =0; i<tempChar.length; i++){
			int n = i+1;
			if(tempChar[i] == ',' && ! (n%4 == 0)){
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * <H3>半角英字チェック.</H3>
	 * 
	 * <PRE>
	 * 
	 * 半角英字のチェックをします。 <br>
	 * 対象文字 ：英大文字、英小文字（全て半角） <br>
	 * 以下の条件に合う場合、trueを返します。 <br>
	 * <ul>
	 * <li>prmStrがnull、もしくは長さが0。
	 * <li>prmStrの各文字が、'A' ～ 'Z'、'a' ～ 'z'の場合。
	 * </ul>
	 * 
	 * 以下の条件に合う場合、falseを返します。 <BR>
	 * <ul>
	 * <li>prmStrの各文字が、'A' ～ 'Z'、'a' ～ 'z'のいずれでもない場合。
	 * </ul>
	 * 
	 * </PRE>
	 * 
	 * @return 対象文字であれば true、それ以外は false
	 * @param prmStr
	 *            チェック対象文字列
	 */
	public static boolean isHalfEnglish(String prmStr) {

		if (prmStr == null || prmStr.length() == 0) {

			return true;

		}

		StringCharacterIterator sci = new StringCharacterIterator(prmStr);

		for (char c = sci.first(); c < CharacterIterator.DONE; c = sci.next()) {
			if ((c >= 'A' && c <= 'Z') || //半角英大文字
					(c >= 'a' && c <= 'z')) { //半角英小文字
			} else {
				return false;
			}
		}
		return true;
	}

	/**
	 * 文字列が電話番号で構成されているかをチェックする
	 * 
	 * <PRE>
	 * nullもしくは空文字の場合は、trueを返します。
	 * </PRE>
	 * 
	 * @param strInput
	 *            チェック対象の文字列
	 * @return boolean 電話番号なら true そうでなければ false
	 */
	public static boolean checkTel(String strInput) {
		
		if (strInput == null || strInput.length() == 0) {
			return true;
		}

		for (int i = 0; i < strInput.length(); i++) {
			char c = strInput.charAt(i);
			if ((c < '0' || c > '9') && // 数字でない
					(c != '-') //'-'でない
			) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 時間チェック　HHMMSS　OR　HHMM
	 * @param strInput チェック対象の文字列
	 * @return boolean 時間書式と間違い時 false 
	*/

	public static boolean  checkTime(String strInput) {

		int h = 0;
		int m = 0;
		int s = 0;
	
		//ブランク時、Trueを戻る
		if(strInput == null || "".equals(strInput)){
			return true;
		}
	
		//4桁と6桁以外、Flaseを戻る
		if (strInput.length() != 4 && strInput.length() != 6){
			 return false; 
		}
		//半角数字以外、Falseを戻る
		if (!isHalfNumber(strInput)){
			 return false;
		}
		//時、分、秒を分割
		h = Integer.parseInt(strInput.substring(0,2));
		m = Integer.parseInt(strInput.substring(2,4));
		if (strInput.length() == 6){
			s = Integer.parseInt(strInput.substring(4,6));
		}
		//時、分、秒のチェック
		if ( 0 > h || h > 24) return false;
		if ( 0 > m || m > 60) return false;
		if ( 0 > s || s > 60) return false;
	
		return true;
	}

	/**
	* パラメータが半角カナだけかどうかを判定する
	*
	* @param text：チェックする文字列
	* @return 戻り値：判定結果（true：全て半角カナ文字，false：半角カナ以外の文字を含んでいる）
	*/
   public static boolean checkKana(String text) {
	char startChar = '･';
	char endChar = 'ﾟ';
	
	if (text==null || text.trim().length()==0) return true;
	
	for (int i = 0; i < text.length(); i++) {
		char temp = text.charAt(i);
		if (!(startChar <= temp && temp <= endChar)) {
			return false;
		}
	}
	return true;
   }

   /**
	* 開始日と終了日の比較する
	* 
	* <PRE>
	* 開始日と終了日の比較
	* </PRE>
	* @param  startDate   日付(050801 / 0508)
	* @param  strEndDate   日付(050801 / 0508)
	* 
	* @return blnFlg  ture:OK　false:エラー
	* Example: compareDate('050801','050901')
	*/
	public static boolean compareDate(String strStartDate,String strEndDate){
		
		boolean blnFlg = true; 
		int intStartDate = 0;
		int intEndDate = 0;
		if(strStartDate == null || "".equals(strStartDate) || strEndDate == null || "".equals(strEndDate)){
			return blnFlg;
		}
		if(strStartDate.length() == 6){
			strStartDate = DateUtil.setDateString(strStartDate);
		}
		if(strEndDate.length() == 6){
			strEndDate = DateUtil.setDateString(strEndDate);
		}
		if(strStartDate.length() != 8 || strEndDate.length() != 8){
			return blnFlg;
		}
		intStartDate = Integer.parseInt(strStartDate);
		intEndDate = Integer.parseInt(strEndDate);
		if(intStartDate > intEndDate){
			blnFlg = false;
		}
		return blnFlg;
	}

	/**
	* メールのアドレスをチェック
	* <br>
	* @param  mail  メールのアドレス
	* @return  ture:OK　false:エラー
	*
	*/
    public static boolean checkMail1(String mail){
	  boolean validFlag = false;
	  boolean specialFlag = false;
	  boolean badFlag = false;

	  char[] mailArray = mail.toCharArray();
	  int len = mailArray.length;
	  int atCount = 0;
	  //メールに　ブランクをチェック　
	  if( !mail.equals("") && mail.indexOf("@")>0 && mail.indexOf(".")>0){
		  //メールのキャラクタをチェック
		  for(int i = 0;i<len;i++){
			  if(mailArray[i]=='@'){
				  atCount++;
			  }else if(mailArray[i]>=(char)32 && mailArray[i]<=(char)44){
				  specialFlag = true;
			  }else if(mailArray[i]==(char)47 || mailArray[i]==(char)96 ||  mailArray[i]>=(char)123){
				  specialFlag = true;
			  }else if(mailArray[i]>=(char)58 && mailArray[i]<=(char)63){
				  specialFlag = true;
			  }else if(mailArray[i]>=(char)91 && mailArray[i]<=(char)94){
				  specialFlag = true;
			  }
		  }
			
		  if(atCount==1 && specialFlag==false){
			  badFlag = false;

			  String userName = mail.substring(0,mail.indexOf("@"));
			  String domainName = mail.substring(mail.indexOf("@")+1,mail.length());
			
			  if(userName.equals("") || domainName.equals("")){
				  badFlag = true; 
			  }
			  if(domainName.charAt(0)=='.' || domainName.charAt(domainName.length()-1)=='.'){
				  badFlag = true;
			  }
			  if(domainName.indexOf("..")>0){
				  badFlag = true;
			  }
				
			  validFlag = true;
		  }
		  if(badFlag == true){
			  validFlag = false;
		  }

	  }
	  return validFlag;
   }

    /**
	* メールのアドレスをチェック(担当者メンテナンス用)
	* 
	* <PRE>
	* A~Z,a~z,0~9,'_'以外　エラーです。
	* </PRE>
	* 
	* @param  mail  メールのアドレス
	* @return  ture:OK　false:エラー
	*
	*/
	public static boolean checkMail2(String mail){
	  boolean validFlag = true;
	  boolean specialFlag = false;
	  boolean badFlag = false;
	  String mailName = "";
	  char[] mailArray = mail.toCharArray();
	  int len = mailArray.length;
	  int atCount = 0;
	  //メールに　ブランクをチェック　
	  if( !mail.equals("")){
		  //メールのキャラクタをチェック
		  for(int i = 0;i<len;i++){
			  if(mailArray[i]=='_'){
				  atCount++;
			  //数字の時
			  }else if(mailArray[i]>=(char)48 && mailArray[i]<=(char)57){
			  //A~Z
			  }else if(mailArray[i]>=(char)65 && mailArray[i]<=(char)90){
			  //a~z  
			  }else if(mailArray[i]>=(char)97 && mailArray[i]<=(char)122){
			  }else{
				  specialFlag = true;
			  }
		  }
			
		  if(atCount > 1 || specialFlag==true){
			  badFlag = true;
			  if(mail.indexOf("_") > 0){
				 mailName = mail.substring(0,mail.indexOf("_"));
				 mailName = mailName + mail.substring(mail.indexOf("_")+1,mail.length());
			  }else{
				 mailName = mail;
			  }
			
			  if(mailName.equals("")){
				  badFlag = true; 
			  }
		  }
		  if(badFlag == true){
			  validFlag = false;
		  }

	  }
	  return validFlag;
   }

}
