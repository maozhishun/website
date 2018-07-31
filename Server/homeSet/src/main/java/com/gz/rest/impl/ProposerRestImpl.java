package com.gz.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gz.bean.ProposerInfoBean;
import com.gz.rest.ProposerRest;
import com.gz.service.ProposerService;
import com.gz.utils.JudgeValue;
import com.gz.utils.RegularUtils;
import com.gz.utils.ReturnData;
@Component
public class ProposerRestImpl implements ProposerRest {

	@Autowired
	ProposerService proposerService;
	@Override
	public ReturnData insertApply(ProposerInfoBean pib) {
		ReturnData rd = new ReturnData();
		if (JudgeValue.isNullOfString(pib.getProposer())) {
			return rd.failBack(200,"申请人姓名不能为空");
		}
		if (JudgeValue.isNullOr0OfLong(pib.getIncity())) {
			return rd.failBack(200,"申请人所在城市不能为空");
		}
		if (JudgeValue.isNullOr0OfInteger(pib.getLoanamount())) {
			return rd.failBack(200,"贷款金额不能为空/贷款金额不能小于等于0");
		}
		if (JudgeValue.isNullOrL0OfInteger(pib.getIshouseproperty())) {
			return rd.failBack(200,"是否拥有房产不能为空");
		}
		if (pib.getTel()==null||pib.getTel().length()!=11) {
			return rd.failBack(200,"手机号格式不正确");
		}
		if (!RegularUtils.isEmail(pib.getEmail())) {
			return rd.failBack(200,"邮箱号格式不正确");
		}
		return proposerService.insertApply(pib);
	}
	@Override
	public ReturnData listProposerInfo(ProposerInfoBean pib) {
		if (JudgeValue.isNullOfString(pib.getGzAdminId())) {
			ReturnData rd = new ReturnData();
			return rd.failBack(200,"管理员账号不能为空");
		}
		return proposerService.listProposerInfo(pib);
	}
	

}
