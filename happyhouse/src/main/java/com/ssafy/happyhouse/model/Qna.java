package com.ssafy.happyhouse.model;

public class Qna {
	private int articleno;
	private String userid;
	private String subject;
	private String content;
	private String regtime;

	public int getArticleno() {
		return articleno;
	}

	public void setArticleno(int articleno) {
		this.articleno = articleno;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	public String getRegtime() {
		return regtime;
	}

	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}

	public Qna(int articleno, String userid, String subject, String content,String regtime) {
		this.articleno = articleno;
		this.userid = userid;
		this.subject = subject;
		this.content = content;
		this.regtime = regtime;
	}

}