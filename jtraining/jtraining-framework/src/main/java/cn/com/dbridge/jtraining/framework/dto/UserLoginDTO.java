package cn.com.dbridge.jtraining.framework.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UserLoginDTO {
	/**
	 * 番号
	 */
	@NotNull
	private String no;
	/**
	 * パスワード
	 */
	@NotNull
	private String password;
}
