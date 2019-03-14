/**
 * All rights Reserved, Designed By dbridge.com.cn
 * @Title:  TrainMaterialVO.java
 * @Package cn.com.dbridge.jtraining.framework.vo
 * @Description:    TODO(用一句话描述该文件做什么) 
 * @author: 宁旭
 * @date:   2018年12月12日 下午3:22:28
 * @version V1.0
 * @Copyright: 2018 dbridge.com.cn Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
package cn.com.dbridge.jtraining.framework.vo;

import lombok.Data;

/**
 * @ClassName:  TrainMaterialVO
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: 宁旭
 * @date:   2018年12月12日 下午3:22:28
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class TrainMaterialVO {

    /**
     * トレーニング資料番号
     */
    private Integer sourceNo;

    /**
     * トレーニング資料種別
     */
    private Integer sourceType;

    /**
     * トレーニング資料タイトル
     */
    private String sourceTitle;

    /**
     * トレーニング資料紹介
     */
    private String sourceDesc;
}
