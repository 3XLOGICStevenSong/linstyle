/**
 * All rights Reserved, Designed By dbridge.com.cn
 * @Title:  TrainItemVOList.java
 * @Package cn.com.dbridge.jtraining.framework.vo
 * @Description:    TODO(用一句话描述该文件做什么) 
 * @author: 宁旭
 * @date:   2018年12月13日 上午11:11:20
 * @version V1.0
 * @Copyright: 2018 dbridge.com.cn Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
package cn.com.dbridge.jtraining.framework.vo;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * @ClassName:  TrainItemVOList
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: 宁旭
 * @date:   2018年12月13日 上午11:11:20
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class TrainInformationVO {

    /**
     * トレーニングID
     */
    private Integer trainId;

    /**
     * トレーニング種別
     */
    private Integer trainType;

    /**
     * トレーニングタイトル
     */
    private String trainTitle;

    /**
     * トレーニング教师
     */
    private String trainTeacher;

    /**
     * トレーニング紹介
     */
    private String trainDesc;

    /**
     * トレーニング画像
     */
    private String trainDraw;

    /**
     * 更新日
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updateDate;

}
