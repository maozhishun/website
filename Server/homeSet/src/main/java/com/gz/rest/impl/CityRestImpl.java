package com.gz.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gz.bean.CityBean;
import com.gz.rest.CityRest;
import com.gz.service.CityService;
import com.gz.utils.JudgeValue;
import com.gz.utils.ReturnData;
@Component
public class CityRestImpl implements CityRest {

	@Autowired
	CityService cityService;
	@Override
	public ReturnData listCity(CityBean city) {
		if (JudgeValue.isNullOfString(city.getIP())) {
			ReturnData rd = new ReturnData();
			return rd.failBack("IP不能为空");
		}
		return cityService.listCity(city);
	}

	
}
