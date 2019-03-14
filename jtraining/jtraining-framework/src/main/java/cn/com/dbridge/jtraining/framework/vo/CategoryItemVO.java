/**
 * All rights Reserved, Designed By dbridge.com.cn
 * @Title:  CategoryItemVO.java
 * @Package cn.com.dbridge.jtraining.framework.vo
 * @Description:    TODO(用一句话描述该文件做什么) 
 * @author: 宁旭
 * @date:   2018年12月13日 上午11:13:29
 * @version V1.0
 * @Copyright: 2018 dbridge.com.cn Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
package cn.com.dbridge.jtraining.framework.vo;

import java.util.List;

import lombok.Data;

/**
 * @ClassName:  CategoryItemVO
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: 宁旭
 * @date:   2018年12月13日 上午11:13:29
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class CategoryItemVO {

    private List<TrainCategoryInformationVO> trainCategoryInformationVOList;
    
    private List<TrainInformationVO> trainInformationVOList;

    private Long count;
}
