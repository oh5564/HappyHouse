package com.ssafy.happyhouse.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.AvgListDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.dongDto;
import com.ssafy.happyhouse.model.service.HouseMapService;

@RestController
@RequestMapping("/map")
@CrossOrigin("*")
public class HouseMapController {
	
	private final Logger logger = LoggerFactory.getLogger(HouseMapController.class);

	@Autowired
	private HouseMapService haHouseMapService;
	
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
		logger.debug("sido : {}", haHouseMapService.getSido());
		return new ResponseEntity<List<SidoGugunCodeDto>>(haHouseMapService.getSido(), HttpStatus.OK);
	}
	
	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(@RequestParam("sido") String sido) throws Exception {
		return new ResponseEntity<List<SidoGugunCodeDto>>(haHouseMapService.getGugunInSido(sido), HttpStatus.OK);
	}
	
	@GetMapping("/dong")
	public ResponseEntity<List<dongDto>> dong(@RequestParam("gugun") String gugun) throws Exception {
		return new ResponseEntity<List<dongDto>>(haHouseMapService.getDongInGugun(gugun), HttpStatus.OK);
	}
	
	@GetMapping("/apt")
	public ResponseEntity<List<HouseInfoDto>> apt(@RequestParam("dong") String dong) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getAptInDong(dong), HttpStatus.OK);
	}
	
	@GetMapping("/searchapt")
	public ResponseEntity<List<HouseInfoDto>> searchApt(@RequestParam("apt") String apt, @RequestParam("state") String state) throws Exception {
		//System.out.println(apt);
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getAptInName(apt,state), HttpStatus.OK);
	}
	
	@GetMapping("/avglist")
	public ResponseEntity<List<AvgListDto>> AvgList(@RequestParam("dong") String dong, @RequestParam("min") int min, @RequestParam("max") int max) throws Exception {
		return new ResponseEntity<List<AvgListDto>>(haHouseMapService.getAvgList(dong,min,max), HttpStatus.OK);
	}
	@GetMapping("/avglistSinCity")
	public ResponseEntity<List<AvgListDto>> AvgListSinCity(@RequestParam("min") int min, @RequestParam("max") int max) throws Exception {
		return new ResponseEntity<List<AvgListDto>>(haHouseMapService.getAvgListSinCity(min,max), HttpStatus.OK);
	}
	@GetMapping("/searchaptbyid")
	public ResponseEntity<HouseInfoDto> searchAptById(@RequestParam("apt") int apt) throws Exception {
		//System.out.println(apt);
		return new ResponseEntity<HouseInfoDto>(haHouseMapService.getAptbyId(apt), HttpStatus.OK);
	}
	@GetMapping("/max")
	public ResponseEntity<ArrayList<HouseInfoDto>> searchMax(@RequestParam("max") int max) throws Exception {
		return new ResponseEntity<ArrayList<HouseInfoDto>>(haHouseMapService.getAptInMax(max), HttpStatus.OK);
	
}
}
