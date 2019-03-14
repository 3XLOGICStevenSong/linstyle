/**
 * All rights Reserved, Designed By dbridge.com.cn
 * @Title:  TrainCategoryUpdateStatusDTO.java
 * @Package cn.com.dbridge.jtraining.framework.dto
 * @Description:    TODO(用一句话描述该文件做什么) 
 * @author: 宁旭
 * @date:   2019年1月8日 下午1:03:59
 * @version V1.0
 * @Copyright: 2019 dbridge.com.cn Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
package cn.com.dbridge.jtraining.framework.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName:  TrainCategoryUpdateStatusDTO
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: 宁旭
 * @date:   2019年1月8日 下午1:03:59
 * 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class TrainCategoryUpdateStatusDTO implements Serializable {

    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 6611726815040564465L;

    /**
     * トレーニング種別
     */
    @NotNull(message = "{trainType.notnull}")
    @ApiModelProperty(value = "トレーニング種別")
    private Integer trainType;

    /**
     * 有効フラグ(0:有効 1:無効)
     */
    @NotNull(message = "{useStatus.notnull}")
    @Range(min = 0, max = 1, message = "誤った状態")
    @ApiModelProperty(value = "有効フラグ(0:有効 1:無効)")
    private Byte useStatus;

}
