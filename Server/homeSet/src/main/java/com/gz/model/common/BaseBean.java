package com.gz.model.common;

import com.gz.utils.ReturnData;

/**
 * 这个类用来存放通用变量，供实体类继承
 * @author admin
 *
 */
public class BaseBean {
	
	private String gzAdminId;//广尊管理员账号
	private String IP;//ip,通常用来做安全校验
	
	private String orderBy;//排序 拼接格式 字段 正反序 如: user_id desc
	private Integer isLimit =0;//是否分页 默认0是 1否(如果此值为1则下面4个分页参数无效)
	private Integer pageSize =10;//单页数量:默认10
	private Integer pageNum =1;//页码:默认1
	private Integer beginNum =0;//分页,从第几条开始.默认0,优先级低于pageNum
	private Integer checkNum =10;//分页,每页几条数据,默认10,优先级低于pageSize
	
	private Long startTime;
	private Long endTime;
	
	
	
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	private ReturnData rd;
	
	
	
	
	public String getGzAdminId() {
		return gzAdminId;
	}
	public void setGzAdminId(String gzAdminId) {
		this.gzAdminId = gzAdminId;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	public ReturnData getRd() {
		if (null == rd) {
			rd = new ReturnData();
		}
		return rd;
	}
	public void setRd(ReturnData rd) {
		this.rd = rd;
	}
	
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		//防sql注入
		if (!(null != orderBy && (orderBy.contains("(") || orderBy.contains(";")))) {
			this.orderBy = orderBy;
		}
	}
	public Integer getIsLimit() {
		return isLimit;
	}
	public void setIsLimit(Integer isLimit) {
		this.isLimit = isLimit;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
		this.checkNum = pageSize;
		if (!pageNum.equals(1)) {
			this.beginNum = (pageNum - 1) * pageSize;
		}
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
		this.beginNum = (pageNum - 1) * pageSize;
	}
	public Integer getBeginNum() {
		return beginNum;
	}
	public void setBeginNum(Integer beginNum) {
		this.beginNum = beginNum;
	}
	public Integer getCheckNum() {
		return checkNum;
	}
	public void setCheckNum(Integer checkNum) {
		this.checkNum = checkNum;
	}
	@Override
	public String toString() {
		return "BaseBean [gzAdminId=" + gzAdminId + ", IP=" + IP + ", orderBy=" + orderBy + ", isLimit=" + isLimit
				+ ", pageSize=" + pageSize + ", pageNum=" + pageNum + ", beginNum=" + beginNum + ", checkNum="
				+ checkNum + ", startTime=" + startTime + ", endTime=" + endTime + ", rd=" + rd + "]";
	}
	
	

}
