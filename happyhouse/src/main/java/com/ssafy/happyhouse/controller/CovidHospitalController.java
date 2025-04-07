package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.happyhouse.model.CovidHospital;
import com.ssafy.happyhouse.controller.CovidHospitalController;
import com.ssafy.happyhouse.model.service.CovidHospitalService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/covidhospital")
@CrossOrigin("*")
@Api("코로나 선별진료소 컨트롤러  API")
public class CovidHospitalController {
	 
	private static final Logger logger = LoggerFactory.getLogger(CovidHospitalController.class);
	
	@Autowired
	private CovidHospitalService covidHospitalService;
	
	//0. 코로나 선별진료소 메인페이지 이동
	@ApiOperation(value = "코로나 선별진료소 메인화면 이동", notes = "코로나 선별진료소 <big>메인 메뉴 선택화면</big>으로 갑니다.")
	@GetMapping(value = "/page")
	public ModelAndView hospitalPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("covidhospital/hpmain");
		return mav;
	}
	
	// 1. 코로나 선별진료소 전체 조회
	@ApiOperation(value = "전체 코로나 선별진료소 목록 조회", notes = "코로나 선별진료소 <big>전체 목록</big>을 반환해 줍니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "코로나선별진료소 목록입니다."),
		@ApiResponse(code = 404, message = "페이지가 없습니다."),
		@ApiResponse(code = 500, message = "서버에 에러가 발생했습니다.")
	})
	@GetMapping(value = "/hospital")
	public ResponseEntity<?> hospitalList() throws Exception {
		List<CovidHospital> list = covidHospitalService.listHospital();
		System.out.println(list.toString());
		if(list != null && !list.isEmpty()) {
			return new ResponseEntity<List<CovidHospital>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	// 2. 코로나 선별진료소 이름으로 검색
	@ApiOperation(value = "코로나 선별진료소 이름으로 검색", notes = "특정 코로나 선별진료소에 대한 정보.")
	@GetMapping(value = "/hospital/name")
	public ResponseEntity<?> hospitalInfo(@RequestParam("name") String hospitalName) throws Exception {
		//logger.debug("파라미터 : {}", hospitalName);
		System.out.println("test - search by name");
		List<CovidHospital> list = covidHospitalService.getHospital(hospitalName);
		//System.out.println(list.toString());
		if(list != null && !list.isEmpty()) {
			return new ResponseEntity<List<CovidHospital>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	// 3. 근처 지역 코로나 선별진료소 검색 
	@ApiOperation(value = "코로나 선별진료소 지역으로 검색", notes = "특정 코로나 선별진료소에 대한 정보.")
	@GetMapping(value = "/hospital/loc")
	public ResponseEntity<?> hospitalInfoByLoc(@RequestParam("sigungu") String hospitalLoc) throws Exception {
		//logger.debug("파라미터 : {}", hospitalLoc);
		System.out.println("test - search by loc");
		List<CovidHospital> list = covidHospitalService.getHospitalByLoc(hospitalLoc);
		if(list != null && !list.isEmpty()) {
			return new ResponseEntity<List<CovidHospital>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	// 4. 코로나 선별진료소 등록(admin 전용)
	@ApiOperation(value = "코로나 선별진료소 등록", notes = "선별진료소의 정보를 받아 등록.")
	@PostMapping(value = "/hospital")
	public ResponseEntity<?> hospitalRegister(@RequestBody CovidHospital covidHospital) throws Exception {
		covidHospitalService.registerHospital(covidHospital);
		List<CovidHospital> list = covidHospitalService.listHospital();
		return new ResponseEntity<List<CovidHospital>>(list, HttpStatus.OK);
	}
	
	

}
