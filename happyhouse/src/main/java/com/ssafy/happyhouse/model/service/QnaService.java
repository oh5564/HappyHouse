package com.ssafy.happyhouse.model.service;

import java.util.List;
import com.ssafy.happyhouse.model.Qna;

public interface QnaService {
	public List<Qna> retrieveQna();
	public Qna detailQna(int articleno);
	public boolean writeQna(Qna qna);
	public boolean updateQna(Qna qna);
	public boolean deleteQna(int articleno);
}
