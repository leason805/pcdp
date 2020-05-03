package com.boxun.pcdp.mi.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.boxun.estms.entity.Const;

public class MiExamQuestionPojo implements Serializable{

	private static final long serialVersionUID = 8188708565165529343L;
	private Long id;
	private String question;
	private Const.QuestionType questionType;
	private List<MiExamOptionPojo> options;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public List<MiExamOptionPojo> getOptions() {
		return options;
	}
	public void setOptions(List<MiExamOptionPojo> options) {
		this.options = options;
	}
	
	public void addOption(MiExamOptionPojo option){
		if(options == null){
			options = new ArrayList<MiExamOptionPojo>();
		}
		options.add(option);
	}
}
