package cn.com.dbridge.lifecare.framework.vo.web;

import java.io.Serializable;

import lombok.Data;

@Data
public class MenuVO implements Serializable {

    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer parentId;
    private String name;
    private String url;
    private Integer order;

}
