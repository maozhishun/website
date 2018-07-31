package com.gz.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gz.bean.ProposerInfoBean;
import com.gz.utils.ReturnData;

/**
 * 此接口用来实现申请人提交申请，或其他功能也可以补充
 * @author admin
 *
 */
@RestController
@RequestMapping(value="homeset/proposer",method={RequestMethod.POST})
public interface ProposerRest {
	
	/**
	 * 申请贷款
	 * @param	proposer	【string】【Y】	申请人姓名
	 * @param	incity		【Long】【Y】             申请人所在城市（传城市代码）
	 * @param	loanamount  【int】【Y】                贷款金额，大于0
	 * @param	ishouseproperty  【int】【Y】       有无房产，有传0，没有传1
	 * @param   email         【string】【Y】          邮箱号
	 * @param   tel         【string】【Y】          手机号
	 * @param   proposerip  【string】【N】           申请人IP
	 * 
	 * @return  {"restNum": null,"code": 1,"num": 200,"msg": "申请资料提交成功","reObj": null}
			
	 */
	@RequestMapping(value="/insertApply.json")
	ReturnData insertApply(ProposerInfoBean pib);
	/**
	 * 获取申请人信息列表
	 * @param   gzAdminId		【string】【Y】		管理员账号，暂时写死用admin
	 * @param	pageNum         【int】【N】                         页码，默认1
	 * @param	pageSize        【int】【N】			单页数量:默认10
	 * @param   isLimit			【int】【N】			是否分页，默认0是，否传1
	 * @param   orderBy			【string】【N】                  排序规则。例如：createTime DESC 按照时间倒序
	 * @param   startTime		【long】【N】                       开始时间
	 * @param   endTime		【long】【N】                       结束时间
	 
	 * @return 固定
	    {"restNum":null,"code":1,"num":200,"msg":"成功获取申请人列表","reObj":
	    [{"modifyTime":1531298439426,"createTime":1531298439426,"proposer":"老牛1","isHouseProperty":0,"tel":"13292443656","id":9,"inCity":10001,"loanAmount":55556666},
	    {"modifyTime":1531295092751,"createTime":1531295092751,"proposer":"老牛","isHouseProperty":0,"tel":"13292443656","id":8,"inCity":10001,"loanAmount":55556666}],
	    "columnTotle":11}
	 */
	@RequestMapping(value="/listProposerInfo.json")
	ReturnData listProposerInfo(ProposerInfoBean pib);
	

}
