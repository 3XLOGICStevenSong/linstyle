package com.djb.art.controller;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import net.sf.json.JSONObject;

@RestControllerAdvice 
public class SystemExceptionHandler {
	
	@ExceptionHandler(UnauthorizedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)  
    public JSONObject handSql(Exception ex){  
        JSONObject ret = new JSONObject();
        ret.put("succeed", "false");
        ret.put("errCode", 403);
        return ret;
    } 
	
}
