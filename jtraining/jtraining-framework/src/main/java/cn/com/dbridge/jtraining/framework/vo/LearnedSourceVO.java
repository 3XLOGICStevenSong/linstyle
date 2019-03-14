/**
 * All rights Reserved, Designed By dbridge.com.cn
 * @Title:  TeacherVO.java
 * @Package cn.com.dbridge.jtraining.framework.vo
 * @Description:    TODO(用一句话描述该文件做什么) 
 * @author: 宁旭
 * @date:   2018年12月13日 下午7:51:37
 * @version V1.0
 * @Copyright: 2018 dbridge.com.cn Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
package cn.com.dbridge.jtraining.framework.vo;

import java.util.List;

import lombok.Data;

/**
 * @ClassName: LearnedSourceVO
 * @Description:学生の勉強完了のトレーニング資料情報
 * @author: 陈军
 * @date: 2018年12月17日 下午7:51:37
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved. 注意：本内容仅限于必捷必信息技术有限公司
 *             内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class LearnedSourceVO {
	/**
	 * トレーニングID
	 */
	private Integer trainId;

	/**
	 * トレーニングタイトル
	 */
	private String trainTitle;

	/**
	 * 勉強完了の資料パーセント
	 */
	private String trainPercent;

	/**
	 * トレーニング資料_ビデオ
	 */
	private List<LearnedSourceItemVO> videoList;

	/**
	 * トレーニング紹介_テキスト
	 */
	private List<LearnedSourceItemVO> textList;

}
