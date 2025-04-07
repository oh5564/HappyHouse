package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.MemberDto;

@Mapper
public interface MemberMapper {

	public MemberDto login(MemberDto memberDto) throws SQLException;
	public MemberDto userInfo(String userid) throws SQLException;
 	public void registerUser(MemberDto memberDto) throws Exception;	
 	public List<MemberDto> listUser() throws Exception;
	public void updateUser(MemberDto memberDto) throws Exception;
	public int deleteUser(String userid) throws Exception;

}
