package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.Board;

public interface BoardService { //test
	public List<Board> retrieveBoard();
	public Board detailBoard(int articleno);
	public void updateHit(int articleno);
	public boolean writeBoard(Board board);
	public boolean updateBoard(Board board);
	public boolean deleteBoard(int articleno);
}
