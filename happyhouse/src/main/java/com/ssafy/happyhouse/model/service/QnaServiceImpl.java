package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ssafy.happyhouse.model.Qna;
import com.ssafy.happyhouse.model.mapper.QnaMapper;

@Service
public class QnaServiceImpl implements QnaService {
	
    @Autowired
	private QnaMapper QnaMapper;

    @Override
	public List<Qna> retrieveQna() {
		return QnaMapper.selectQna();
	}
    
  	@Override
	public boolean writeQna(Qna qna) {
		return QnaMapper.insertQna(qna) == 1;
	}

	@Override
	public Qna detailQna(int articleno) {
		return QnaMapper.selectQnaByNo(articleno);
	}

	@Override
	@Transactional
	public boolean updateQna(Qna board) {
		return QnaMapper.updateQna(board) == 1;
	}

	@Override
	@Transactional
	public boolean deleteQna(int articleno) {
		return QnaMapper.deleteQna(articleno) == 1;
	}
}