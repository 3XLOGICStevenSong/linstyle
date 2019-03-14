package cn.com.dbridge.lifecare.framework.vo.web;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * @ClassName:WebUnassignedTaskVO
 * @Description:TODO
 * @author:郭健
 * @date:2019年1月7日 上午11:27:05
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
@JsonInclude(value=Include.NON_NULL)
public class UnassignedTaskManageVO {
    /**
     * 记录总数
     */
    private Long total;
    /**
     * 服务总时长
     */
    private Integer totalOrderDuration;
    /**
     * 查询结果集
     */
    private List<UnassignedTaskVO> unassignedTaskVOList;
}
