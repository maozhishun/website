package com.gz.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
/**
 * 对字符检测的工具类,防止sql注入
 * @author admin
 *
 */
public class IllegalStrFilterUtil {
	 private static final Log Logger = LogFactory.getLog(IllegalStrFilterUtil.class);
	 	//暂时剔除对@的过滤，增加<>，防止xss跨站脚本攻击
	    private static final String REGX = "<|>|!|！|◎|#|＃|(\\$)|￥|%|％|(\\^)|……|(\\&)|※|(\\*)|×|(\\()|（|(\\))|）|_|——|(\\+)|＋|(\\|)|§";

	    /**
	     * 对常见的sql注入攻击进行拦截
	     * 
	     * @param sInput
	     * @return
	     *  true 表示参数不存在SQL注入风险
	     *  false 表示参数存在SQL注入风险
	     */
	    public static Boolean sqlStrFilter(String sInput) {
	        if (sInput == null || sInput.trim().length() == 0) {
	            return false;
	        }
	        sInput = sInput.toUpperCase();
	        Logger.info("==sinput:"+sInput);
	        if (sInput.indexOf("DELETE") >= 0 || sInput.indexOf("ASCII") >= 0 || sInput.indexOf("UPDATE") >= 0 || sInput.indexOf("SELECT") >= 0
	                || sInput.indexOf("'") >= 0 || sInput.indexOf("SUBSTR(") >= 0 || sInput.indexOf("COUNT(") >= 0 || sInput.indexOf(" OR ") >= 0
	                || sInput.indexOf(" AND ") >= 0 || sInput.indexOf("DROP") >= 0 || sInput.indexOf("EXECUTE") >= 0 || sInput.indexOf("EXEC") >= 0
	                || sInput.indexOf("TRUNCATE") >= 0 || sInput.indexOf("INTO") >= 0 || sInput.indexOf("DECLARE") >= 0 || sInput.indexOf("MASTER") >= 0) {
	            Logger.error("该参数存在SQL注入风险：sInput=" + sInput);
	            return false;
	        }
	        Logger.info("通过sql检测");
	        return true;
	    }

	    /**
	     * 对非法字符进行检测
	     * 
	     * @param sInput
	     * @return
	     *  true 表示参数不包含非法字符
	     *  false 表示参数包含非法字符
	     */
	    public static Boolean isIllegalStr(String sInput) {

	        if (sInput == null || sInput.trim().length() == 0) {
	            return false;
	        }
	        sInput = sInput.trim();
	        Pattern compile = Pattern.compile(REGX, Pattern.CASE_INSENSITIVE);
	        Matcher matcher = compile.matcher(sInput);
	        Boolean result = matcher.find();
	        if (result) {//true说明包含非法字符
	        	Logger.info("未通过字符串检测");
			}else {
				Logger.info("通过字符串检测");
			}
	        return !result;
	    }

	    public static void main(String[] args) {
			String param = "PROPOSERINFOBEAN [ID=NULL, PROPOSER=WDX, INCITY=NULL, ISHOUSEPROPERTY=NULL, LOANAMOUNT=NULL, PROPOSERIP=NULL, EMAIL=18178947409@163.COM, TEL=18802154909, CREATETIME=NULL, MODIFYTIME=NULL]";
			param = param.trim();
	        Pattern compile = Pattern.compile(REGX, Pattern.CASE_INSENSITIVE);
	        Matcher matcher = compile.matcher(param.trim());
	        System.out.println(matcher.find());
	    }
}
