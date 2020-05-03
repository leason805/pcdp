package com.boxun.pcdp.knowledge.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.boxun.pcdp.knowledge.entity.KQuestion;

public class ExamSetPojo implements Serializable{

	private static final long serialVersionUID = -6851869839560193765L;
	
	private List<KQuestion> questions;

	public List<KQuestion> getQuestions() {
		return questions;
	}

	public void setQuestions(List<KQuestion> questions) {
		this.questions = questions;
	}
	
	public void addQuestions(List<KQuestion> ques){
		if(questions == null){
			questions = new ArrayList<KQuestion>();
		}
		questions.addAll(ques);
	}
}
