package com.gz.utils;



import java.util.List;


public class ReturnData {
	private Integer restNum;//接口编号
	private Integer code;//状态码
	private Integer status;//状态编号
	private String msg;//状态消息
	private Object reObj;//返回
	
	

	@Override
	public String toString() {
		if (null != reObj) {
			if (reObj instanceof List) {
				return "ReturnData [restNum=" + restNum + ", code=" + code + ", status=" + status + ", msg=" + msg + "]";
			}
		}
		return "ReturnData [restNum=" + restNum + ", code=" + code + ", status=" + status + ", msg=" + msg + ", reObj="
				+ reObj + "]";
	}



	public ReturnData(Integer restNum) {
		super();
		this.restNum = restNum;
	}



	public Integer getRestNum() {
		return restNum;
	}
	public void setRestNum(Integer restNum) {
		this.restNum = restNum;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getReObj() {
		return reObj;
	}
	public void setReObj(Object reObj) {
		this.reObj = reObj;
	}
	public ReturnData(Integer code, Integer status, String msg, Object reObj) {
		super();
		this.code = code;
		this.status = status;
		this.msg = msg;
		this.reObj = reObj;
	}
	public ReturnData(Integer code, Integer status, String msg) {
		super();
		this.code = code;
		this.status = status;
		this.msg = msg;
	}
	
	public ReturnData() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ReturnData back(Integer code, Integer status, String msg){
		setCode(code);
		setStatus(status);
		setMsg(msg);
		return this;
	}
	
	public ReturnData failBack(Integer status, String msg){
		setCode(0);
		setStatus(status);
		setMsg(msg);
		return this;
	}
	public ReturnData failBack(String msg){
		setCode(0);
		setStatus(0);
		setMsg(msg);
		return this;
	}
	public ReturnData failBack(){
		setCode(0);
		setStatus(0);
		setMsg("fail");
		return this;
	}
	public ReturnData okBack(Integer status, String msg){
		setCode(1);
		setStatus(status);
		setMsg(msg);
		return this;
	}
	public ReturnData okBack(Integer status, String msg,Object reObj){
		setCode(1);
		setStatus(status);
		setMsg(msg);
		setReObj(reObj);
		return this;
	}
	public ReturnData okBack(){
		setCode(1);
		setStatus(1);
		setMsg("OK");
		return this;
	}
	public ReturnData okBack(String msg){
		setCode(1);
		setStatus(1);
		setMsg(msg);
		return this;
	}
	public ReturnData okBack(Object reObj){
		setCode(1);
		setStatus(1);
		setMsg("OK");
		setReObj(reObj);
		return this;
	}
	
}
