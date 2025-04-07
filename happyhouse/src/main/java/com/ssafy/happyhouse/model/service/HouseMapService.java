package com.ssafy.happyhouse.model.service;

import java.util.ArrayList;
import java.util.List;

import com.ssafy.happyhouse.model.AvgListDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.dongDto;

public interface HouseMapService {

	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<dongDto> getDongInGugun(String gugun) throws Exception;
	List<HouseInfoDto> getAptInDong(String dong) throws Exception;
	List<HouseInfoDto> getAptInName(String apt,String state) throws Exception;
	ArrayList<HouseInfoDto> getAptInMax(int max)throws Exception;
	HouseInfoDto getAptbyId(int apt) throws Exception;
	List<AvgListDto> getAvgList(String dong, int min,int max);
	List<AvgListDto> getAvgListSinCity(double min,double max);
}
