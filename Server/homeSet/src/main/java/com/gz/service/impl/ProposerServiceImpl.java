package com.gz.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gz.bean.ProposerInfoBean;
import com.gz.init.SysInit;
import com.gz.mapper.ProposerInfoMapper;
import com.gz.model.ProposerInfo;
import com.gz.rest.UserMailController;
import com.gz.service.ProposerService;
import com.gz.utils.JudgeValue;
import com.gz.utils.ReturnData;
import com.gz.utils.ReturnPageData;
@Service
@Transactional
public class ProposerServiceImpl implements ProposerService {
	
	private static final Logger logger = LogManager.getLogger(ProposerServiceImpl.class);

	@Autowired
	ProposerInfoMapper proposerInfoMapper;
	@Autowired
	UserMailController userMailController;
	@Override
	/**
	 * 用户提交贷款申请，插入一条申请记录
	 */
	public ReturnData insertApply(ProposerInfoBean pib) {
		//1.插入之前根据 申请人姓名+tel，查询是否已经提交申请，避免恶意提交
		ProposerInfo pi =  proposerInfoMapper.getTheUserInfoByTelAndProposer(pib);
		if (null!=pi) {
			ReturnData rd = new ReturnData();
			return rd.okBack(200,"手机号"+pib.getTel()+"名为【"+pib.getProposer()
							+"】的用户已经提交过申请或申请尚未处理，暂不可重复提交");
		}
		//2.空，执行入库操作
		ProposerInfo proposerInfo = new ProposerInfo();
		BeanUtils.copyProperties(pib, proposerInfo);
		long nowTime = System.currentTimeMillis();
		proposerInfo.setCreatetime(nowTime);
		proposerInfo.setModifytime(nowTime);
		int res = proposerInfoMapper.insertSelective(proposerInfo);
		if (res>0) {
			logger.info("入库成功，新增记录主键："+proposerInfo.getId());
			//发送邮件
			
			SysInit.THREAD_POOL.execute(()->{
				try {
					userMailController.sendMailForAdd(pib);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			);
			ReturnData rd = new ReturnData();
			
			return rd.okBack(200,"申请资料提交成功");
		}
		return null;
	}
	@Override
	/**
	 * 获取申请人信息列表
	 */
	public ReturnData listProposerInfo(ProposerInfoBean pib) {
		//1.查询列表记录数量
		Integer count = proposerInfoMapper.listProposerInfoCount(pib);
		if (JudgeValue.isNullOr0OfInteger(count)) {
			ReturnData rd = new ReturnData();
			rd.okBack(200,"申请列表为空");
		}
		logger.info("成功获取申请人列表,记录数为："+count);
		List<Map<String, Object>> list = proposerInfoMapper.listProposerInfo(pib);
		
		ReturnPageData rpd = new ReturnPageData();
		rpd.setRecordTotle(count);
		rpd.setReObj(list);
		return rpd.okBack(200,"成功获取申请人列表");
	}
	
	
	
}
