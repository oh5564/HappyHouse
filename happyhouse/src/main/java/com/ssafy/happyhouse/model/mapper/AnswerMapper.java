package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.Answer;

@Mapper
public interface AnswerMapper {
	public List<Answer> selectAnswer();
	public List<Answer> selectAnswerByNo(int qnano);
	public Answer detailAnswer(int ansno);
	public int insertAnswer(Answer answer);
	public int updateAnswer(Answer answer);
	public int deleteAnswer(int ansno);
	public int deleteAnswerByQnano(int qnano);

}
