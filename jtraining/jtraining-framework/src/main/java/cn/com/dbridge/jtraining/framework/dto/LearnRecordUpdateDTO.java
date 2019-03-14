package cn.com.dbridge.jtraining.framework.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * @ClassName: LearnRecordUpdateDTO
 * @Description:勉強記録更新DTO
 * @author: 陈军
 * @date: 2018年12月17日 下午3:58:31
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved. 注意：本内容仅限于必捷必信息技术有限公司
 *             内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class LearnRecordUpdateDTO implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
    private static final long serialVersionUID = -8870129331980018154L;
    /**
	 * 学生番号
	 */
	private String studentId;

    /**
	 * トレーニングID
	 */
    private Integer trainId;

    /**
	 * トレーニング資料番号
	 */
	private Integer sourceNo;

    /**
	 * トレーニング資料勉強規模(ビデオ:秒 テキスト:頁)
	 */
	private Integer sourceLearnLength;
}