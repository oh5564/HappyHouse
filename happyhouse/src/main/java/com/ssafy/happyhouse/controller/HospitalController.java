package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.Hospital;
import com.ssafy.happyhouse.model.service.HospitalService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/hospital")
@CrossOrigin("*")
@Api("병원 컨트롤러  API")
public class HospitalController {
	
	private static final Logger logger = LoggerFactory.getLogger(HospitalController.class);
	
	@Autowired
	private HospitalService hospitalService;
	
	// 1. 병원 전체 조회
		@ApiOperation(value = "전체 병원 목록 조회", notes = "병원 <big>전체 목록</big>을 반환해 줍니다.")
		@ApiResponses({
			@ApiResponse(code = 200, message = "병원 목록입니다."),
			@ApiResponse(code = 404, message = "페이지가 없습니다."),
			@ApiResponse(code = 500, message = "서버에 에러가 발생했습니다.")
		})
		@GetMapping(value = "/hospital")
		public ResponseEntity<?> hospitalList() throws Exception {
			List<Hospital> list = hospitalService.listHospital();
			System.out.println(list.toString());
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<Hospital>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		}
		
	// 2. 병원 이름으로 검색
	// 3. 병원 지역명으로 검색 (읍면동명)
	// 4. 진료과목명으로 검색(진료과목코드)
	// 5. 좌표, 반경으로 검색 (x, y좌표, radius)	
	

}
