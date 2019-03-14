package cn.com.dbridge.lifecare.dao.po;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;


/**
 * 
 * @ClassName:  ResourcePO
 * @Description: 资源PO
 * @author: linh
 * @date:   2018年12月26日 下午1:05:50
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class ResourcePO implements Serializable {
    private Integer resourceId;

    private Integer parentId;

    private String resourceName;

    private String resourceType;

    private String url;

    private String permission;

    private Integer order;

    private Boolean status;

    private Date createTime;

    private Date updateTime;

    private PermissionPO permissions;

    private Integer roleId;

    private static final long serialVersionUID = 957878591829432822L;
}
