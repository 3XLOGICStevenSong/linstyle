package com.djb.art.cms.security;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.crypto.hash.Sha256Hash;


public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {
	
	private Cache<String, AtomicInteger> passwordRetryCache;
	private Cache<String, StatelessToken> tokenCache;
	private Cache<String, String> userTokenCache;
	private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
		passwordRetryCache = cacheManager.getCache("passwordRetryCache");
		tokenCache = cacheManager.getCache("tokenCache");
		userTokenCache = cacheManager.getCache("userTokenCache");
    }

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		String username = (String)token.getPrincipal();
		//retry count + 1
		AtomicInteger retryCount = passwordRetryCache.get(username);
		if(retryCount == null) {
			retryCount = new AtomicInteger(0);
			passwordRetryCache.put(username, retryCount);
		}
		if(retryCount.incrementAndGet() > 5) {
			//if retry count > 5 throw
			throw new ExcessiveAttemptsException();
        }
		boolean matches = super.doCredentialsMatch(token, info);
		
		if(matches) {
			//clear retry count
			passwordRetryCache.remove(username);
			StatelessToken value = new StatelessToken();
			String tk = new Sha256Hash(username, "this is a random string" + formatter.format(new Date())).toString();;
			value.setTokenId(tk);
			value.setPrincipal(username);
			value.setCredentials(toString(((UsernamePasswordToken)token).getPassword()));
			userTokenCache.put(username, tk);
			tokenCache.put(tk, value);
        }
		return matches;
	}
	
	private String toString(char[] chs) {
		StringBuilder sb = new StringBuilder();
		for(char ch : chs) {
			sb.append(ch);
		}
		return sb.toString();
	}
}
