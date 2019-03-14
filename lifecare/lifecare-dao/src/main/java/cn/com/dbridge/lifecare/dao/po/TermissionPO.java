package cn.com.dbridge.lifecare.dao.po;

import java.io.Serializable;

import lombok.Data;
/**
 * 
 * @ClassName:  TermissionPO
 * @Description:角色与菜单关系
 * @author: 陈健飞
 * @date:   2019年1月15日 下午7:17:34
 * 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class TermissionPO implements Serializable {
    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 资源ID
     */
    private Integer resourceId;

    /**
     * t_permission
     */
    private static final long serialVersionUID = 1L;
}