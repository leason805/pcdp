package com.boxun.pcdp.knowledge.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.boxun.estms.entity.Const;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "KN_QUESTION")
@JsonIgnoreProperties(value={"options", "section"})
public class KQuestion implements Serializable{

	private static final long serialVersionUID = 6178303284483189811L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true)
	private Long id;
	
	@Column(name = "QUESTION")
	private String question;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "QUESTION_TYPE")
	private Const.QuestionType questionType;
	
	@Column(name = "CREATE_TIME")
	private Date createTime;
	
	@ManyToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name = "SECTION_ID")
	private KSection section;
	
	@OneToMany(fetch = FetchType.LAZY, cascade={ CascadeType.ALL })
	@JoinColumn(name = "QUESTION_ID")
	@OrderBy(value = "code ASC")
	private List<KOption> options;

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public KSection getSection() {
		return section;
	}

	public void setSection(KSection section) {
		this.section = section;
	}

	public List<KOption> getOptions() {
		return options;
	}

	public void setOptions(List<KOption> options) {
		this.options = options;
	}
}
