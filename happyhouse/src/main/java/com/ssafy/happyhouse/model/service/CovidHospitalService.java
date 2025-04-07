package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.CovidHospital;

public interface CovidHospitalService {
	
	List<CovidHospital> listHospital() throws Exception;
	List<CovidHospital> getHospital(String name) throws Exception;
	List<CovidHospital> getHospitalByLoc(String sigungu) throws Exception;
    void registerHospital(CovidHospital covidHospital) throws Exception;
}
