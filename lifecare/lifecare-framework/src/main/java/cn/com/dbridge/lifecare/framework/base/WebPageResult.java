/**
 * All rights Reserved, Designed By dbridge.com.cn
 * @Title:  Result.java
 * @Package lifecare.framework.base
 * @Description:    分页操作返回对象模型
 * @author: 陈健飞 
 * @date:   2018年11月22日 下午1:54:49
 * @version V1.0
 * @Copyright: 2018 dbridge.com.cn Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
package cn.com.dbridge.lifecare.framework.base;

import java.util.Collection;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName:  WebPageResult
 * @Description: Web分页操作返回对象模型
 * @author: 王林江
 * @date:   2019年01月05日  16:54:49
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class WebPageResult<T> {
    @ApiModelProperty(value = "状态码")
    private Integer statusCode;
    @ApiModelProperty(value = "当前页数")
    private int offset;
    @ApiModelProperty(value = "每页显示记录数")
    private int limit;
    @ApiModelProperty(value = "描述消息")
    private String msg;
    @ApiModelProperty(value = "记录总数")
    private Long total;
    private T rows;

    public WebPageResult(Integer statusCode, String msg) {
        super();
        this.statusCode = statusCode;
        this.msg = msg;
    }

    @SuppressWarnings("unchecked")
    public WebPageResult(Integer statusCode, String msg, T rows, Long total) {
        this(statusCode, msg);
        this.total = total;
        if (rows != null) {
            this.rows = rows;
        } else if (rows instanceof Collection) {
            this.rows = (T) new JSONArray();
        } else {
            this.rows = (T) new JSONObject();
        }
    }
}
