package cn.com.dbridge.jtraining.framework.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import cn.com.dbridge.jtraining.framework.base.Result;
import cn.com.dbridge.jtraining.framework.exception.CustomException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ErrorMsgUtils {
	 /**
     * 
     * @Title: response401
     * @Description: レスポンス情報に直接戻る
     * @param req ServletRequest
     * @param resp ServletResponse
     * @param msg 返されたエラーメッセージ
     */
    public static void response401(ServletRequest req, ServletResponse resp,String msg) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = httpServletResponse.getWriter();
            String data = JsonConvertUtil.objectToJson(new Result<String>(HttpStatus.UNAUTHORIZED.value(), msg, null));
            out.append(data);
        } catch (IOException e) {
            log.error("レスポンスメッセージに直接戻ると、IOException例外が発生します:" + e.getMessage());
            throw new CustomException(
                    "レスポンスメッセージに直接戻ると、IOException例外が発生します。:" + e.getMessage());
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
