package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.CovidHospital;
import com.ssafy.happyhouse.model.mapper.CovidHospitalMapper;

@Service
public class CovidHospitalServiceImpl implements CovidHospitalService{
	
	@Autowired
	private CovidHospitalMapper hospitalMapper;
	
	@Override
	public List<CovidHospital> listHospital() throws Exception{
		return hospitalMapper.listHospital();
	}
	
	@Override
	public List<CovidHospital> getHospital(String name) throws Exception{
		return hospitalMapper.getHospital(name);
	}
	
	@Override
	public List<CovidHospital> getHospitalByLoc(String sigungu) throws Exception{
		return hospitalMapper.getHospitalByLoc(sigungu);
	}
	
	@Override
    public void registerHospital(CovidHospital covidHospital) throws Exception{
		hospitalMapper.registerHospital(covidHospital);
	}

}
