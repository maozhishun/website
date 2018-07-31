package com.gz.aspect.safe;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.gz.utils.IllegalStrFilterUtil;
import com.gz.utils.ReturnData;
/**
 * 切面类，检查入参合法性，防止sql注入，防止xss脚本攻击
 * @author admin
 *
 */
@Aspect
@Component
public class CheckInputParameterAspect {
	private static final Log Logger = LogFactory.getLog(CheckInputParameterAspect.class);

    // 存在SQL注入风险
    private static final String IS_SQL_INJECTION = "输入参数存在SQL注入风险";

    private static final String UNVALIDATED_INPUT = "输入参数含有非法字符";

    private static final String ERORR_INPUT = "输入的参数非法";

    /**
     * 定义切入点:拦截rest(即controller)层所有方法
     */
    @Pointcut("execution(* com.gz.rest..*.*(..))")//定义在rest包和所有子包里的任意类的任意方法的执行：
    public void params() {
    }

    /**
     * 定义环绕通知
     * 
     * @param joinPoint
     * @throws Throwable
     */
    @Around("params()")
    public Object doBefore(ProceedingJoinPoint joinPoint) throws Throwable {
    	ReturnData rd = new ReturnData();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Object[] args = joinPoint.getArgs();// 参数
        for(int i=0;i<args.length;i++) {
        	String str = String.valueOf(args[i]);
        	//判断是否存在sql注入风险，如or like 等
            if (!IllegalStrFilterUtil.sqlStrFilter(str)) {
                Logger.info(IS_SQL_INJECTION);
                new RuntimeException(ERORR_INPUT);
                return rd.okBack(200,IS_SQL_INJECTION);
            }
            //判断是否存在非法字符，以防xss脚本攻击（暂时剔除@）
            if (!IllegalStrFilterUtil.isIllegalStr(str)) {
                Logger.info(UNVALIDATED_INPUT);
                new RuntimeException(ERORR_INPUT);
                return rd.okBack(200,UNVALIDATED_INPUT);
            }
        }
        //当所有条件都符合要求，向下执行
        Object result = joinPoint.proceed();
        Logger.info("当前调用接口-[" + request.getRequestURL() + "]");
        return result;
    }
}
