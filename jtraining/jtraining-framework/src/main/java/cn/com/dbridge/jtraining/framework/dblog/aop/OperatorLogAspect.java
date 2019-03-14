package cn.com.dbridge.jtraining.framework.dblog.aop;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.com.dbridge.jtraining.dao.po.OperatorLogPO;
import cn.com.dbridge.jtraining.dao.respository.OperatorLogPOMapper;
import cn.com.dbridge.jtraining.framework.dblog.annotation.OperatorLog;
import cn.com.dbridge.jtraining.framework.util.IPUtil;
/**
 * 
 * @ClassName:  OperatorLogAspect
 * @Description:系统操作日志处理切面   
 * @author: 陈健飞
 * @date:   2018年12月7日 上午10:16:17
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Aspect
@Component
public class OperatorLogAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private LocalVariableTableParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
    @Autowired
    private OperatorLogPOMapper operatorLogPOMapper;
    /**
     * 
     * @Title: LogAspect
     * @Description: 自定义日志注解拦截切面 
     * @return: void    
     * @throws
     */
    @Pointcut("@annotation(cn.com.dbridge.jtraining.framework.dblog.annotation.OperatorLog)")
    public void LogAspect() {
    }
    /**
     * 
     * @Title: doAfterThrowing
     * @Description: 对所有产生的异常进行处理
     * @param point
     * @param e
     * @throws Exception
     */
    @AfterThrowing(pointcut = "LogAspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint point, Throwable e) throws Exception {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        OperatorLogPO operatorLogPO = new OperatorLogPO();
        Map<String, Object> map = this.getMethodDescription(point);
        operatorLogPO.setModule(map.get("module").toString());
        operatorLogPO.setMethod(map.get("methods").toString());
        operatorLogPO.setStatusDesc("执行方法异常:" + e + "</font>");
        operatorLogPO.setArgs(map.get("args").toString());
        operatorLogPO.setIp(IPUtil.getIP(request));
        operatorLogPO.setCreateTime(new Date());
        operatorLogPOMapper.insert(operatorLogPO);
    }
    /**
     * 
     * @Title: doAround
     * @Description: 处理日志界面环绕方法
     * @param point
     * @return
     */
    @Around("LogAspect()")
    public Object doAround(ProceedingJoinPoint point) {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        Object result = null;
        try {
            result = point.proceed();
            OperatorLogPO operatorLogPO = new OperatorLogPO();
            Map<String, Object> map = this.getMethodDescription(point);
            operatorLogPO.setModule(map.get("module").toString());
            operatorLogPO.setMethod(map.get("methods").toString());
            operatorLogPO.setStatusDesc(map.get("description").toString());
            operatorLogPO.setArgs(map.get("args").toString());
            operatorLogPO.setIp(IPUtil.getIP(request));
            operatorLogPO.setCreateTime(new Date());
            operatorLogPOMapper.insert(operatorLogPO);
        } catch (Throwable e) {
            logger.error("异常信息:{}", e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }
    /**
     * 
     * @Title: getMethodDescription
     * @Description: 获取方法描述信息
     * @param joinPoint
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public Map<String, Object> getMethodDescription(JoinPoint joinPoint)
            throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class<?> targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    map.put("module", method.getAnnotation(OperatorLog.class)
                            .module());
                    map.put("methods", method
                            .getAnnotation(OperatorLog.class).methods());
                    map.put("args", this.getArgs(method, arguments));
                    String desc = method.getAnnotation(OperatorLog.class)
                            .description();
                    if (StringUtils.isEmpty(desc))
                        desc = "执行成功!";
                    map.put("description", desc);
                    break;
                }
            }
        }
        return map;
    }
    /**
     * 
     * @Title: getArgs
     * @Description: 获取调用方法时传递的参数 
     * @param method 反射获取的方法对象
     * @param arguments 反射获取的参数数组
     * @return
     */
    private String getArgs(Method method, Object[] arguments) {
        StringBuilder builder = new StringBuilder("{");
        String params[] = parameterNameDiscoverer.getParameterNames(method);
        for (int i = 0; i < params.length; i++) {
            if (!"password".equals(params[i])) {
                if (arguments[i].getClass().isArray()) {
                    arguments[i] = Arrays
                            .toString(makeArrayObject(arguments[i]).toArray());
                }
                builder.append(params[i]).append(":").append(arguments[i]);
                if (i != params.length - 1) {
                    builder.append("; ");
                }
            }
        }
        return builder.append("}").toString();
    }
    /**
    * 
    * @Title: makeArrayObject
    * @Description: 反射获取的参数数组转换为集合
    * @param array
    * @return
    */
    private List<Object> makeArrayObject(Object array) {
        List<Object> tem = new ArrayList<Object>();
        for (int i = 0; i < Array.getLength(array); i++) {
            tem.add(Array.get(array, i));
        }
        return tem;
    }
}