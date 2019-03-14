package cn.com.dbridge.jtraining.framework.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: InformationUpdateVO
 * @Description:個人情報変更VO
 * @author: 陈军
 * @date: 2018年12月17日 下午3:58:31
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved. 注意：本内容仅限于必捷必信息技术有限公司
 *             内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class InformationUpdateVO {

	/**
	 * 番号
	 */
	@ApiModelProperty(value = "番号")
	private String no;

	/**
	 * 名前
	 */
	@ApiModelProperty(value = "名前")
	private String name;

	/**
	 * 誕生日
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	@ApiModelProperty(value = "誕生日")
	private Date birthday;

	/**
	 * 年齢
	 */
	@ApiModelProperty(value = "年齢")
	private int age;

	/**
	 * 更新件数
	 */
	@ApiModelProperty(value = "更新件数")
	private int updatedCount;

    /**
	 * url
	 */
	@ApiModelProperty(value = "url")
	private String url;
}