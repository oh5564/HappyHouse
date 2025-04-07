package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.Answer;

public interface AnswerService {
	public List<Answer> retrieveAnswer();
	public List<Answer> retrieveByQnano(int qnano);
	public Answer detailAnswer(int ansno);
	public boolean writeAnswer(Answer ans);
	public boolean updateAnswer(Answer ans);
	public boolean deleteAnswer(int ansno);
	public boolean deleteAnswerByQnano(int qnano);

}
