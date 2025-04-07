package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.Hospital;

@Mapper
public interface HospitalMapper {
	List<Hospital> listHospital() throws Exception;

}
