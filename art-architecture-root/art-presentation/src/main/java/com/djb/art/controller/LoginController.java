package com.djb.art.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
	private static Logger log =  LoggerFactory.getLogger(LoginController.class);
	private Cache<String, String> userTokenCache;

	@Autowired
	public void setUserTokenCache(CacheManager cacheManager) {
		userTokenCache = cacheManager.getCache("userTokenCache");
	}

	@GetMapping("/login")
	public ModelAndView loginPage() {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()){
			return new ModelAndView("redirect:/");
		}
		return new ModelAndView("login");
	}
	
	@PostMapping("/login")
	public ModelAndView getUser(
    		@RequestParam("accountName") String accountName, 
    		@RequestParam("password") String password, 
    		@RequestParam(name="rm", required=false, defaultValue="false") Boolean rm, 
    		Model model,HttpServletRequest request, HttpServletResponse response) {
		
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(accountName, password);
		ModelAndView ret = new ModelAndView();
		try{
			token.setRememberMe(rm);
			subject.login(token);
			// session.setAttribute("userName", accountName);
			ret.setViewName("redirect:/");
			String tokenid = userTokenCache.get(accountName);
			response.addCookie(new Cookie("tkid", tokenid));
		} catch(UnknownAccountException uae) { 
		    log.info("username wasn't in the system."); 
		    ret.setViewName("login");
		    ret.addObject("ErrMessage", "");
		} catch(IncorrectCredentialsException ice) { 
		    log.info("password didn't match."); 
		    ret.setViewName("login");
		    ret.addObject("ErrMessage", "");
		} catch(LockedAccountException lae) { 
		    log.info("account for that username is locked - can't login."); 
		    ret.setViewName("login");
		    ret.addObject("ErrMessage", "");
		} catch(AuthenticationException ae) { 
		    log.info("unexpected condition."); 
		    ret.setViewName("login");
		    ret.addObject("ErrMessage", "");
		}
		return ret;
    }
	
	@GetMapping("~resource/loginout")
	public ModelAndView logout() {
		Subject subject = SecurityUtils.getSubject();
		try {
            subject.logout();
        } catch (SessionException ise) {
           ise.printStackTrace();
        }
		return new ModelAndView("redirect:/login/");
	}
	
}
