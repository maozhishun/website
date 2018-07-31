package com.gz.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gz.bean.ProposerInfoBean;
import com.gz.service.MailService;
import com.gz.service.QuartzService;
import com.gz.utils.JudgeValue;
import com.gz.utils.RegularUtils;
import com.gz.utils.ReturnData;

@RestController
public class UserMailController {
	@Autowired
	MailService mailService;
	@Autowired
	QuartzService quartzService;

	@RequestMapping("/sendMailForAdd")
	/**
	 * 申请贷款邮件发送
	 * 
	 * @param proposer
	 *            【string】【Y】 申请人姓名
	 * @param incity
	 *            【Long】【Y】 申请人所在城市（传城市代码）
	 * @param loanamount
	 *            【int】【Y】 贷款金额，大于0
	 * @param ishouseproperty
	 *            【int】【Y】 有无房产，有传0，没有传1
	 * @param tel
	 *            【string】【Y】 手机号
	 * @param email
	 *            【string】【Y】 电子邮件
	 * @param proposerip
	 *            【string】【N】 申请人IP
	 * 
	 * @return {"restNum":null,"code":null,"num":null,"msg":"已成功发送邮件至广尊科技业务人员!","reObj":null}
	 * 
	 */
	public ReturnData sendMailForAdd(ProposerInfoBean pib) throws Exception {
		ReturnData rd = new ReturnData();

		if (JudgeValue.isNullOfString(pib.getProposer())) {
			return rd.failBack(200, "申请人姓名不能为空");
		}
		if (JudgeValue.isNullOr0OfLong(pib.getIncity())) {
			return rd.failBack(200, "申请人所在城市不能为空");
		}
		if (JudgeValue.isNullOr0OfInteger(pib.getLoanamount())) {
			return rd.failBack(200, "贷款金额不能为空/贷款金额不能小于等于0");
		}
		if (JudgeValue.isNullOrL0OfInteger(pib.getIshouseproperty())) {
			return rd.failBack(200, "是否拥有房产不能为空");
		}
		if (!RegularUtils.isMobileNum(pib.getTel())) {
			return rd.failBack(200, "手机号格式不正确");
		}
		if (!RegularUtils.isEmail(pib.getEmail())) {
			return rd.failBack(200, "邮箱号格式不正确");
		}
		try {
			mailService.SendMailForAdd(pib);
			rd.setMsg("已成功发送邮件至广尊科技业务人员!");
			return rd;
		} catch (Exception e) {
			rd.setMsg("邮件发送失败!");
			return rd;
		}
	}

	@RequestMapping("/sendMailForSummary")
	/**
	 * 发送当天到目前为止官网申贷汇总邮件
	 * 
	 * @param gzAdminId
	 *            【string】【Y】 管理员账号，暂时写死用admin
	 * @return 固定 {"restNum":null,"code":1,"num":200,"msg":"邮件汇总发送成功。","reObj":null}
	 */
	public ReturnData sendMailForSummary(ProposerInfoBean pib) throws Exception {
		if (JudgeValue.isNullOfString(pib.getGzAdminId())) {
			ReturnData rd = new ReturnData();
			return rd.failBack(200, "管理员账号不能为空");
		} else {
			return quartzService.summary();
		}

	}

}
