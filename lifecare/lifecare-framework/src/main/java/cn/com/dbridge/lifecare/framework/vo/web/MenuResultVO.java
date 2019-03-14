package cn.com.dbridge.lifecare.framework.vo.web;

import java.io.Serializable;

import com.alibaba.fastjson.JSONArray;

import lombok.Data;

@Data
public class MenuResultVO implements Serializable {

    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 1L;

    private JSONArray menuList;

    private String userNumber;
}
