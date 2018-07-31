package com.gz.service;

import com.gz.bean.ProposerInfoBean;

public interface MailService {
	void SendMailForAdd(ProposerInfoBean pib) throws Exception;

	void sendMessageMail(Object params, String title, String templateName, String receiver);
}
