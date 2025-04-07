package com.ssafy.happyhouse.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.happyhouse.model.FavoriteDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.service.FavoriteService;
import com.ssafy.happyhouse.model.service.HouseMapService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/favorite")
@CrossOrigin("*")
public class FavoriteController {
	
	private final Logger logger = LoggerFactory.getLogger(FavoriteController.class);
	
	@Autowired
	private FavoriteService favoriteService;
	
	@Autowired
	private HouseMapService houseService;
	
	//관심매물 조회 페이지 이동
		@ApiOperation(value = "관심매물 조회 페이지 이동", notes = "관심매물 조회 페이지로 이동")
		@GetMapping(value = "/page")
		public ModelAndView hospitalPage(HttpSession session) throws Exception {
			
			MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
			ModelAndView mav = new ModelAndView();
			if(memberDto != null) {
				mav.setViewName("favorite/fvmain");
				return mav;
			}else {
				mav.addObject("msg", "관심목록 서비스는 로그인 후 이용할 수 있습니다.");
				mav.setViewName("user/login");
				return mav;
			}
					
	}
	
	//관심매물 추가
	@ApiOperation(value = "관심매물 추가", notes = "관심매물을 추가합니다.")
	@PostMapping(value = "/addFavorite")
	public ResponseEntity<?> addFavorite(@RequestBody FavoriteDto favoriteDto) throws Exception {
		//System.out.println("favoriteDto: " + favoriteDto); //for test
		
		int aptId = favoriteDto.getFvitem(); //추가 버튼 클릭 시 보내진 아파트코드
		String userId = favoriteDto.getFvuserid(); //추가 버튼 클릭시 보내진 유저id
		//기존에 담겨있던 관심매물 리스트
		List<FavoriteDto> favorites = favoriteService.listFavorite(userId);
		
		for(int i = 0; i < favorites.size(); i++) {
			//기존 리스트에 있던 매물을 중복 추가하려고하면
			if(aptId == favorites.get(i).getFvitem()) {	
				//알림..?
				System.out.println("중복입니다");
				return new ResponseEntity<String>("중복", HttpStatus.NO_CONTENT);
			}
		}
		
		favoriteService.addFavorite(favoriteDto);
		String fvuserid = favoriteDto.getFvuserid();
		List<FavoriteDto> list = favoriteService.listFavorite(fvuserid);
		return new ResponseEntity<List<FavoriteDto>>(list, HttpStatus.OK);
	}
	
	//관심매물 삭제
	@ApiOperation(value = "관심매물 삭제", notes = "관심매물을 삭제합니다.")
	@DeleteMapping(value = "/deleteFavorite")
	public ResponseEntity<?> deleteFavorite(@RequestBody FavoriteDto favoriteDto) throws Exception {
		try {
			int fvid = favoriteDto.getFvid();
			String fvuserid = favoriteDto.getFvuserid();
			favoriteService.deleteFavorite(fvid);
			List<FavoriteDto> list = favoriteService.listFavorite(fvuserid);
			return new ResponseEntity<List<FavoriteDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	//관심매물 리스트
	@ApiOperation(value = "전체 관심매물 목록 조회", notes = "전체 관심매물 목록 반환")
	@ApiResponses({
		@ApiResponse(code = 200, message = "관심으로 등록하신 매물 목록입니다."),
		@ApiResponse(code = 404, message = "페이지가 없습니다."),
		@ApiResponse(code = 500, message = "서버에 에러가 발생했습니다.")
	})
	@GetMapping(value = "/listFavorite")
	public ResponseEntity<?> listFavorite(@RequestParam("fvuserid") String fvuserid) throws Exception {
		List<FavoriteDto> list = favoriteService.listFavorite(fvuserid);
		System.out.println(list.toString());
		
		List<HouseInfoDto> houseList = new ArrayList<>();
		
		for(int i = 0; i < list.size(); i++) {
			int aptNum = list.get(i).getFvitem();
			HouseInfoDto house = houseService.getAptbyId(aptNum);
			houseList.add(house);
		}
		
		if(list != null && !list.isEmpty()) {
			return new ResponseEntity<List<HouseInfoDto>>(houseList, HttpStatus.OK);
		} else {

			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

}
