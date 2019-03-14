package cn.com.dbridge.lifecare.dao.po;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 
 * @ClassName:  RolePO
 * @Description:角色PO
 * @author: linh
 * @date:   2018年12月26日 下午1:05:26
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class RolePO implements Serializable {
	
	private Integer roleId;
	
	private String roleName;
	
    private String status;
	
	private Date createTime;
	
	private Date updateTime;

	private String  memo;
	
    private List<ResponsibilityPO> responsibiltyList;

    private String roleNamePinyin;

    private static final long serialVersionUID = -8530710278034239646L;
}
