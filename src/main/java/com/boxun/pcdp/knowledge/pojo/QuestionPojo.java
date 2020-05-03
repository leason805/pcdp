package com.boxun.pcdp.knowledge.pojo;

import java.io.Serializable;

import com.boxun.estms.entity.Const;

public class QuestionPojo implements Serializable{

	private static final long serialVersionUID = 4643733746151250319L;
	
	public String question;
	public Const.QuestionType questionType;
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public Const.QuestionType getQuestionType() {
		return questionType;
	}
	public void setQuestionType(Const.QuestionType questionType) {
		this.questionType = questionType;
	}
	
}
