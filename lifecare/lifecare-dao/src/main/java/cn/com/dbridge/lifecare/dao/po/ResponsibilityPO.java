/**   
* @Title: Responsibility.java 
* @Package com.djb.art.sct.model 
* @Description: TODO(用一句话描述该文件做什么) 
* @author linh  
* @date 2017年11月13日 下午4:10:57 
* @version V1.0   
*/
package cn.com.dbridge.lifecare.dao.po;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 * @ClassName:  ResponsibilityPO
 * @Description:用户与角色关系PO
 * @author: linh
 * @date:   2018年12月26日 下午1:05:14
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class ResponsibilityPO implements Serializable {

    private Integer userId;

    private Integer roleId;

    private static final long serialVersionUID = 5663036768072643301L;
}
