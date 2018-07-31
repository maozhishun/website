package com.gz.utils;



public class ReturnPageData extends ReturnData{
	private Integer recordTotle;

	public ReturnPageData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReturnPageData(Integer code, Integer num, String msg, Object reObj) {
		super(code, num, msg, reObj);
		// TODO Auto-generated constructor stub
	}

	public ReturnPageData(Integer code, Integer num, String msg) {
		super(code, num, msg);
		// TODO Auto-generated constructor stub
	}

	public ReturnPageData(Integer restNum) {
		super(restNum);
		// TODO Auto-generated constructor stub
	}

	

	public Integer getRecordTotle() {
		return recordTotle;
	}

	public void setRecordTotle(Integer recordTotle) {
		this.recordTotle = recordTotle;
	}

	@Override
	public ReturnData back(Integer code, Integer num, String msg) {
		// TODO Auto-generated method stub
		 super.back(code, num, msg);
		 return this;
	}
}
