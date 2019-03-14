/**
 * All rights Reserved, Designed By www.tydic.com
 * @Title:PhoneUserVO.java
 * @Package cn.com.dbridge.jtraining.framework.vo
 * @Description:
 * @author:郭健
 * @date:2018年12月17日 下午9:01:58
 * @version V1.0
 * @Copyright: 2018 www.tydic.com Inc.すべての権利を保有します。
 * 注：このコンテンツは、DJB Information Technology Co.Ltd.の内部流通のためのものであり、漏洩および他の商業目的での使用は禁じられています。
 */
package cn.com.dbridge.jtraining.framework.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * @ClassName:PhoneUserVO
 * @Description:
 * @author:郭健
 * @date:2018年12月17日 下午9:01:58
 *
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class PhoneUserVO {
    private String no;
    private String name;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date birthday;
    private Integer age;
    private Byte sex;
    private String personDraw;
}
