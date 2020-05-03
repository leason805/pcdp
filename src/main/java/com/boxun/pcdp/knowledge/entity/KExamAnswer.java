package com.boxun.pcdp.knowledge.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.boxun.estms.entity.Const;
import com.boxun.pcdp.admin.entity.TUser;

@Entity
@Table(name = "KN_EXAM_ANSWER")
public class KExamAnswer implements Serializable{

	private static final long serialVersionUID = 6178303284483189811L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true)
	private Long id;

	@Column(name = "ORDER_NO")
	private Integer order;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "CORRECT_TYPE")
	private Const.CorrectType correctType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SCORE_ID")
	private KExamScore score;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "QUESTION_ID")
	private KQuestion question;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "KN_EXAM_ANSWER_OPTION", joinColumns = @JoinColumn(name = "ANSWER_ID"), inverseJoinColumns = @JoinColumn(name = "OPTION_ID"))
	private List<KOption> options;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private TUser user;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Const.CorrectType getCorrectType() {
		return correctType;
	}

	public void setCorrectType(Const.CorrectType correctType) {
		this.correctType = correctType;
	}

	public KExamScore getScore() {
		return score;
	}

	public void setScore(KExamScore score) {
		this.score = score;
	}

	public KQuestion getQuestion() {
		return question;
	}

	public void setQuestion(KQuestion question) {
		this.question = question;
	}

	public List<KOption> getOptions() {
		return options;
	}

	public void setOptions(List<KOption> options) {
		this.options = options;
	}

	public TUser getUser() {
		return user;
	}

	public void setUser(TUser user) {
		this.user = user;
	}
}
