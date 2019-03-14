package com.djb.highway.road.dtoclient;

/**
 * 类说明
 * 
 * @author LiWm E-mail:
 * @version 创建时间：2014年7月11日 下午2:43:47
 */
public class OverpassDetailClientDTO {
    /**
     * 出口名字
     */
    private String direction_content;

    /**
     * 出口连接
     */
    private String pic_path;

    public String getDirection_content() {
        return direction_content;
    }

    public void setDirection_content(String direction_content) {
        this.direction_content = direction_content;
    }

    public String getPic_path() {
        return pic_path;
    }

    public void setPic_path(String pic_path) {
        this.pic_path = pic_path;
    }

}
