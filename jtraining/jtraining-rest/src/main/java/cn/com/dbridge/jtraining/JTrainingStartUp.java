/**
 * All rights Reserved, Designed By dbridge.com.cn
 * @Title:  StartUp.java
 * @Package cn.com.dbridge.jtraining
 * @Description:    程序入口 
 * @author: 陈健飞 
 * @date:   2018年12月6日 下午2:20:25
 * @version V1.0
 * @Copyright: 2018 dbridge.com.cn Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
package cn.com.dbridge.jtraining;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import cn.com.dbridge.jtraining.upload.picture.service.StorageService;
import cn.com.dbridge.jtraining.upload.picture.service.impl.StorageProperties;

/**
 * @ClassName:  StartUp
 * @Description: 程序入口
 * @author: 陈健飞
 * @date:   2018年12月6日 下午2:20:25
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class JTrainingStartUp {
    public static void main(String[] args) {
        SpringApplication.run(JTrainingStartUp.class, args);
    }
    
    @Bean
    CommandLineRunner init(StorageService storageService){
        return (args) ->{
            storageService.init();
        };
    }
}
