/**
 * All rights Reserved, Designed By dbridge.com.cn
 * @Title:  pageDTO.java
 * @Package cn.com.dbridge.jtraining.framework.dto
 * @Description:    TODO(用一句话描述该文件做什么) 
 * @author: 宁旭
 * @date:   2018年12月21日 下午5:20:19
 * @version V1.0
 * @Copyright: 2018 dbridge.com.cn Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
package cn.com.dbridge.jtraining.framework.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName:  pageDTO
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: 宁旭
 * @date:   2018年12月21日 下午5:20:19
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class PageDTO {

    @ApiModelProperty(value = "開始ページ番号")
    private Integer offset;

    @ApiModelProperty(value = "1ページあたりのページ数")
    private Integer limit;
}
