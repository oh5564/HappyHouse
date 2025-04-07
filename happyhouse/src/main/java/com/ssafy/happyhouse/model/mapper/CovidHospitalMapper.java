package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.CovidHospital;

@Mapper
public interface CovidHospitalMapper {
	
	List<CovidHospital> listHospital() throws Exception;
	List<CovidHospital> getHospital(String name) throws Exception;
	List<CovidHospital> getHospitalByLoc(String sigungu) throws Exception;
	void registerHospital(CovidHospital covidHospital) throws Exception;

}
