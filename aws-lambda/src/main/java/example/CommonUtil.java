package example;

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

 

   

}
