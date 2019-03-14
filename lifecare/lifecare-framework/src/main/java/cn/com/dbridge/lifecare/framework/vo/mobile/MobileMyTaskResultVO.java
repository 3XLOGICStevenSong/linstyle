package cn.com.dbridge.lifecare.framework.vo.mobile;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName:MobileMyTaskResultVO
 * @Description:TODO
 * @author:郭健
 * @date:2019年1月3日 下午3:53:00
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
public class MobileMyTaskResultVO {

    /**
     * 总条数
     */
    @ApiModelProperty(value = "总条数")
    private int total;

    /**
     * 结果集
     */
    @ApiModelProperty(value = "结果集")
    private List<MobileMyTaskVO> mobileMyTaskVOList;
}
