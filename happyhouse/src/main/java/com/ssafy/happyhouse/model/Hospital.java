package com.ssafy.happyhouse.model;

public class Hospital {
	private String hospitalname; //병원명 yadmNm
	private String codename; //종별코드명 clCdNm
//	private String sidocode;
//	private String sidoname;
//	private String sigungucode;
//	private String sigunguname;
	private String dongname; //읍면동명 emdongNm
	private String address; //주소 addr
	private String phone; //전화번호 telno
	private String homepage; //홈페이지 hospUrl
	private float xloc; //x좌표 XPos
	private float yloc; //y좌표 YPos
	
	public String getHospitalname() {
		return hospitalname;
	}
	public void setHospitalname(String hospitalname) {
		this.hospitalname = hospitalname;
	}
	public String getCodename() {
		return codename;
	}
	public void setCodename(String codename) {
		this.codename = codename;
	}
	public String getDongname() {
		return dongname;
	}
	public void setDongname(String dongname) {
		this.dongname = dongname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public float getXloc() {
		return xloc;
	}
	public void setXloc(float xloc) {
		this.xloc = xloc;
	}
	public float getYloc() {
		return yloc;
	}
	public void setYloc(float yloc) {
		this.yloc = yloc;
	}
	
	@Override
	public String toString() {
		return "Hospital [hospitalname=" + hospitalname + ", codename=" + codename + ", dongname=" + dongname
				+ ", address=" + address + ", phone=" + phone + ", homepage=" + homepage + ", xloc=" + xloc + ", yloc="
				+ yloc + "]";
	}

}
