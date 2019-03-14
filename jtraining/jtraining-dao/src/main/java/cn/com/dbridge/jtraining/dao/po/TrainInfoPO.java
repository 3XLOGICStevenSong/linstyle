/**
 * All rights Reserved, Designed By dbridge.com.cn
 * @Title:  TrainItemListPO.java
 * @Package cn.com.dbridge.jtraining.dao.po
 * @Description:    TODO(用一句话描述该文件做什么) 
 * @author: 宁旭
 * @date:   2018年12月11日 下午1:57:39
 * @version V1.0
 * @Copyright: 2018 dbridge.com.cn Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
package cn.com.dbridge.jtraining.dao.po;

import java.io.Serializable;

import lombok.Data;

/**
 * @ClassName: TrainInfoPO
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: 陳軍
 * @date: 2018年12月11日 下午1:57:39
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved. 注意：本内容仅限于必捷必信息技术有限公司
 *             内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class TrainInfoPO implements Serializable{
    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
	private static final long serialVersionUID = -810525249751071874L;

    /**
     * トレーニングID
     */
    private Integer trainId;

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
	 * 教師名前
	 */
	private String name;

	/**
	 * 教師備考
	 */
	private String remarks;

	/**
	 * 教師画像
	 */
	private String personDraw;

}
