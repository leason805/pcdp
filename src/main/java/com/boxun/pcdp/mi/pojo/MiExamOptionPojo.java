package com.boxun.pcdp.mi.pojo;

import java.io.Serializable;

import com.boxun.estms.entity.Const;

public class MiExamOptionPojo implements Serializable{

	private static final long serialVersionUID = -3877822667278881118L;
	private Long id;
	private String code;
	private Const.AnswerType answerType;
	private String content;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Const.AnswerType getAnswerType() {
		return answerType;
	}
	public void setAnswerType(Const.AnswerType answerType) {
		this.answerType = answerType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
