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
package cn.com.dbridge.jtraining.dao.po;

import java.io.Serializable;

import lombok.Data;

/**
 * @ClassName: TrainListQueryPO
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: 陈军
 * @date: 2018年12月17日 下午3:58:31
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved. 注意：本内容仅限于必捷必信息技术有限公司
 *             内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class TrainListQueryPO implements Serializable {
    /**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 2712964119854260148L;

	/**
	 * 学生番号
	 */
	private String studentId;

    /**
	 * トレーニングID
	 */
	private Integer trainId;

	/**
	 * トレーニング資料種別
	 */
	private Integer sourceType;
}
