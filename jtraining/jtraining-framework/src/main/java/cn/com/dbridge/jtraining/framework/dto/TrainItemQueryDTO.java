/**
 * All rights Reserved, Designed By dbridge.com.cn
 * @Title:  TrainItemQueryDTO.java
 * @Package cn.com.dbridge.jtraining.framework.dto
 * @Description:    TODO(用一句话描述该文件做什么) 
 * @author: 宁旭
 * @date:   2018年12月11日 下午3:58:31
 * @version V1.0
 * @Copyright: 2018 dbridge.com.cn Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
package cn.com.dbridge.jtraining.framework.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName:  TrainItemQueryDTO
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: 宁旭
 * @date:   2018年12月11日 下午3:58:31
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class TrainItemQueryDTO {
    /**
     * トレーニング種別
     */
    @ApiModelProperty(value = "トレーニング種別")
    private Integer trainType;

    /**
     * 開始時間
     */
    @ApiModelProperty(value = "開始時間")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date stratTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "終了時間")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date endTime;

    @ApiModelProperty(value = "スタートページ")
    private Integer offset;

    @ApiModelProperty(value = "1ページあたりのページ数")
    private Integer limit;
}
