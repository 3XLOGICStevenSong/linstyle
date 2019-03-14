/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.com.dbridge.lifecare.framework.enums;
public enum OrderEnum {
    EXCEPTION_TIME(10,"订单异常时间");
    public final Integer value;
    public final String name;
    private OrderEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }
    public static String getDesc(Integer value) {
        OrderEnum[] sexEnum = values();
        for (OrderEnum sex : sexEnum) {
            if (sex.value.equals(value)) {  
                return sex.name;  
            }  
        }  
        return null;  
    }  
}
