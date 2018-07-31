package com.gz.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gz.bean.CityBean;
import com.gz.utils.ReturnData;

/**
 * 提交申请时，获取城市列表接口
 * @author admin
 *
 */
@RestController
@RequestMapping("homeset/city")
public interface CityRest {
	/**
	 * 
	 * @param IP     用户IP，此处做简单的安全校验       [string][Y]      
	 * 
	 * @return 说明： 最外层code是服务端用于执行逻辑的参数，前端不必理会
	 *              msg是正常返回情况下给前端的提示语
	 *              reObj是真正需要的json数据
	           示例：
	    {"restNum":null,"code":1,"num":200,"msg":"成功获取城市列表",
	    "reObj":[{"id":1,"code":10000,"city":"上海市","province":"上海市","level":2,"remark":"","isbusiness":0,"createtime":111111,"modifytime":1111111},
	    {"id":2,"code":10001,"city":"北京市","province":"北京市","level":2,"remark":null,"isbusiness":0,"createtime":222222,"modifytime":222222}]}
	 */
	@RequestMapping("/listCity.json")
	ReturnData listCity(CityBean cb);
}
