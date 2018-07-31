package com.gz.model;

import java.io.Serializable;
import java.util.List;

public class Mail implements Serializable {

	/**
	 * 邮件实体
	 */
	private static final long serialVersionUID = 2743402021655488340L;
	
	public static final String ENCODING="UTF-8";
	
	private String host; // 服务器地址
	private String protocol;//邮件协议
	private String port;//邮件服务器发送端口
	private String sender; // 发件人的邮箱  
	private String receiver; // 收件人的邮箱
	private String nickName; // 发件人昵称
	private String username; // 账号
	private String password; // 密码
	private String subject; // 主题  
	private String mailBodyContent; // 信息(支持HTML)  
	
	private List<String> copyToList;//抄送人列表
	private List<String> bccList;//密送人列表
	private List<String> receiverList;//收件人列表
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMailBodyContent() {
		return mailBodyContent;
	}
	public void setMailBodyContent(String mailBodyContent) {
		this.mailBodyContent = mailBodyContent;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<String> getReceiverList() {
		return receiverList;
	}
	public void setReceiverList(List<String> receiverList) {
		this.receiverList = receiverList;
	}
	public List<String> getCopyToList() {
		return copyToList;
	}
	public void setCopyToList(List<String> copyToList) {
		this.copyToList = copyToList;
	}
	public List<String> getBccList() {
		return bccList;
	}
	public void setBccList(List<String> bccList) {
		this.bccList = bccList;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}

}
