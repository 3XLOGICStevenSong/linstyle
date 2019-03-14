/**
 * All rights Reserved, Designed By dbridge.com.cn
 * 
 * @Title: GlobalExceptionAdvice.java
 * @Package cn.com.dbridge.lifecare.framework.exception
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: 陈健飞
 * @date: 2018年12月7日 上午9:03:59
 * @version V1.0
 * @Copyright: 2018 dbridge.com.cn Inc. All rights reserved.
 *             注意：本内容仅限于必捷必信息技术有限公司 内部传阅，禁止外泄以及用于其他的商业目
 */
package cn.com.dbridge.lifecare.framework.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import cn.com.dbridge.lifecare.framework.base.Result;
import cn.com.dbridge.lifecare.framework.enums.ResponseCode;

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
	public Result<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		BindingResult bindingResult = e.getBindingResult();
		Result<String> result = new Result<String>();
		result.setStatusCode(ResponseCode.SERVICE_ERROR.value);
		for (ObjectError error : bindingResult.getAllErrors()) {
			if (error instanceof FieldError) {
			    FieldError fieldError = (FieldError) error;
			    result.setMsg(fieldError.getDefaultMessage());
			    break;
			}
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
		if(e instanceof HttpMessageNotReadableException) {
		    result.setStatusCode(ResponseCode.NOT_FOUND.value);
            result.setMsg("请求参数错误或请求参数不完整");
		}else if (e instanceof HttpMediaTypeNotSupportedException || e instanceof HttpRequestMethodNotSupportedException
				|| e instanceof NoHandlerFoundException) {
			result.setStatusCode(ResponseCode.NOT_FOUND.value);
			result.setMsg(e.getMessage());
		} else if (e instanceof UnauthenticatedException 
		        || e instanceof AuthorizationException) {
			result.setStatusCode(HttpStatus.UNAUTHORIZED.value());
			result.setMsg("没有权限");
		}else if (e instanceof CustomAuthException) {
			result.setStatusCode(HttpStatus.UNAUTHORIZED.value());
			result.setMsg(e.getMessage());
		}  else if (e instanceof CustomException 
				|| e instanceof CustomUnauthorizedException) {
			result.setStatusCode(ResponseCode.SERVICE_ERROR.value);
			result.setMsg(e.getMessage());
		} else if(e instanceof MaxUploadSizeExceededException){
			result.setStatusCode(ResponseCode.SERVICE_ERROR.value);
			result.setMsg("上传文件过大,请上传小于400KB的文件");
		} else{
			result.setStatusCode(ResponseCode.SERVER_ERROR.value);
			result.setMsg(e.getMessage());
		}
		return result;
	}
}
