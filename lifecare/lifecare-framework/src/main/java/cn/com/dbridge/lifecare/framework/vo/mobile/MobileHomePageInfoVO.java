package cn.com.dbridge.lifecare.framework.vo.mobile;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
/**
 * 
 * @ClassName:HomePageInfoVO
 * @Description:主页VO
 * @author:陈健飞
 * @date:2018年12月27日 下午12:54:03
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
@JsonInclude(value=Include.NON_NULL)
public class MobileHomePageInfoVO implements Serializable {
    private String title1;
    private String content1;
    private String title2;
    private String content2;
    private String title3;
    private String content3;
    private String title4;
    private String content4;
    private static final long serialVersionUID = 1L;
}
