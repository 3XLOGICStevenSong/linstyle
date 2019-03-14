package cn.com.dbridge.lifecare.framework.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @ClassName:  WebPageResult
 * @Description: 任务用Web分页操作返回对象模型
 * @author: 郭建
 * @param <T>
 * @param <T>
 * @date:   2019年01月05日  16:54:49
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
@Getter
@Setter
public class TaskWebPageResult<T> extends WebPageResult<T> {
    @ApiModelProperty(value = "预约总时长")
    private String totalOrderDuration;
    @ApiModelProperty(value = "服务总时长")
    private String totalServiceDuration;

    public TaskWebPageResult(Integer statusCode, String msg, T rows, Long total, String totalOrderDuration, String totalServiceDuration) {
        super(statusCode, msg, rows, total);
        this.totalOrderDuration = totalOrderDuration;
        this.totalServiceDuration = totalServiceDuration;
    }
}
