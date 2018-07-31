package com.gz.service.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.gz.bean.ProposerInfoBean;
import com.gz.service.MailService;
import com.gz.utils.SendEmailUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
@Transactional
public class MailServiceImpl implements MailService {
	// 邮件的发送者
	@Value("${spring.mail.username}")
	private String from;
	// 注入MailSender
	@Autowired
	private JavaMailSender mailSender;
	// 发送邮件的模板配置
	private Configuration configuration = null;

	// 邮件接收地址
	@Value("${mail_address}")
	private String receiver;
	// 邮件接收主题的前缀
	@Value("${mail_title_prefix}")
	private String prefix;

	@Override
	public void SendMailForAdd(ProposerInfoBean pib) throws Exception {
		Logger log = Logger.getLogger("sendMailLog");
		try {
			// 发送邮件
			SendEmailUtils s = new SendEmailUtils(receiver, pib, prefix);
			s.send();
			log.info("官网新增贷款申请邮件发送成功。");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("邮件发送失败。");
		}
	}

	/**
	 * @param params
	 *            发送邮件的主题对象 object
	 * @param title
	 *            邮件标题
	 * @param templateName
	 *            模板名称
	 * @param receiver
	 *            收取人
	 */
	@Override
	public void sendMessageMail(Object params, String title, String templateName, String receiver) {

		try {
			configuration = new Configuration(Configuration.VERSION_2_3_25);
			configuration.setDefaultEncoding("utf-8");
			configuration.setClassForTemplateLoading(this.getClass(), "/com/gz/ftl");
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setFrom(from);// 发送者
			helper.setTo(InternetAddress.parse(receiver));
			helper.setSubject("【" + title + "-" + LocalDate.now() + " " + LocalTime.now().withNano(0) + "】");// 邮件标题

			Map<String, Object> model = new HashMap<>();
			model.put("params", params);
			Template template = null;

			try {
				template = configuration.getTemplate("message.ftl");
				try {
					String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

					helper.setText(text, true);
					mailSender.send(mimeMessage);
				} catch (TemplateException e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
