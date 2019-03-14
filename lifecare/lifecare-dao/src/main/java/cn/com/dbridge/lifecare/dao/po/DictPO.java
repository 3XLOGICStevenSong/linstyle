package cn.com.dbridge.lifecare.dao.po;

import java.io.Serializable;

import lombok.Data;
/**
 * 
 * @ClassName:  DictPO
 * @Description：区域信息PO
 * @author: 陈健飞
 * @date:   2019年1月15日 下午7:09:34
 * 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class DictPO implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * code
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 颜色
     */
    private String color;

    /**
     * 类别
     */
    private String type;

    /**
     * t_dict
     */
    private static final long serialVersionUID = 1L;
}