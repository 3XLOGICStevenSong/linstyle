package cn.com.dbridge.lifecare.framework.dto.web;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 
 * @ClassName:  WebHomePageInfoDTO
 * @Description:主页信息DTO
 * @author: 王林江
 * @date:   2019年01月10日 下午12:56:16
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class WebHomePageInfoDTO implements Serializable {
	
	@NotNull(message = "标题不能为空")
	private String title1;
	
	@Length(min = 0, max = 4000, message = "内容长度不能大于4000个字符")
    private String content1;
	
	@NotNull(message = "标题不能为空")
    private String title2;
	
    @Length(min = 0, max = 4000, message = "内容长度不能大于4000个字符")
    private String content2;
    
    @NotNull(message = "标题不能为空")
    private String title3;
    
    @Length(min = 0, max = 4000, message = "内容长度不能大于4000个字符")
    private String content3;
    
    @NotNull(message = "标题不能为空")
    private String title4;
    
    @Length(min = 0, max = 4000, message = "内容长度不能大于4000个字符")
    private String content4;

    /**
     * 登录用户ID
     */
    @ApiModelProperty(value = "登录用户ID")
    @NotNull(message = "登录用户ID设定不正：登录用户ID必须入力")
    private Integer loginUserId;

    /**
     * t_home_page_info
     */
    private static final long serialVersionUID = 1L;
}