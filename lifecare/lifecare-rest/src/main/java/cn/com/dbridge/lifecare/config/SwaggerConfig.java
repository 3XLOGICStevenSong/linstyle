/**
 * All rights Reserved, Designed By dbridge.com.cn
 * 
 * @Title: SwaggerConfig.java
 * @Package lifecare.web.config
 * @Description: Swagger插件配置
 * @author: 陈健飞
 * @date: 2018年11月27日 下午4:16:47
 * @version V1.0
 * @Copyright: 2018 dbridge.com.cn Inc. All rights reserved.
 *             注意：本内容仅限于必捷必信息技术有限公司 内部传阅，禁止外泄以及用于其他的商业目
 */
package cn.com.dbridge.lifecare.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Lists;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * 
 * @ClassName:  SwaggerConfig
 * @Description:Swagger插件配置
 * @author: 陈健飞
 * @date:   2018年12月3日 上午10:57:42
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.com.dbridge.lifecare"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Lists.newArrayList(apiKey()));
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("陈健飞", "https://dbridge.com.cn/","chen.jf89@outlook.com");
        return new ApiInfoBuilder().title("久久养老项目使用Swagger2构建RESTful APIs").contact(contact)
                .version("2.0").build();
    }
    
    private ApiKey apiKey() {
        return new ApiKey("apikey", "Authorization", "header");
    }
}
