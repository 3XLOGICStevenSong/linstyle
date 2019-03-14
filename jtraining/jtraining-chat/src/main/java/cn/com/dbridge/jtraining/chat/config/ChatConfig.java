package cn.com.dbridge.jtraining.chat.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import cn.com.dbridge.jtraining.chat.SpringUtil;

/**
 * @ClassName: ChatConfig
 * @Description: 配置类
 * @author: 陈健飞
 * @date: 2018-12-13 20:19
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 * 注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@SpringBootConfiguration
public class ChatConfig {
    @Bean
    public SpringUtil getSpingUtil() {
        return new SpringUtil();
    }
}
