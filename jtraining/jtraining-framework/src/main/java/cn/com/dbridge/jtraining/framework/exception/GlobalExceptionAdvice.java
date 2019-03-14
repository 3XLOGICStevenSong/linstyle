/**
 * All rights Reserved, Designed By dbridge.com.cn
 * 
 * @Title: GlobalExceptionAdvice.java
 * @Package cn.com.dbridge.jtraining.framework.exception
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: 陈健飞
 * @date: 2018年12月7日 上午9:03:59
 * @version V1.0
 * @Copyright: 2018 dbridge.com.cn Inc. All rights reserved.
 *             注意：本内容仅限于必捷必信息技术有限公司 内部传阅，禁止外泄以及用于其他的商业目
 */
package cn.com.dbridge.jtraining.framework.exception;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import cn.com.dbridge.jtraining.framework.base.ErrorField;
import cn.com.dbridge.jtraining.framework.base.Result;
import cn.com.dbridge.jtraining.framework.enums.ResponseCode;

/**
 * @ClassName: GlobalExceptionAdvice
 * @Description: 全局异常处理
 * @author: 陈健飞
 * @date: 2018年12月7日 上午9:03:59
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved. 注意：本内容仅限于必捷必信息技术有限公司
 *             内部传阅，禁止外泄以及用于其他的商业目
 */
@EnableWebMvc
@ControllerAdvice
public class GlobalExceptionAdvice {
	
	/**
	 * 拦截非法参数异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public Result<List<ErrorField>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		BindingResult bindingResult = e.getBindingResult();
		Result<List<ErrorField>> result = new Result<List<ErrorField>>();
		result.setStatusCode(ResponseCode.SERVICE_ERROR.getValue());
		result.setMsg("パラメータエラー");
		ErrorField errorField = null;
		List<ErrorField> errorFieldList = new ArrayList<ErrorField>();
		for (ObjectError error : bindingResult.getAllErrors()) {
			if (error instanceof FieldError) {
				errorField = new ErrorField();
				FieldError fieldError = (FieldError) error;
				errorField.setField(fieldError.getField());
				errorField.setMessage(fieldError.getDefaultMessage());
				errorFieldList.add(errorField);
			}
			result.setData(errorFieldList);
		}
		return result;
	}

	/**
	 *  拦截其他异常信息
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Result<String> handleGlobalException(Exception e) {
		e.printStackTrace();
		Result<String> result = new Result<String>();
		if (e instanceof HttpMediaTypeNotSupportedException || e instanceof HttpRequestMethodNotSupportedException
				|| e instanceof NoHandlerFoundException) {
			result.setStatusCode(ResponseCode.NOT_FOUND.getValue());
			result.setMsg(e.getMessage());
		} else if (e instanceof UnauthenticatedException) {
			result.setStatusCode(HttpStatus.UNAUTHORIZED.value());
			result.setMsg("権限がない");
		}else if (e instanceof CustomAuthException) {
			result.setStatusCode(HttpStatus.UNAUTHORIZED.value());
			result.setMsg(e.getMessage());
		}  else if (e instanceof CustomException 
				|| e instanceof CustomUnauthorizedException) {
			result.setStatusCode(ResponseCode.SERVICE_ERROR.getValue());
			result.setMsg(e.getMessage());
		} else {
			result.setStatusCode(ResponseCode.SERVER_ERROR.getValue());
			result.setMsg("システムエラー");
		}
		return result;
	}
}
