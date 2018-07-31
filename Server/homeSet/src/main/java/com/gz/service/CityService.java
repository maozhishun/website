package com.gz.service;

import com.gz.bean.CityBean;
import com.gz.bean.ProposerInfoBean;
import com.gz.model.City;
import com.gz.utils.ReturnData;
                 
public interface CityService {

	ReturnData listCity(CityBean city);

public String seletCityByPib(ProposerInfoBean pib);
}
