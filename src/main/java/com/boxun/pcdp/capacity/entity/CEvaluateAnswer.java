package com.boxun.pcdp.capacity.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CA_ANSWER")
public class CEvaluateAnswer implements Serializable{

	private static final long serialVersionUID = 6178303284483189811L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true)
	private Long id;
	
	@ManyToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name = "ARRANGE_USER_ID")
	private CEvaluateArrangeUser arrangeUser;
	
	@ManyToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name = "QUESTION_ID")
	private CEvaluateQuestion question;
	
	@Column(name = "ANSWER", length=5000)
	private String answer;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CEvaluateArrangeUser getArrangeUser() {
		return arrangeUser;
	}

	public void setArrangeUser(CEvaluateArrangeUser arrangeUser) {
		this.arrangeUser = arrangeUser;
	}

	public CEvaluateQuestion getQuestion() {
		return question;
	}

	public void setQuestion(CEvaluateQuestion question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
}
