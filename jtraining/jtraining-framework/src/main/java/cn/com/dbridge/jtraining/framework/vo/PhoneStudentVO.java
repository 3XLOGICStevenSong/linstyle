/**
 * All rights Reserved, Designed By www.tydic.com
 * @Title:PhoneStudentVO.java
 * @Package cn.com.dbridge.jtraining.framework.vo
 * @Description:
 * @author:郭健
 * @date:2018年12月18日 上午11:00:24
 * @version V1.0
 * @Copyright: 2018 www.tydic.com Inc.すべての権利を保有します。
 * 注：このコンテンツは、DJB Information Technology Co.Ltd.の内部流通のためのものであり、漏洩および他の商業目的での使用は禁じられています。
 */
package cn.com.dbridge.jtraining.framework.vo;

import lombok.Data;

/**
 * @ClassName:PhoneStudentVO
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author:郭健
 * @date:2018年12月18日 上午11:00:24
 *
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class PhoneStudentVO {
    private String no;
    private String name;
    private Byte sex;
    private Integer age;
    private String personDraw;
}
