package com.ssafy.happyhouse.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ssafy.happyhouse.model.Qna;
import com.ssafy.happyhouse.model.service.QnaService;


@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/qna")
public class QnaController {

	private static final Logger logger = LoggerFactory.getLogger(QnaController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private QnaService boardService;

	@GetMapping("/list")
	public ResponseEntity<List<Qna>> retrieveBoard() throws Exception {
		logger.debug("retrieveQna - 호출");
		return new ResponseEntity<List<Qna>>(boardService.retrieveQna(), HttpStatus.OK);
	}
    
	@GetMapping("{articleno}")
	public ResponseEntity<Qna> detailBoard(@PathVariable int articleno) {
		logger.debug("detailQna - 호출");
		return new ResponseEntity<Qna>(boardService.detailQna(articleno), HttpStatus.OK);
	}


	@PostMapping("/write")
	public ResponseEntity<String> writeBoard(@RequestBody Qna qna) {
		logger.debug("writeQna - 호출");
		if (boardService.writeQna(qna)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@PutMapping("{articleno}")
	public ResponseEntity<String> updateBoard(@RequestBody Qna qna) {
		logger.debug("updateQna - 호출");
		logger.debug("" + qna);
		
		if (boardService.updateQna(qna)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    
	@DeleteMapping("{articleno}")
	public ResponseEntity<String> deleteBoard(@PathVariable int articleno) {
		logger.debug("deleteQna - 호출");
		if (boardService.deleteQna(articleno)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
