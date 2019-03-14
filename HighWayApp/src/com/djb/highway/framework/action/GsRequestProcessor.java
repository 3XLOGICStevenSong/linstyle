package com.djb.highway.framework.action;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.RequestProcessor;

public class GsRequestProcessor extends RequestProcessor {

    protected boolean processPreprocess(HttpServletRequest request,
            HttpServletResponse responce) {

        try {
            request.setCharacterEncoding("UTF-8");
            responce.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
        }

        return super.processPreprocess(request, responce);
    }

}
