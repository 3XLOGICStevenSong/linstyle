package com.djb.ylt.common.util;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;



public class CommonUtil {

    public static String nowDate(String strPattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strPattern);
        return simpleDateFormat.format(new Date());
    }

    public static String dateToString(Date date, String strPattern) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strPattern);
        return simpleDateFormat.format(date);
    }

    public static Date stringToDate(String strDate, String strPattern) {
        Date date = null;
        DateFormat format = new SimpleDateFormat(strPattern);
        try {
            date = format.parse(strDate);
        } catch (ParseException e) {
            // e.printStackTrace();
        }
        return date;
    }

    public static Date addOneDay(Date date) {
        try {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, 1);
            date = calendar.getTime();

        } catch (Exception e) {
            // e.printStackTrace();
        }
        return date;
    }

    public static boolean isNullOrBlank(String strParam) {
        return strParam == null || "".equals(strParam);
    }
    public static String nowMillisecond() {
        Calendar nowtime = new GregorianCalendar();
        String strDateTime = String.format("%04d", nowtime.get(Calendar.YEAR)) + "" + String.format("%02d", 1 + nowtime.get(Calendar.MONTH)) + ""
                        + String.format("%02d", nowtime.get(Calendar.DATE)) + "" + String.format("%02d", nowtime.get(Calendar.HOUR)) + ""
                        + String.format("%02d", nowtime.get(Calendar.MINUTE)) + "" + String.format("%02d", nowtime.get(Calendar.SECOND)) + ""
                        + String.format("%03d", nowtime.get(Calendar.MILLISECOND));
        return strDateTime;
    }
    /**
     * Convert a String to an int, returning a default value if the conversion
     * fails.
     * 
     * If the string is null, the default value is returned.
     * 
     * @param str
     *            the string to convert, may be null
     * @param defaultValue
     *            the default value
     * @return the int represented by the string, or the default if conversion
     *         fails
     */
    public static int toInt(String str, int defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return defaultValue;
        }
    }

    /**
     * Convert a String to an long, returning a default value if the conversion
     * fails.
     * 
     * If the string is null, the default value is returned.
     * 
     * @param str
     *            the string to convert, may be null
     * @param defaultValue
     *            the default value
     * @return the long represented by the string, or the default if conversion
     *         fails
     */
    public static long toLong(String str, long defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException nfe) {
            return defaultValue;
        }
    }

    public static float toFloat(String str, float defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException nfe) {
            return defaultValue;
        }
    }

    /**
     * set_classの値 TO get_classの値
     * 
     * @param inObj
     *            From class
     * @param outObj
     *            To class
     */
    @SuppressWarnings({ "rawtypes", "unchecked", "unused" })
    public static void reflectClass(Object inObj, Object outObj) {
        final String CLASS = "class ";
        final String SET = ".set";
        final String JAVA = "(java";
        final String JAVA1 = "(int";
        try {
            // /////////////////////////////////
            if (inObj == null || outObj == null) {
                return;
            }
            String inClassName = ("" + inObj.getClass()).substring((CLASS).length());
            // System.out.println("outClassName=="+inClassName);
            Class inClass = Class.forName(inClassName);
            Method[] get = inClass.getMethods();

            // /////////////////////////////////
            String outClassName = ("" + outObj.getClass()).substring((CLASS).length());
            // System.out.println("outClassName==="+outClassName);
            Class outClass = Class.forName(outClassName);
            Method[] set = outClass.getMethods();

            String name = "";

            for (int i = 0; i < set.length; i++) {
                name = "" + set[i];
                if (name.indexOf(outClassName + ".set") > 0) {
                    try {
                        // System.out.println(name);
                        // type creat
                        Class[] sArgTypes = {};
                        Class[] gArgTypes = null;

                        // //////////////////////////////////////////////////////
                        Class[] STRING_TYPES = { String.class };
                        Class[] STRING_GROUP_TYPES = { String[].class };
                        Class[] LIST_TYPES = { List.class };
                        Class[] LIST_GROUP_TYPES = { List[].class };
                        Class[] OBJECT_TYPES = { Object.class };
                        Class[] OBJECT_GROUP_TYPES = { Object[].class };
                        Class[] INT_TYPES = { Integer.TYPE };
                        Class[] INT_GROUP_TYPES = { int[].class };
                        Class[] long_TYPES = { Long.TYPE };
                        Class[] long_GROUP_TYPES = { long[].class };
                        Class[] FLOAT_TYPES = { Float.TYPE };
                        Class[] FLOAT_GROUP_TYPES = { Float[].class };
                        Class[] BIGDECIMAL_TYPES = { BigDecimal.class };
                        Class[] BIGDECIMAL_GROUP_TYPES = { BigDecimal[].class };
                        Class[] LONG_TYPES = { Long.class };
                        Class[] LONG_GROUP_TYPES = { Long[].class };
                        Class[] INTEGER_TYPES = { Integer.class };
                        Class[] INTEGER_GROUP_TYPES = { Integer[].class };
                        Class[] DATE_TYPES = { Date.class };
                        // //////////////////////////////////////////////////////
                        if (name.indexOf("(java.lang.String)") > 0) {
                            gArgTypes = STRING_TYPES;
                        } else if (name.indexOf("(java.lang.String[])") > 0) {
                            gArgTypes = STRING_GROUP_TYPES;
                        } else if (name.indexOf("(java.util.List)") > 0) {
                            gArgTypes = LIST_TYPES;
                        } else if (name.indexOf("(java.util.List[])") > 0) {
                            gArgTypes = LIST_GROUP_TYPES;
                        } else if (name.indexOf("(java.math.BigDecimal)") > 0) {
                            gArgTypes = BIGDECIMAL_TYPES;
                        } else if (name.indexOf("(java.math.BigDecimal[])") > 0) {
                            gArgTypes = BIGDECIMAL_GROUP_TYPES;
                        } else if (name.indexOf("(java.lang.Integer)") > 0) {
                            gArgTypes = INTEGER_TYPES;
                        } else if (name.indexOf("(java.lang.Integer[])") > 0) {
                            gArgTypes = INTEGER_GROUP_TYPES;
                        } else if (name.indexOf("(java.lang.Long)") > 0) {
                            gArgTypes = LONG_TYPES;
                        } else if (name.indexOf("(java.lang.Long[])") > 0) {
                            gArgTypes = LONG_GROUP_TYPES;
                        } else if (name.indexOf("(java.lang.Float)") > 0) {
                            gArgTypes = FLOAT_TYPES;
                        } else if (name.indexOf("(java.lang.Float[])") > 0) {
                            gArgTypes = FLOAT_GROUP_TYPES;
                        } else if (name.indexOf("(java.util.Date)") > 0) {
                            gArgTypes = DATE_TYPES;
                        }

                        // //////////////////////////////////////////////////////
                        if (name.indexOf("(int)") > 0) {
                            // get Method Name from outObj
                            name = name.substring(name.indexOf(SET) + SET.length(), name.indexOf(JAVA1));

                            String getMethodName = "get" + name;
                            String setMethodName = "set" + name;
                            // copy value to outObj from inObj
                            Method g_method = outClass.getMethod(setMethodName, INT_TYPES);
                            Method s_method = inClass.getMethod(getMethodName, sArgTypes);
                            Object t = s_method.invoke(inObj, sArgTypes);
                            if (t != null) {
                                Object[] val = { t };
                                g_method.invoke(outObj, val);
                            }
                        } else if (name.indexOf("(int[])") > 0) {
                            // get Method Name from outObj
                            name = name.substring(name.indexOf(SET) + SET.length(), name.indexOf(JAVA1));

                            String getMethodName = "get" + name;
                            String setMethodName = "set" + name;
                            // copy value to outObj from inObj
                            Method g_method = outClass.getMethod(setMethodName, INT_GROUP_TYPES);
                            Method s_method = inClass.getMethod(getMethodName, sArgTypes);
                            Object t = s_method.invoke(inObj, sArgTypes);
                            if (t != null) {
                                Object[] val = { t };
                                g_method.invoke(outObj, val);
                            }
                        } else {
                            // get Method Name from outObj
                            name = name.substring(name.indexOf(SET) + SET.length(), name.indexOf(JAVA));

                            String getMethodName = "get" + name;
                            String setMethodName = "set" + name;
                            // copy value to outObj from inObj
                            Method g_method = outClass.getMethod(setMethodName, gArgTypes);
                            Method s_method = inClass.getMethod(getMethodName, sArgTypes);
                            Object t = s_method.invoke(inObj, sArgTypes);
                            if (t != null) {
                                Object[] val = { t };
                                g_method.invoke(outObj, val);
                            }
                        }
                    } catch (Exception ex) {
                        // ex.printStackTrace();
                    }
                }
            }

        } catch (Exception ex) {
            // ex.printStackTrace();
        }
    }

    /**
     * <H3>トリム.</H3>
     * 
     * <PRE>
     * 
     * 先頭と最後の空白は削除する。 <br>
     * 
     * </PRE>
     * 
     * @param prmString
     *            対象文字列
     * @return String 空白を削除した文字列
     */
    public static String trimSpace(String prmString) {

        // 入力文字のnull・空文字チェック
        if (CheckUtil.isEmpty(prmString)) {
            return prmString;
        }

        // trim
        return prmString.trim();
    }

    /**
     * 数値フォーマット(数値をカンマ付きに編集)を行う
     * 
     * @param strInput
     *            編集前数値
     * @return String 編集後数値
     */
    public static String numFormat(String strInput) {

        boolean blnMinus = false;
        boolean blnPlus = false;
        int intStrIndex = 0;
        StringBuffer strbuffData = new StringBuffer();
        int intStrLength = 0;

        if (strInput == null || strInput.length() == 0) {
            return strInput;
        }

        if (strInput.substring(0, 1).equals("-")) {
            blnMinus = true;
            strInput = strInput.substring(1);
        } else if (strInput.substring(0, 1).equals("+")) {
            blnPlus = true;
            strInput = strInput.substring(1);
        }

        // 小数点のチェック
        String strInt = null;
        String strDec = null;

        int intDecPos = strInput.indexOf(".");

        if (intDecPos >= 0) {
            strInt = strInput.substring(0, intDecPos);
            strDec = strInput.substring(intDecPos + 1, strInput.length());
        } else {
            strInt = strInput;
        }

        if (CheckUtil.isEmpty(strInt) || !CheckUtil.checkNumber(strInt)) {
            return null;
        }
        if (strDec != null && !CheckUtil.checkNumber(strDec)) {
            return null;
        }

        intStrLength = strInt.length();
        for (; intStrIndex < intStrLength - 3; intStrIndex = intStrIndex + 3) {
            strbuffData.insert(0, strInt.substring(intStrLength - intStrIndex - 3, intStrLength - intStrIndex));
            strbuffData.insert(0, ",");
        }

        strbuffData.insert(0, strInt.substring(0, intStrLength - intStrIndex));

        if (blnMinus) {
            strbuffData.insert(0, "-");
        } else if (blnPlus) {
            strbuffData.insert(0, "+");
        }

        if (!CheckUtil.isEmpty(strDec)) {
            strbuffData.append("." + strDec);
        }

        return strbuffData.toString();

    }

    /**
     * 数値フォーマット(数値をカンマ付きに編集)を行う
     * 
     * @param intInput
     *            編集前数値
     * @return String 編集後数値
     */
    public static String numFormat(int intInput) {
        return numFormat(Integer.toString(intInput));
    }

    /**
     * ms系unicodeを標準のunicodeにう変換
     * 
     * @param s
     *            変換したい文字列
     * @return String 変換した文字列
     */
    public static String msToStd(String s) {
        StringBuffer sb = new StringBuffer();
        char c;
        if (s == null || s.length() == 0) {
            return s;
        }
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            switch (c) {
            case 0xff3c: // FULLWIDTH REVERSE SOLIDUS ->
                c = 0x005c; // REVERSE SOLIDUS
                break;
            case 0xff5e: // FULLWIDTH TILDE ->
                c = 0x301c; // WAVE DASH
                break;
            case 0x2225: // PARALLEL TO ->
                c = 0x2016; // DOUBLE VERTICAL LINE
                break;
            case 0xff0d: // FULLWIDTH HYPHEN-MINUS ->
                c = 0x2212; // MINUS SIGN
                break;
            case 0xffe0: // FULLWIDTH CENT SIGN ->
                c = 0x00a2; // CENT SIGN
                break;
            case 0xffe1: // FULLWIDTH POUND SIGN ->
                c = 0x00a3; // POUND SIGN
                break;
            case 0xffe2: // FULLWIDTH NOT SIGN ->
                c = 0x00ac; // NOT SIGN
                break;
            }
            sb.append(c);
        }
        return new String(sb);
    }

    /**
     * 標準のunicodeをms系unicodeに変換
     * 
     * @param s
     *            変換したい文字列
     * @return String 変換した文字列
     */
    public static String stdToMs(String s) {
        StringBuffer sb = new StringBuffer();
        char c;
        if (s == null || s.length() == 0) {
            return s;
        }
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            switch (c) {
            case 0x005c: // REVERSE SOLIDUS ->
                c = 0xff3c; // FULLWIDTH REVERSE SOLIDUS
                break;
            case 0x301c: // WAVE DASH ->
                c = 0xff5e; // FULLWIDTH TILDE
                break;
            case 0x2016: // DOUBLE VERTICAL LINE ->
                c = 0x2225; // PARALLEL TO
                break;
            case 0x2212: // MINUS SIGN ->
                c = 0xff0d; // FULLWIDTH HYPHEN-MINUS
                break;
            case 0x00a2: // CENT SIGN ->
                c = 0xffe0; // FULLWIDTH CENT SIGN
                break;
            case 0x00a3: // POUND SIGN ->
                c = 0xffe1; // FULLWIDTH POUND SIGN
                break;
            case 0x00ac: // NOT SIGN ->
                c = 0xffe2; // FULLWIDTH NOT SIGN
                break;
            }
            sb.append(c);
        }
        return new String(sb);
    }

    /**
     * <PRE>
     * 
     * 文字列の前のゼロを取り除く。 <br>
     * 
     * </PRE>
     * 
     * @param strParam
     *            対象文字列
     * @return String 編集した文字列
     */
    public static String cutBeforeZero(String strParam) {

        String strReturn = "";
        long lngReturn = 0;
        if (strParam != null && !"".equals(strParam)) {
            if (strParam.length() > 0) {
                lngReturn = Long.parseLong(strParam);
                strReturn = Long.toString(lngReturn);
            }
        }
        return strReturn;

    }

    /**
     * <PRE>
     * 
     * NULL -> ブランクを編集する。 <br>
     * 
     * </PRE>
     * 
     * @param strParam
     *            文字列
     * @return String 編集した文字列
     */
    public static String isNulltoBlank(String strParam) {

        String strReturn = strParam;
        if (strParam == null) {
            strReturn = "";
        }

        return strReturn;
    }

    /**
     * 
     * 指定された桁数になるよう左から指定された値を埋めます。
     * 
     * @param data
     *            対象文字列
     * @param fill
     *            埋める値
     * @param length
     *            桁数
     * @return ( n * 0 ) + データ
     */
    public static String fillInLeft(String data, char fill, int length) {

        StringBuffer fmtData = new StringBuffer();
        int zeroSu;

        if (data == null) {

            data = "";
            zeroSu = length;

        } else if (data.getBytes().length > length) {

            zeroSu = 0;

        } else {

            zeroSu = length - data.getBytes().length;

        }

        for (int i = 0; i < zeroSu; i++)
            fmtData.append(fill);

        fmtData.append(data);
        return fmtData.toString();

    }

    /**
     * 
     * 指定された桁数になるよう右から指定された値を埋めます。
     * 
     * @param data
     *            対象文字列
     * @param fill
     *            埋める値
     * @param len
     *            桁数
     * @return データ + ( fill * len )
     */
    public static String fillInRight(String data, char fill, int len) {

        // 指定長が「0」の場合も「0」を返す。
        if (len == 0)
            return data;

        // 基本情報が「null」もしくは、指定長より長い場合は加工処理をしない
        if (data == null || data.getBytes().length > len)
            return data;

        int diffLen = len - data.getBytes().length;
        StringBuffer fmtStr = new StringBuffer(data);

        for (int i = 0; i < diffLen; i++)
            fmtStr.append(fill);

        return fmtStr.toString();

    }

    /**
     * 
     * カンマを除去します。
     * 
     * @param strInput
     *            カンマを含む文字列データ
     * @return カンマを除去された文字列
     */
    public static String removeComma(String strInput) {
        if (strInput == null) {
            return null;
        }
        if (strInput.length() < 1 || strInput.equals("")) {
            return null;
        }
        StringBuffer stbValue = new StringBuffer(strInput);

        for (int i = 0; i < stbValue.length(); i++) {
            char parts = stbValue.charAt(i);
            if (parts == ',') {
                // カンマを発見したら除去し、オフセットを１つ戻す
                stbValue = stbValue.deleteCharAt(i);
                i--;
            }
        }

        return stbValue.toString();

    }

    public static String htmlSpecialCharacterConvert(final String jnlData) {
        StringBuffer jnlBuffer = new StringBuffer();
        String journalData = jnlData;
        // 改行コード(Byte列)
        byte[] BYTE_CRLF = { 0x0d, 0x0a };

        // 改行コード
        String CRLF = new String(BYTE_CRLF);

        int startIdx;
        int endIdx;

        // ジャーナルデータ分ループ
        for (startIdx = 0, endIdx = 0; endIdx < journalData.length(); endIdx++) {

            // 特殊文字か判定
            switch (journalData.charAt(endIdx)) {
            case '<':
                jnlBuffer.append(journalData.substring(startIdx, endIdx));
                jnlBuffer.append("&lt;");
                startIdx = endIdx + 1;
                break;
            case '>':
                jnlBuffer.append(journalData.substring(startIdx, endIdx));
                jnlBuffer.append("&gt;");
                startIdx = endIdx + 1;
                break;
            case '&':
                jnlBuffer.append(journalData.substring(startIdx, endIdx));
                jnlBuffer.append("&amp;");
                startIdx = endIdx + 1;
                break;
            case '"':
                jnlBuffer.append(journalData.substring(startIdx, endIdx));
                jnlBuffer.append("&quot;");
                startIdx = endIdx + 1;
                break;
            default:
                break;
            }
        }
        if (startIdx < journalData.length()) {
            jnlBuffer.append(journalData.substring(startIdx));
        }
        // CRLFを<br>に変換する
        startIdx = 0;
        endIdx = 0;
        journalData = jnlBuffer.toString();
        jnlBuffer.delete(0, jnlBuffer.length());

        while ((endIdx = journalData.indexOf(CRLF, startIdx)) > 0) {
            jnlBuffer.append(journalData.substring(startIdx, endIdx));
            jnlBuffer.append("<br>");
            startIdx = endIdx + 2;
        }
        if (startIdx >= 0 && startIdx < journalData.length()) {
            jnlBuffer.append(journalData.substring(startIdx));
        }
        return jnlBuffer.toString();
    }

    /**
     * ヘッドゼロ補填
     * 
     * @param keyWord
     *            ：ヘッドゼロ補填対象文字列
     * @param len
     *            ：文字列レンス
     * @return <code>String</code>：ヘッドゼロ補填結果の文字列
     */
    public static String headZeroEdit(final String keyWord, int len) {

        // 入力データのコピー
        String ret = "";

        if (keyWord == null || "".equals(keyWord)) {
            return ret;
        }
        // ヘッドゼロサプレスを実行
        for (int i = 0; i < len - keyWord.length(); i++) {
            ret = ret + "0";
        }
        // 変換結果文字列を返す
        return ret + keyWord;
    }
    /** 
     * 二进制转字符串 
     * @param b 
     * @return 
     */  
    public static String byte2hex(byte[] b) // 二进制转字符串  
    {  
        StringBuffer sb = new StringBuffer();  
        String stmp = "";  
        for (int n = 0; n < b.length; n++) {  
            stmp = Integer.toHexString(b[n] & 0XFF);  
            if (stmp.length() == 1) {  
                sb.append("0" + stmp);  
            } else {  
                sb.append(stmp);  
            }  
        }  
        return sb.toString();  
    }  
    /** 
     * 字符串转二进制 
     * @param str 要转换的字符串 
     * @return  转换后的二进制数组 
     */  
    public byte[] hex2byte(String str) { // 字符串转二进制  
        if (str == null)  
            return null;  
        str = str.trim();  
        int len = str.length();  
        if (len == 0 || len % 2 == 1)  
            return null;  
        byte[] b = new byte[len / 2];  
        try {  
            for (int i = 0; i < str.length(); i += 2) {  
            	b[i / 2]=(byte)Integer.parseInt(str.substring(i, i + 2),16);
                //b[i / 2] = (byte) Integer.decode("0X" + str.substring(i, i + 2)).intValue();  
            }  
            return b;
    
        } catch (Exception e) {  
            return null;  
        }  
    }  
}
