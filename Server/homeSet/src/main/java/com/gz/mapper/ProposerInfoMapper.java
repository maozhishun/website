package com.gz.mapper;

import java.util.List;
import java.util.Map;

import com.gz.bean.ProposerInfoBean;
import com.gz.model.ProposerInfo;
import com.gz.utils.MyMapper;

public interface ProposerInfoMapper extends MyMapper<ProposerInfo> {

	/**
	 * 根据申请人姓名和电话查询申请是否重复提交
	 * @param pib
	 * @return
	 */
	ProposerInfo getTheUserInfoByTelAndProposer(ProposerInfoBean pib);
	/**
	 * 获取申请人列表记录数
	 * @param pib
	 * @return
	 */
	Integer listProposerInfoCount(ProposerInfoBean pib);

	/**
	 * 获取申请人列表
	 * @param pib
	 * @return
	 */
	List<Map<String, Object>> listProposerInfo(ProposerInfoBean pib);
}