/**
 * All rights Reserved, Designed By dbridge.com.cn
 * @Title:  MainConfig.java
 * @Package lifecare.web.config
 * @Description:    核心配置内容
 * @author: 陈健飞 
 * @date:   2018年11月27日 下午4:16:47
 * @version V1.0
 * @Copyright: 2018 dbridge.com.cn Inc. All rights reserved.
 *  注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
package cn.com.dbridge.lifecare.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * @ClassName:  MainConfig
 * @Description:核心配置内容
 * @author: 陈健飞
 * @date:   2018年12月3日 上午11:00:17
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@SpringBootConfiguration
@ComponentScan(basePackages = "cn.com")
@MapperScan(basePackages = "cn.com.dbridge.lifecare.dao.respository")
@EnableCaching
@EnableTransactionManagement
@EnableScheduling
public class MainConfig {

}
