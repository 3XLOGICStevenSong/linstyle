/**
 * All rights Reserved, Designed By dbridge.com.cn
 * @Title:  ValidI18nConfig.java
 * @Package cn.com.dbridge.jtraining.config
 * @Description:    TODO(用一句话描述该文件做什么) 
 * @author: 陈健飞 
 * @date:   2018年12月7日 上午8:41:46
 * @version V1.0
 * @Copyright: 2018 dbridge.com.cn Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
package cn.com.dbridge.jtraining.config;

import javax.validation.Validator;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * @ClassName:  ValidI18nConfig
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: 陈健飞
 * @date:   2018年12月7日 上午8:41:46
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@SpringBootConfiguration
public class ValidI18nConfig {
    public ResourceBundleMessageSource getMessageSource() throws Exception {
        ResourceBundleMessageSource rbms = new ResourceBundleMessageSource();
        rbms.setDefaultEncoding("UTF-8");
        rbms.setBasenames("i18n/errors/ErrorMessages",
                "i18n/prompt/PromptMessages",
                "i18n/validation/ValidationMessages");
        return rbms;
    }

    @Bean
    public Validator getValidator() throws Exception {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(getMessageSource());
        return validator;
    }
}
