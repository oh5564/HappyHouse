package com.ssafy.happyhouse.model;

public class HouseInfoDto {

	private String aptCode;
	private String apartmentName;
	private String dongCode;
	private String dongName;
	private int buildYear;
	private int floor;
	private String jibun;
	private String lat;
	private String lng;
	private String img;
	private String recentPrice;
	private String sellYear;

	public String getAptCode() {
		return aptCode;
	}

	public void setAptCode(String aptCode) {
		this.aptCode = aptCode;
	}

	public String getApartmentName() {
		return apartmentName;
	}

	public void setApartmentName(String aptName) {
		this.apartmentName = aptName;
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

	public int getBuildYear() {
		return buildYear;
	}

	public void setBuildYear(int buildYear) {
		this.buildYear = buildYear;
	}

	public String getJibun() {
		return jibun;
	}

	public void setJibun(String jibun) {
		this.jibun = jibun;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getRecentPrice() {
		return recentPrice;
	}

	public void setRecentPrice(String recentPrice) {
		this.recentPrice = recentPrice;
	}
	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public String getSellYear() {
		return sellYear;
	}

	public void setSellYear(String sellYear) {
		this.sellYear = sellYear;
	}

}
