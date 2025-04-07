package com.ssafy.happyhouse.model;

public class Answer {
	private int qnano; //(qnas: articleno) 질문번호
	private int ansno; //답변번호, pk
	private String answriter; //(ssafy_member: userid) 작성자 아이디
	private String anscontent; //답변 내용
	private boolean isselected; //답변 채택 여부
	private boolean isrecommended; //추천 여부
	private int upvote; //추천수
	private String regtime; //작성일자
	
	public Answer(int qnano, int ansno, String answriter, String anscontent, boolean isselected, boolean isrecommended,
			int upvote, String regtime) {
		super();
		this.qnano = qnano;
		this.ansno = ansno;
		this.answriter = answriter;
		this.anscontent = anscontent;
		this.isselected = isselected;
		this.isrecommended = isrecommended;
		this.upvote = upvote;
		this.regtime = regtime;
	}
	
	public int getQnano() {
		return qnano;
	}
	public void setQnano(int qnano) {
		this.qnano = qnano;
	}
	public int getAnsno() {
		return ansno;
	}
	public void setAnsno(int ansno) {
		this.ansno = ansno;
	}
	public String getAnswriter() {
		return answriter;
	}
	public void setAnswriter(String answriter) {
		this.answriter = answriter;
	}
	public String getAnscontent() {
		return anscontent;
	}
	public void setAnscontent(String anscontent) {
		this.anscontent = anscontent;
	}
	public boolean getIsselected() {
		return isselected;
	}
	public void setIsselected(boolean isselected) {
		this.isselected = isselected;
	}
	public int getUpvote() {
		return upvote;
	}
	public void setUpvote(int upvote) {
		this.upvote = upvote;
	}
	public String getRegtime() {
		return regtime;
	}
	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}
	
	public boolean isIsrecommended() {
		return isrecommended;
	}

	public void setIsrecommended(boolean isrecommended) {
		this.isrecommended = isrecommended;
	}

	@Override
	public String toString() {
		return "Answer [qnano=" + qnano + ", ansno=" + ansno + ", answriter=" + answriter + ", anscontent=" + anscontent
				+ ", isselected=" + isselected + ", isrecommended=" + isrecommended + ", upvote=" + upvote
				+ ", regtime=" + regtime + "]";
	}

}
