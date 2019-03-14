package cn.com.dbridge.jtraining.framework.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class UserLoginVO {
	/**
	 * 番号
	 */
	private String no;
	/**
	 * 名前
	 */
	private String name;
	/**
	 * 性別
	 */
	private Byte sex;
	/**
	 * パスワード
	 */
	@JsonIgnore
	private String password;
	/**
	 * 誕生日
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	private Date birthday;
	/**
	 * 年齢
	 */
	private Integer age;
	/**
	 * 本人画像
	 */
	private String personDraw;
	/**
	 * 類別(0:管理员 1：老师 2：学生)
	 */
	private Byte type;
	/**
	 * token
	 */
	private String token;
}
