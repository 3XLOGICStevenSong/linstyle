package cn.com.dbridge.jtraining.framework.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: TrainSourceDTO
 * @Description:トレーニング資料DTO
 * @author: 陈军
 * @date: 2018年12月17日 下午3:58:31
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved. 注意：本内容仅限于必捷必信息技术有限公司
 *             内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class TrainSourceDTO implements Serializable {
    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
	private static final long serialVersionUID = -2840199734641945511L;
    /**
     * トレーニング資料番号
     */
	@ApiModelProperty(value = "トレーニング資料番号")
    private Integer sourceNo;

    /**
	 * トレーニング資料パス
	 */
	@ApiModelProperty(value = "トレーニング資料パス")
	private String sourcePath;
}