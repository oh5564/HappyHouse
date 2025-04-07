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
import com.ssafy.happyhouse.model.Answer;
import com.ssafy.happyhouse.model.Qna;
import com.ssafy.happyhouse.model.service.AnswerService;


@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/answer")
public class AnswerController {

	private static final Logger logger = LoggerFactory.getLogger(AnswerController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private AnswerService answerService;

	//1. 답변 리스트 전체 조회
	@GetMapping("/list")
	public ResponseEntity<List<Answer>> retrieveAnswer() throws Exception {
		logger.debug("retrieveAnswer - 호출");
		return new ResponseEntity<List<Answer>>(answerService.retrieveAnswer(), HttpStatus.OK);
	}
	
	//2. 특정 질문에 대한 답변 리스트 조회
	@GetMapping("{qnano}")
	public ResponseEntity<List<Answer>> retrieveByQnano(@PathVariable int qnano) {
		logger.debug("retrieveByQnano - 호출");
		return new ResponseEntity<List<Answer>>(answerService.retrieveByQnano(qnano), HttpStatus.OK);
	}
    
	//3. 답변 번호로 검색
	@GetMapping("/detail/{ansno}")
	public ResponseEntity<Answer> detailAnswer(@PathVariable int ansno) {
		logger.debug("detailAnswer - 호출");
		return new ResponseEntity<Answer>(answerService.detailAnswer(ansno), HttpStatus.OK);
	}
	
	//4. 답변 추가
	@PostMapping("/write")
	public ResponseEntity<String> writeAnswer(@RequestBody Answer answer) {
		logger.debug("writeAnswer - 호출");
		if (answerService.writeAnswer(answer)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	//5. 답변수정
	@PutMapping("{ansno}")
	public ResponseEntity<String> updateAnswer(@RequestBody Answer answer) {
		logger.debug("updateAnswer - 호출");
		logger.debug("" + answer);
		
		if (answerService.updateAnswer(answer)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    //6. 답변 번호로 답변 삭제
	@DeleteMapping("{ansno}")
	public ResponseEntity<String> deleteAnswer(@PathVariable int ansno) {
		logger.debug("deleteAnswer - 호출");
		if (answerService.deleteAnswer(ansno)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	//7. 질문 번호로 답변 한꺼번에 삭제
	@DeleteMapping("/deleteByAnsno/{qnano}")
	public ResponseEntity<String> deleteAnswerByQnano(@PathVariable int qnano) {
		logger.debug("deleteAnswer - 호출");
		if (answerService.deleteAnswerByQnano(qnano)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
}
