package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="MemberDto (회원정보)", description = "회원의 아이디, 비밀번호, 이름 , 이메일, 가입일의 정보")
public class MemberDto {
	
	@ApiModelProperty(value = "회원의 아이디")
	private String userid;
	
	@ApiModelProperty(value = "회원의 이름")
	private String username;
	
	@ApiModelProperty(value = "비밀번호")
	private String userpwd;
	
	@ApiModelProperty(value = "이메일")
	private String email;
	
	@ApiModelProperty(value = "가입일")
	private String joindate;

	
	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}


	public String getUserpwd() {
		return userpwd;
	}



	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getJoindate() {
		return joindate;
	}



	public void setJoindate(String joindate) {
		this.joindate = joindate;
	}



	@Override
	public String toString() {
		return "MemberDto [username=" + username + ", userid=" + userid + ", userpwd=" + userpwd + ", email=" + email
				+ ", joindate=" + joindate + "]";
	}

}
