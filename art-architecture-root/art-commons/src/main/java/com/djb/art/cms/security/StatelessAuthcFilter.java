package com.djb.art.cms.security;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

public class StatelessAuthcFilter extends AccessControlFilter {
	
	private Cache<String, StatelessToken> tokenCache;
	
	public StatelessAuthcFilter(CacheManager cacheManager) {
		tokenCache = cacheManager.getCache("tokenCache");
    }

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		if(!subject.isAuthenticated()) {
			return false;
		}
		return true;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		Cookie[] cookies = ((HttpServletRequest)request).getCookies();
		String tkid = null;
		for(Cookie cookie : cookies) {
			if(null != cookie && "tkid".equals(cookie.getName())) {
				tkid = cookie.getValue();
			}
		}
		try {
			StatelessToken tk = tokenCache.get(tkid.replaceAll("\"", ""));
			UsernamePasswordToken token = new UsernamePasswordToken((String)tk.getPrincipal(), (String)tk.getCredentials());
			subject.login(token);
		} catch(Exception e) {
			e.printStackTrace();  
	      onLoginFail(response);
	      return false;
		}
		return true;
	}
	
	private void onLoginFail(ServletResponse response) throws IOException {  
	    HttpServletResponse httpResponse = (HttpServletResponse) response;  
	    httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);  
	    httpResponse.getWriter().write("login error");  
	}

}
