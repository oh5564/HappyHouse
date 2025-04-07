package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.AvgListDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.dongDto;

@Mapper
public interface HouseMapMapper {

	List<SidoGugunCodeDto> getSido() throws SQLException;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws SQLException;
	List<dongDto> getDongInGugun(String gugun) throws SQLException;
	List<HouseInfoDto> getAptInDong(String dong) throws SQLException;
	List<HouseInfoDto> getAptInName(String apt,String state) throws SQLException;
	ArrayList<HouseInfoDto> getAptInMax(int max) throws SQLException;
	HouseInfoDto getAptbyId(int id) throws SQLException;
	List<AvgListDto> getAvgList(String dong, double min,double max);
	List<AvgListDto> getAvgListSinCity(double min,double max);
	
}
