package com.boxun.pcdp.mi.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MiExamSetPojo implements Serializable{

	private static final long serialVersionUID = -2782183628900380747L;
	private Long projectId;
	private String projectName;
	private List<MiExamQuestionPojo> questions;
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public List<MiExamQuestionPojo> getQuestions() {
		return questions;
	}
	public void setQuestions(List<MiExamQuestionPojo> questions) {
		this.questions = questions;
	}
	
	public void addQuestion(MiExamQuestionPojo question){
		if(questions == null){
			questions = new ArrayList<MiExamQuestionPojo>();
		}
		questions.add(question);
	}
}
