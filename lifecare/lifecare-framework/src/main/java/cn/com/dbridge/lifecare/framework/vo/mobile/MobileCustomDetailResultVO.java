package cn.com.dbridge.lifecare.framework.vo.mobile;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName:MobileCustomDetailVO
 * @Description:TODO
 * @author:郭健
 * @date:2019年1月5日 上午11:22:29
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
public class MobileCustomDetailResultVO {
    /**
     * 客户主键
     */
    @ApiModelProperty(value = "客户主键")
    private Integer customId;

    /**
     * 相片地址
     */
    @ApiModelProperty(value = "相片地址")
    private String img;

    /**
     * 客户姓名
     */
    @ApiModelProperty(value = "客户姓名")
    private String realName;

    /**
     * 客户相关订单信息
     */
    @ApiModelProperty(value = "客户相关订单信息")
    private List<MobileCustomDetailVO> mobileCustomDetailVOList;

}
