//package cn.com.dbridge.lifecare.config;
//
//import me.chanjar.weixin.mp.api.WxMpConfigStorage;
//import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
//import me.chanjar.weixin.mp.api.WxMpService;
//import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
///**
// * 
// * @ClassName:  WechatMpConfig
// * @Description:TODO(这里用一句话描述这个类的作用)
// * @author: 陈健飞
// * @date:   2019年1月29日 上午9:29:17
// * 
// * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
// *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
// */
//@Component
//public class WechatMpConfig {
//
//    @Autowired
//    private WechatAccountConfig accountConfig;
//
//    @Bean
//    public WxMpService wxMpService() {
//        WxMpService wxMpService = new WxMpServiceImpl();
//        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
//        return wxMpService;
//    }
//
//    @Bean
//    public WxMpConfigStorage wxMpConfigStorage() {
//        WxMpInMemoryConfigStorage wxMpConfigStorage = new WxMpInMemoryConfigStorage();
//        wxMpConfigStorage.setAppId(accountConfig.getMpAppId());
//        wxMpConfigStorage.setSecret(accountConfig.getMpAppSecret());
//        return wxMpConfigStorage;
//    }
//}
