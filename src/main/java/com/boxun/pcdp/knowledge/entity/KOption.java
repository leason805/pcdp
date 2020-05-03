package com.boxun.pcdp.knowledge.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.boxun.estms.entity.Const;

@Entity
@Table(name = "KN_OPTION")
public class KOption implements Serializable{

	private static final long serialVersionUID = 6178303284483189811L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true)
	private Long id;

	@Column(name = "CODE")
	private String code;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ANSWER_TYPE")
	private Const.AnswerType answerType;
	
	@Column(name = "CONTENT", length=2000)
	private String content;
	
	@Column(name = "ANSWER_DESC", length=2000)
	private String answerDesc;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "QUESTION_ID")
	private KQuestion question;
	
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

	public String getAnswerDesc() {
		return answerDesc;
	}

	public void setAnswerDesc(String answerDesc) {
		this.answerDesc = answerDesc;
	}

	public KQuestion getQuestion() {
		return question;
	}

	public void setQuestion(KQuestion question) {
		this.question = question;
	}
}
