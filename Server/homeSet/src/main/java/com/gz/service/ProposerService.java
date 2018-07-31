package com.gz.service;

import com.gz.bean.ProposerInfoBean;
import com.gz.utils.ReturnData;

public interface ProposerService {

	ReturnData insertApply(ProposerInfoBean pib);

	ReturnData listProposerInfo(ProposerInfoBean pib);

   
}
