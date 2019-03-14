package cn.com.dbridge.jtraining.framework.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: PasswordChangeDTO
 * @Description:パスワード変更DTO
 * @author: 陈军
 * @date: 2018年12月17日 下午3:58:31
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved. 注意：本内容仅限于必捷必信息技术有限公司
 *             内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class PasswordChangeDTO implements Serializable {
    /**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 2717964119874260548L;
	/**
	 * 番号
	 */
	@ApiModelProperty(value = "番号")
    private String no;

    /**
     * パスワード
     */
	@ApiModelProperty(value = "パスワード")
    private String password;

    /**
	 * 新パスワード
	 */
	@ApiModelProperty(value = "新パスワード")
	private String newPassword;
}