package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.Board;
import com.ssafy.happyhouse.model.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
    @Autowired
	private BoardMapper boardMapper;

    @Override
	public List<Board> retrieveBoard() {
		return boardMapper.selectBoard();
	}
    
  	@Override
	public boolean writeBoard(Board board) {
		return boardMapper.insertBoard(board) == 1;
	}

	@Override
	public Board detailBoard(int articleno) {
		return boardMapper.selectBoardByNo(articleno);
	}
	
	@Override
	public void updateHit(int articleno) {
		boardMapper.updateHit(articleno);
	}

	@Override
	@Transactional
	public boolean updateBoard(Board board) {
		return boardMapper.updateBoard(board) == 1;
	}

	@Override
	@Transactional
	public boolean deleteBoard(int articleno) {
		return boardMapper.deleteBoard(articleno) == 1;
	}
}