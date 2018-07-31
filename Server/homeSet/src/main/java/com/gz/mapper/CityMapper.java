package com.gz.mapper;

import java.util.List;
import java.util.Map;

import com.gz.bean.CityBean;
import com.gz.model.City;
import com.gz.utils.MyMapper;

public interface CityMapper extends MyMapper<City> {

	List<Map<String, Object>> listCity(CityBean city);
	public String selectCityByCode(Long code);

}