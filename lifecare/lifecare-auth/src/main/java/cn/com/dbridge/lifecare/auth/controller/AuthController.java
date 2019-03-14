package cn.com.dbridge.lifecare.auth.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.dbridge.lifecare.framework.base.Result;
import cn.com.dbridge.lifecare.framework.constant.Constant;
import cn.com.dbridge.lifecare.framework.dblog.annotation.OperatorLog;
import cn.com.dbridge.lifecare.framework.dto.mobile.MobileUserDTO;
import cn.com.dbridge.lifecare.framework.dto.web.LoginDTO;
import cn.com.dbridge.lifecare.framework.dto.web.UserDTO;
import cn.com.dbridge.lifecare.framework.enums.UserStatusEnum;
import cn.com.dbridge.lifecare.framework.enums.UserTypeEnum;
import cn.com.dbridge.lifecare.framework.exception.CustomException;
import cn.com.dbridge.lifecare.framework.util.AesCipherUtil;
import cn.com.dbridge.lifecare.framework.util.JedisUtil;
import cn.com.dbridge.lifecare.framework.util.JwtUtil;
import cn.com.dbridge.lifecare.framework.vo.mobile.MobileUserVO;
import cn.com.dbridge.lifecare.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @ClassName:  AuthController
 * @Description: 权限管理
 * @author: 陈健飞
 * @date:   2018年12月27日 上午11:29:24
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *  注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@RestController
@PropertySource("classpath:config.properties")
@Api(tags = "权限管理")
@RequestMapping(value = "/api")
@Slf4j
public class AuthController {

    /**
     * RefreshToken 有効期限
     */
    @Value("${refreshTokenExpireTime}")
    private String refreshTokenExpireTime;

    @Autowired
    private UserService userService;

    /**
     * 
     * @Title: login
     * @Description: 登陆
     * @param loginDTO
     * @param httpServletResponse
     * @return
     */
    @ApiOperation(value = "登陆", notes = "登陆")
    @PostMapping("/login")
    @OperatorLog(module="权限模块",methods="登陆",description="登陆")
    public Result<Object> login(@RequestBody LoginDTO loginDTO,HttpServletResponse httpServletResponse) {
        String username = loginDTO.getUserNumber();
        String password = loginDTO.getPassword();
        String openId = loginDTO.getOpenId();
        String wechatNickName = loginDTO.getNickName();
        
        //为空业务校验,如果OpenId为空才校验用户名密码是否合法，否则不校验
        if (StringUtils.isEmpty(openId)) {
            if (StringUtils.isEmpty(username)) {
                throw new CustomException("用户名不能为空");
            }
            if (StringUtils.isEmpty(password)) {
                throw new CustomException("密码不能为空");
            }
        }
        
        //判断用户名或OpenId是否存在
        UserDTO userDTO = new UserDTO();
        if(StringUtils.isNotEmpty(username)) {
        	userDTO.setUserNumber(username);
        	log.info("密钥为：" + AesCipherUtil.enCrypto(password));
        	userDTO.setPassword(AesCipherUtil.enCrypto(password));
        }else {
        	userDTO.setOpenId(openId);
        	UserDTO user = userService.getUserByUser(userDTO);
        	if(user != null) {
        		MobileUserDTO mobileUserDTO = new MobileUserDTO();
        		mobileUserDTO.setUserId(user.getUserId());
        		mobileUserDTO.setEmail("");
        		mobileUserDTO.setOpenId("");
				userService.updateUser(mobileUserDTO );
        	}
        }
        userDTO = userService.getUserByUser(userDTO);
        //1.查询不到用户，提示【用户名或密码错误】
        if (userDTO == null) {
            throw new CustomException("身份认证错误");
        }
        
        //2.如果等级账号是客户，提示没有权限
        if(UserTypeEnum.CUSTOMER.value.equals(userDTO.getUserType())) {
            throw new CustomException("该账号无权限");
        }

        //服务人员在Web不能登录
        if(UserTypeEnum.SERVICE_PERSONAL.value.equals(userDTO.getUserType())){
            String sourceType = loginDTO.getSourceType();
            if(StringUtils.isEmpty(sourceType) || !"wechat".equals(sourceType)){
                throw new CustomException("该账号无权限");
            }
        }
        //后台用户在手机端不能登录
        if(UserTypeEnum.BACKGROUND_USER.value.equals(userDTO.getUserType())){
            String sourceType = loginDTO.getSourceType();
            if(StringUtils.isNotEmpty(sourceType) && "wechat".equals(sourceType)){
                throw new CustomException("该账号无权限");
            }
        }
        //3.判断账号状态，如果锁定，提示【账号已锁定】
        if(UserStatusEnum.STOP_STATUS.value.equals(userDTO.getUserStatus())) {
            throw new CustomException("账号已锁定");
        }
        
        //4.登陆成功，更新微信昵称
        MobileUserDTO mobileUserDTO = new MobileUserDTO();
        if(StringUtils.isNotEmpty(wechatNickName)) {
        	mobileUserDTO.setWechatNickName(wechatNickName);
        }
        if(StringUtils.isNotEmpty(openId)) {
        	 mobileUserDTO.setOpenId(openId);
        }
        mobileUserDTO.setUserId(userDTO.getUserId());
        userService.updateUser(mobileUserDTO);
        
        
        //5.密码正确继续做业务操作,下面的Cache必须处理，需自己重写
        if (JedisUtil.exists(Constant.PREFIX_SHIRO_CACHE + username)) {
            JedisUtil.delKey(Constant.PREFIX_SHIRO_CACHE + username);
        }
        
        //6.生成Token
        if(StringUtils.isEmpty(username) && StringUtils.isNotEmpty(openId)){
            UserDTO reqDTO = new UserDTO();
            reqDTO.setOpenId(openId);
            UserDTO userByUser = userService.getUserByUser(reqDTO);
            if(userByUser != null){
                username = userByUser.getUserNumber();
            }
        }
        String currentTimeMillis = String.valueOf(System.currentTimeMillis());
        JedisUtil.setObject(Constant.PREFIX_SHIRO_REFRESH_TOKEN + username,
                currentTimeMillis, Integer.parseInt(refreshTokenExpireTime));
        String token = JwtUtil.sign(username, currentTimeMillis);
        httpServletResponse.setHeader("Authorization", token);
        log.debug("token:{}",token);
        httpServletResponse.setHeader("Access-Control-Expose-Headers","Authorization");
        
        //7.封装返回给调用方的数据结构
        MobileUserVO mobileUserVO = new MobileUserVO();
        BeanUtils.copyProperties(userDTO, mobileUserVO);
        return new Result<Object>(HttpStatus.OK.value(), "登陆成功", mobileUserVO);
    }
}