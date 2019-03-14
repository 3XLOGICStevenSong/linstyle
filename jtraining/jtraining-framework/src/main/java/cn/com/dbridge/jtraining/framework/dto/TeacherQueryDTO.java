/**
 * All rights Reserved, Designed By dbridge.com.cn
 * @Title:  TeacherQueryDTO.java
 * @Package cn.com.dbridge.jtraining.framework.dto
 * @Description:    TODO(用一句话描述该文件做什么) 
 * @author: 宁旭
 * @date:   2018年12月17日 上午11:42:01
 * @version V1.0
 * @Copyright: 2018 dbridge.com.cn Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
package cn.com.dbridge.jtraining.framework.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName:  TeacherQueryDTO
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: 宁旭
 * @date:   2018年12月17日 上午11:42:01
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class TeacherQueryDTO {

    @ApiModelProperty(value = "トレーニング種別")
    private Integer trainType;
}
