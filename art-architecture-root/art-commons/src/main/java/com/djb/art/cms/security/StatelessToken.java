package com.djb.art.cms.security;

import java.io.Serializable;

import org.apache.shiro.authc.AuthenticationToken;

public class StatelessToken implements AuthenticationToken, Serializable {

	private static final long serialVersionUID = -5241578394028019550L;

	private String principal;
	private String credentials;
	private String tokenId;
	
	@Override
	public Object getPrincipal() {
		return principal;
	}

	@Override
	public Object getCredentials() {
		return credentials;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

}
