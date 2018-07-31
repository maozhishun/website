package com.gz.utils;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.gz.bean.ProposerInfoBean;
import com.gz.config.EmailConfig;
import com.sun.mail.util.MailSSLSocketFactory;

public class SendEmailUtils {

	private static String account = EmailConfig.ACCOUNT;// 登录用户名
	private static String pass = EmailConfig.PASS; // 登录密码
	private static String from = EmailConfig.FROM; // 发件地址
	private static String host = EmailConfig.HOST; // 服务器地址
	private static String port = EmailConfig.PORT; // 端口
	private static String protocol = EmailConfig.PROTOCOL; // 协议

	private String to; // 收件人
	private String prefix; // 收件人主题前缀
	// private String id; //重置密码地址标识(这句话是我的业务需要，你们可以不要)
	ProposerInfoBean pib;

	public SendEmailUtils(String to, ProposerInfoBean pib, String prefix) {
		this.to = to;
		this.pib = pib;
		this.prefix = prefix;
	}

	public void send() {
		Properties prop = new Properties();
		Date data = new Date();
		String houseProerty = null;// 有无房产
		String proposer = null;// 申请人姓名
		String tel = null;// 电话
		String email = null;// 邮箱
		Integer loanAmount = null;// 贷款金额
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");// 格式化日期格式
		String time = format1.format(data.getTime());
		// System.out.println("格式化结果1：" + time);
		// 协议
		prop.setProperty("mail.transport.protocol", protocol);
		// 服务器
		prop.setProperty("mail.smtp.host", host);
		// 端口
		prop.setProperty("mail.smtp.port", port);
		// 使用smtp身份验证
		prop.setProperty("mail.smtp.auth", "true"); // 使用SSL，企业邮箱必需！
		// 开启安全协议
		MailSSLSocketFactory sf = null;
		try {
			sf = new MailSSLSocketFactory();
			sf.setTrustAllHosts(true);
		} catch (GeneralSecurityException e1) {
			e1.printStackTrace();
		}
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.socketFactory", sf);
		//
		Session session = Session.getInstance(prop, new MyAuthenricator(account, pass));
		session.setDebug(true);

		MimeMessage mimeMessage = new MimeMessage(session);
		try {
			if (JudgeValue.isNullOfString(pib.getProposer())) {

			} else {
				proposer = pib.getProposer();
			}
			if (JudgeValue.isNullOfString(pib.getProposer())) {

			} else {
				proposer = pib.getProposer();
			}
			if (JudgeValue.isNullOrL0OfInteger(pib.getIshouseproperty())) {

			} else {
				if (pib.getIshouseproperty() == 1) {
					houseProerty = "是";
				} else {
					houseProerty = "否";
				}
			}

			if (JudgeValue.isNullOr0OfInteger(pib.getLoanamount())) {

			} else {
				loanAmount = pib.getLoanamount();
			}
			if (JudgeValue.isNullOfString(pib.getTel())) {

			} else {
				tel = pib.getTel();
			}
			if (JudgeValue.isNullOfString(pib.getEmail())) {

			} else {
				email = pib.getEmail();
			}
			// 设置邮件标题
			if (prefix != null && prefix.length() != 0) {
				mimeMessage.setSubject(prefix + "广尊科技贷款申请人员新增");
			} else {
				mimeMessage.setSubject("广尊科技贷款申请人员新增");
			}
			mimeMessage.setFrom(new InternetAddress(from, "op.alert@goodzoomtech.com"));
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			mimeMessage.setSentDate(data);
			mimeMessage.setText("用户" + proposer + "在" + time + "提出贷款申请：" + "\n\n用户姓名：" + proposer + "\n申请时间：" + time
					+ "\n有无房产：" + houseProerty + "\n贷款金额：" + loanAmount + "\n手机号码：" + tel + "\n电子邮件：" + email
					+ "\n\n请进行相应的业务处理。" + "\n本邮件由系统自动发出，请勿回复。");

			mimeMessage.saveChanges();
			Transport.send(mimeMessage);
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}