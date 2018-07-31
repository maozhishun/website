package com.gz.service;

import java.text.DateFormat;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gz.bean.ProposerInfoBean;
import com.gz.mapper.ProposerInfoMapper;
import com.gz.utils.ReturnData;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class QuartzService {
	@Autowired
	ProposerInfoMapper proposerInfoMapper;
	@Autowired
	MailService mailService;
	@Autowired
	ProposerService proposerService;
	// 邮件接收地址
	@Value("${mail_address}")
	private String receiver;
	// 邮件接收主题的前缀
	@Value("${mail_title_prefix}")
	private String prefix;

	// 每天下午5点启动
	@Scheduled(cron = "0 0 17 * * ?")
	// @Scheduled(cron = " */5 * * * * ?")
	public ReturnData summary() {
		ReturnData rd = new ReturnData();
		try {
			// 邮件主题与接收方
			String subject = null;
			ProposerInfoBean pib = new ProposerInfoBean();
			// 当前时间毫秒数
			long current = System.currentTimeMillis();
			// 今天零点零分零秒的毫秒数
			long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
			// 设置查询时间
			pib.setStartTime(zero);
			pib.setEndTime(current);
			// 不分页
			pib.setIsLimit(1);
			List<Map<String, Object>> List = proposerInfoMapper.listProposerInfo(pib);
			DateFormat dateFormat = DateFormat.getDateTimeInstance();
			if (List != null && !List.isEmpty()) {
				// 不为空，执行
				for (Map<String, Object> map : List) {

					if (map.get("createTime") == null) {
						map.put("createTime", "未录入");
					} else {
						map.put("createTime", dateFormat.format(map.get("createTime")));
					}
					if (map.get("tel") == null) {
						map.put("tel", "未录入");
					}
					if (map.get("proposer") == null) {
						map.put("proposer", "未录入");
					}
					if (map.get("loanAmount") == null) {
						map.put("loanAmount", "未录入");
					}
					if (map.get("isHouseProperty") == null) {
						map.put("isHouseProperty", "未录入");
					} else {
						if (map.get("isHouseProperty") == "0") {
							map.put("isHouseProperty", "无");
						} else {
							map.put("isHouseProperty", "有房产");
						}
					}
					if (map.get("email") == null) {
						map.put("email", "未录入");
					}
				}
				// 设置邮件标题
				if (prefix != null && prefix.length() != 0) {
					subject = prefix + "广尊科技官网今日贷款申请汇总";
				} else {
					subject = "广尊科技官网今日贷款申请汇总";
				}
				if (receiver != null && receiver.length() != 0) {
					mailService.sendMessageMail(List, subject, "message.ftl", receiver);
				}
				Logger.getLogger("邮件汇总发送成功！");
				return rd.okBack(200, "邮件汇总发送成功。");
			} else {// 为空，不执行。
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger("邮件汇总发送失败！");
			return rd.failBack(500, "邮件汇总发送失败！");
		}
		return null;
	}

}
