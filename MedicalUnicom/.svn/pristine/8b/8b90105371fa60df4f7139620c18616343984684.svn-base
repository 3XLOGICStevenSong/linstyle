package com.djb.ylt.common.util;

import javax.servlet.ServletContext;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionServlet;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 
 * 
 * Use to load Spring context beans and message resources.
 */
public class ResourceLocator {

    private static ApplicationContext appContext;

    private ResourceLocator() {

    }

    public static ApplicationContext getContextInstance(Action action) {

        if (appContext == null) {
            ActionServlet actionServlet = action.getServlet();
            if (actionServlet != null) {
                ServletContext servletContext = actionServlet.getServletContext();
                appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
            }
        }

        return appContext;
    }

    public static ApplicationContext getContextInstance(ServletContext servletContext) {
        if (appContext == null) {
            appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        }

        return appContext;
    }

    public static Object getBean(String beanName, ServletContext servletContext) {
        return getContextInstance(servletContext).getBean(beanName);
    }

    public static String getI18NMessage(Action action, String key) {
        ApplicationContext appContext = getContextInstance(action);
        return appContext.getMessage(key, null, "Error reading resource", null);
    }

    public static String getI18NMessage(Action action, String key, Object[] args) {
        ApplicationContext appContext = getContextInstance(action);
        return appContext.getMessage(key, args, "Error reading resource", null);
    }

}
