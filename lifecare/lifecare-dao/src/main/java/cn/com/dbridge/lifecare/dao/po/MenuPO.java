package cn.com.dbridge.lifecare.dao.po;

import java.io.Serializable;

import lombok.Data;
/**
 * 
 * @ClassName:  MenuPO
 * @Description:菜单PO
 * @author: 陈健飞
 * @date:   2019年1月15日 下午7:10:36
 * 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class MenuPO implements Serializable {
    /**
     * 菜单编号
     */
    private Integer id;
    /**
     * 父菜单编号
     */
    private Integer parentId;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜单地址
     */
    private String url;
    /**
     * 菜单顺序
     */
    private Integer order;

    private static final long serialVersionUID = 1L;
}
