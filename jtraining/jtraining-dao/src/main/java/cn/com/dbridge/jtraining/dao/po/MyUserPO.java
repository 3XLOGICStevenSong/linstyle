/**
 * All rights Reserved, Designed By www.tydic.com
 * @Title:MyUserPO.java
 * @Package cn.com.dbridge.jtraining.dao.po
 * @Description:
 * @author:郭健
 * @date:2018年12月18日 下午4:27:16
 * @version V1.0
 * @Copyright: 2018 www.tydic.com Inc.すべての権利を保有します。
 * 注：このコンテンツは、DJB Information Technology Co.Ltd.の内部流通のためのものであり、漏洩および他の商業目的での使用は禁じられています。
 */
package cn.com.dbridge.jtraining.dao.po;

import lombok.Data;

/**
 * @ClassName:MyUserPO
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author:郭健
 * @date:2018年12月18日 下午4:27:16
 *
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class MyUserPO {
    private String no;
    private String name;
    private String personDraw;
    private String remarks;
    private String typeName;
}
