package com.gz.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gz.bean.CityBean;
import com.gz.bean.ProposerInfoBean;
import com.gz.mapper.CityMapper;
import com.gz.service.CityService;
import com.gz.utils.JudgeValue;
import com.gz.utils.ReturnData;
@Service
@Transactional
public class CityServiceImpl implements CityService {

	@Autowired
	CityMapper cityMapper;
	
	@Override
	public ReturnData listCity(CityBean city) {
		ReturnData rd = new ReturnData();
		List<Map<String, Object>> citys = cityMapper.listCity(city);
		//List<City> citys = cityMapper.selectAll();
		if (JudgeValue.isNullOrS0OfList(citys)) {
			rd.okBack(200, "城市列表为空");
		}
		rd.setReObj(citys);
		return rd.okBack(200, "成功获取城市列表");
	}


	/**
	 * 根据用户信息检索所属城市名称。
	 */
	@Override
	public String seletCityByPib(ProposerInfoBean pib) {
		// TODO Auto-generated method stub
		Long code =null;
		if (JudgeValue.isNullOr0OfLong(pib.getIncity())) {
			
		}else {
			 code =pib.getIncity();
		}
		System.out.println(cityMapper.selectCityByCode(code));
		return cityMapper.selectCityByCode(code);
	}



}
