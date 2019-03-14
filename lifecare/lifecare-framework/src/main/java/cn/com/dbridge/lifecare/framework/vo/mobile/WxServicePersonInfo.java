package cn.com.dbridge.lifecare.framework.vo.mobile;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 
 * @ClassName: WxMyTaskQueryVO
 * @Description:微信任务查询接口
 * @author: 陈健飞
 * @date: 2019年1月8日 上午11:07:18
 * 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *             注意：本内容仅限于必捷必信息技术有限公司 内部传阅，禁止外泄以及用于其他的商业目
 */
@JsonInclude(value = Include.NON_NULL)
@Data
public class WxServicePersonInfo {
    /**
     * 客户ID
     */
    @ApiModelProperty(value = "客户ID")
    private Integer customId;
    /**
     * 客户真实姓名
     */
    @ApiModelProperty(value = "客户真实姓名")
    private String realName;
    /**
     * 头像地址
     */
    @ApiModelProperty(value = "头像地址")
    private String img;
    /**
     * 客户与服务人员间的任务DTO
     */
    @ApiModelProperty(value = "客户与服务人员间的任务DTO")
    private List<WxCustomServicePersonTaskVO> taskVoList;
}
