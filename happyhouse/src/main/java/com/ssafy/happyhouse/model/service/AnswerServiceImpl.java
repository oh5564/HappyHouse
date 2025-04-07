package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.Answer;
import com.ssafy.happyhouse.model.Qna;
import com.ssafy.happyhouse.model.mapper.AnswerMapper;

@Service
public class AnswerServiceImpl implements AnswerService{
	    @Autowired
		private AnswerMapper AnswerMapper;

	    @Override
		public List<Answer> retrieveAnswer() {
			return AnswerMapper.selectAnswer();
		}
	    
	    @Override
	    public List<Answer> retrieveByQnano(int qnano){
	    	return AnswerMapper.selectAnswerByNo(qnano);
	    }
	    
	    @Override
	    public Answer detailAnswer(int ansno) {
	    	return AnswerMapper.detailAnswer(ansno);
	    }
	    
	  	@Override
		public boolean writeAnswer(Answer answer) {
			return AnswerMapper.insertAnswer(answer) == 1;
		}

		@Override
		@Transactional
		public boolean updateAnswer(Answer answer) {
			return AnswerMapper.updateAnswer(answer) == 1;
		}

		@Override
		@Transactional
		public boolean deleteAnswer(int ansno) {
			return AnswerMapper.deleteAnswer(ansno) == 1;
		}
		
		@Override
		@Transactional
		public boolean deleteAnswerByQnano(int qnano) {
			return AnswerMapper.deleteAnswerByQnano(qnano) == 1;
		}

//		@Override
//		public Answer detailAnswer(int ansno) {
//			return AnswerMapper.selectAnswerByNo(ansno);
//		}
		
		

}
