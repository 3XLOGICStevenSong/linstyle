package cn.com.dbridge.jtraining.auth.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.dbridge.jtraining.auth.common.Constant;
import cn.com.dbridge.jtraining.auth.util.JwtUtil;
import cn.com.dbridge.jtraining.framework.base.Result;
import cn.com.dbridge.jtraining.framework.dto.UserLoginDTO;
import cn.com.dbridge.jtraining.framework.exception.CustomException;
import cn.com.dbridge.jtraining.framework.exception.CustomUnauthorizedException;
import cn.com.dbridge.jtraining.framework.util.AesCipherUtil;
import cn.com.dbridge.jtraining.framework.util.JedisUtil;
import cn.com.dbridge.jtraining.framework.vo.UserLoginVO;
import cn.com.dbridge.jtraining.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: UserController
 * @Description: ユーザ権限関連コントローラ
 * @author: 陈健飞
 * @date: 2018年12月5日 10：34：30AM
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved. 注：このコンテンツはDBJ
 *             Information Technology
 *             Co.Ltd.が社内でのみ流通させており、他の商業目的で漏洩することは禁止されています。
 */
@RestController
@PropertySource("classpath:config.properties")
@Api(tags = "権利管理")
@Slf4j
@RequestMapping(value = "/v1/api")
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
	 * @Description: ログイン
	 * @param userDTO
	 * @param httpServletResponse
	 * @return ステータスコードとステータス
	 */
	@ApiOperation(value = "ログイン", notes = "ログイン")
	@PostMapping("/login")
    public Result<Object> login(@Valid @RequestBody UserLoginDTO userLoginDTO,
			HttpServletResponse httpServletResponse) {
		String username = userLoginDTO.getNo();
		String password = userLoginDTO.getPassword();
		UserLoginVO userLoginVO = userService.getUserByAccount(username);
		if (userLoginVO == null) {
			throw new CustomException("アカウントが存在しません");
		}
		// AES復号化のためのパスワード
		log.info("密钥为：" + AesCipherUtil.enCrypto(username + password));
		String key = null;
		String dbPassword = userLoginVO.getPassword();
		if (!StringUtils.isEmpty(dbPassword)) {
			key = AesCipherUtil.deCrypto(dbPassword);
		}
		if ((username + password).equals(key)) {
			// 可能なShiro許可情報キャッシュをクリアする
			if (JedisUtil.exists(Constant.PREFIX_SHIRO_CACHE + username)) {
				JedisUtil.delKey(Constant.PREFIX_SHIRO_CACHE + username);
			}

			// RefreshTokenを設定します。タイムスタンプは現在のタイムスタンプです
			String currentTimeMillis = String.valueOf(System.currentTimeMillis());
			JedisUtil.setObject(Constant.PREFIX_SHIRO_REFRESH_TOKEN + username, currentTimeMillis,
					Integer.parseInt(refreshTokenExpireTime));

			// ヘッダーのAuthorizationからAccessTokenを返します。タイムスタンプは現在のタイムスタンプです
			String token = JwtUtil.sign(username, currentTimeMillis);
			userLoginVO.setToken(token);
			httpServletResponse.setHeader("Authorization", token);
			httpServletResponse.setHeader("Access-Control-Expose-Headers", "Authorization");
			return new Result<Object>(HttpStatus.OK.value(), "ログインの成功", userLoginVO);
		} else {
			throw new CustomUnauthorizedException("不正なアカウントまたはパスワード");
		}
	}
}