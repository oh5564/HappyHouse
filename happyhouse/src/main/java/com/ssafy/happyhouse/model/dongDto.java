package com.ssafy.happyhouse.model;

public class dongDto {

	private String dongCode;
	private String dongName;
	public dongDto(String dongCode, String dongName) {
		this.dongCode = dongCode;
		this.dongName = dongName;
	}
	public String getDongCode() {
		return dongCode;
	}
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}
	public String getDongName() {
		return dongName;
	}
	public void setDongName(String dongName) {
		this.dongName = dongName;
	}


}
