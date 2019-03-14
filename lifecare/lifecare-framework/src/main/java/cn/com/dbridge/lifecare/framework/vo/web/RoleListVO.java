package cn.com.dbridge.lifecare.framework.vo.web;

import java.util.List;

import cn.com.dbridge.lifecare.framework.base.PageResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @ClassName:  RolePO
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: linh
 * @date:   2018年12月26日 下午1:05:26
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RoleListVO extends PageResult<Object> {

    private List<RoleVO> rows;

    private Integer total;

}
