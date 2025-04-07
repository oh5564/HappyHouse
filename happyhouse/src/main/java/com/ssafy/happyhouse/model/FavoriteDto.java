package com.ssafy.happyhouse.model;

/* 관심매물 등록 Dto */
public class FavoriteDto {
	private int fvid; //관심매물 아이디
	private String fvuserid; //유저 아이디 (외래키 - ssafy_member userid)
	private int fvitem; //매물 아이디(외래키 - housedeal no)
	
	public FavoriteDto() {
	}
	
	public FavoriteDto(int fvid, String fvuserid, int fvitem) {
		super();
		this.fvid = fvid;
		this.fvuserid = fvuserid;
		this.fvitem = fvitem;
	}
	
	public int getFvid() {
		return fvid;
	}
	public void setFvid(int fvid) {
		this.fvid = fvid;
	}
	public String getFvuserid() {
		return fvuserid;
	}
	public void setFvuserid(String fvuserid) {
		this.fvuserid = fvuserid;
	}
	public int getFvitem() {
		return fvitem;
	}
	public void setFvitem(int fvitem) {
		this.fvitem = fvitem;
	}

	@Override
	public String toString() {
		return "FavoriteDto [fvid=" + fvid + ", fvuserid=" + fvuserid + ", fvitem=" + fvitem + "]";
	}
	
}
