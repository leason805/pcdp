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
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.boxun.estms.entity.Const;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "KN_EXAM_SCORE")
@JsonIgnoreProperties(value={"arrangeUser", "paper", "answers"})
public class KExamScore implements Serializable{

	private static final long serialVersionUID = 6178303284483189811L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true)
	private Long id;

	@Column(name = "SCORE")
	private Double score;
	
	@Column(name = "CREATE_TIME")
	private Date createTime;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "SCORE_TYPE")
	private Const.ScoreType scoreType;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "PASS_TYPE")
	private Const.PassType passType; 
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "USER_ID")
//	private TUser user;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ARRANGE_USER_ID")
	private KArrangeUser arrangeUser;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PAPER_ID")
	private KPaper paper;
	
	@OneToMany(fetch = FetchType.LAZY, cascade={ CascadeType.ALL })
	@JoinColumn(name = "SCORE_ID")
	@OrderBy(value = "order ASC")
	private List<KExamAnswer> answers;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Const.ScoreType getScoreType() {
		return scoreType;
	}

	public void setScoreType(Const.ScoreType scoreType) {
		this.scoreType = scoreType;
	}

	public Const.PassType getPassType() {
		return passType;
	}

	public void setPassType(Const.PassType passType) {
		this.passType = passType;
	}

//	public TUser getUser() {
//		return user;
//	}
//
//	public void setUser(TUser user) {
//		this.user = user;
//	}

	public List<KExamAnswer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<KExamAnswer> answers) {
		this.answers = answers;
	}

	public KArrangeUser getArrangeUser() {
		return arrangeUser;
	}

	public void setArrangeUser(KArrangeUser arrangeUser) {
		this.arrangeUser = arrangeUser;
	}

	public KPaper getPaper() {
		return paper;
	}

	public void setPaper(KPaper paper) {
		this.paper = paper;
	}
}
