package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		System.out.println("login-service!!!!");
		System.out.println("memberDto: "+ memberDto);
		if(memberDto.getUserid() == null || memberDto.getUserpwd() == null) {
			System.out.println("null");
			return null;
		}
		return memberMapper.login(memberDto);
	}

	@Override
	public MemberDto userInfo(String userid) throws Exception {
		return memberMapper.userInfo(userid);
	}

	@Override
	public void registerUser(MemberDto memberDto) throws Exception {
		memberMapper.registerUser(memberDto);
		
	}

	@Override
	public List<MemberDto> listUser() throws Exception {
		return memberMapper.listUser();
	}
	
	@Override
	public void updateUser(MemberDto memberDto) throws Exception {
		memberMapper.updateUser(memberDto);
	}

	@Override
	@Transactional
	public boolean deleteUser(String userid) throws Exception {
		return memberMapper.deleteUser(userid) == 1;
	}
	
//	@Override
//	public void deleteUser(String userid) throws Exception {
//		memberMapper.deleteUser(userid);
//	}
	
	
//	@Override
//	public int idCheck(String checkId) throws Exception {
//		return memberMapper.idCheck(checkId); // 0 or 1
//	}
//
//	@Override
//	public MemberDto getMember(String userId) throws Exception {
//		return memberMapper.getMember(userId);
//	}
//
}
