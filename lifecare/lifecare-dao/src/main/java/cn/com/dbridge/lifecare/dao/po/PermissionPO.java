package cn.com.dbridge.lifecare.dao.po;

import java.io.Serializable;

import lombok.Data;
/**
 * 
 * @ClassName:  PermissionPO
 * @Description:角色与资源关系PO
 * @author: 陈健飞
 * @date:   2019年1月15日 下午7:14:05
 * 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class PermissionPO implements Serializable  {

    private Integer roleId;

    private Integer resourceId;

    private static final long serialVersionUID = 1L;
}
