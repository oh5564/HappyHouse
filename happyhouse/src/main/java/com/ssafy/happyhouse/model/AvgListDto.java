package com.ssafy.happyhouse.model;

public class AvgListDto {
	private String gugunName;
	private String dealYear;
	private String avg;
	private int count;
	public String getDealYear() {
		return dealYear;
	}
	public String getGugunName() {
		return gugunName;
	}
	public void setSigunguCode(String gugunName) {
		this.gugunName = gugunName;
	}
	public void setDealYear(String dealYear) {
		this.dealYear = dealYear;
	}
	public String getAvg() {
		return avg;
	}
	public void setAvg(String avg) {
		this.avg = avg;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}



}
