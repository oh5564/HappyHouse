package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.MemberDto;

public interface MemberService {

	public MemberDto login(MemberDto memberDto) throws Exception;
	public MemberDto userInfo(String userid) throws Exception;
	public void registerUser(MemberDto memberDto) throws Exception;
	public List<MemberDto> listUser() throws Exception;
	public void updateUser(MemberDto memberDto) throws Exception;
	public boolean deleteUser(String userid) throws Exception;
	
}
