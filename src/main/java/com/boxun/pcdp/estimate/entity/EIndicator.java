package com.boxun.pcdp.estimate.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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

@Entity
@Table(name = "ES_INDICATOR")
public class EIndicator implements Serializable{

	private static final long serialVersionUID = 6178303284483189811L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true)
	private Long id;

	@Column(name = "NUMBER")
	private Integer number;
	
	@Column(name = "SEQ")
	private String sequence;
	
	@Column(name = "PSEQ")
	private String psequence;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "LEVEL")
	private Integer level;
	
	@Column(name = "SCORE")
	private Integer score;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "WEIGHT", precision=12, scale=3)
	private BigDecimal weight;
	
	@Column(name = "ADJUST_WEIGHT", precision=12, scale=3)
	private BigDecimal adjustWeight;
	
	@Column(name = "ALARM_SCORE", precision=12, scale=2)
	private Double alarmScore;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "MANDATORY")
	private Const.Mandatory mandatory;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
	private Const.IndicatorType indicatorType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY_ID")
	private EIndicatorCategory category;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID")
	private EIndicator parent;
	
	@OneToMany(fetch = FetchType.LAZY, cascade=(CascadeType.ALL))
	@JoinColumn(name = "PARENT_ID")
	@OrderBy("psequence asc, number asc")
	private List<EIndicator> children;
	
	@OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy = "indicator")
	private EIndicatorAssociate associate;
	
	@OneToMany(fetch = FetchType.LAZY, cascade={ CascadeType.ALL })
	@JoinColumn(name = "INDICATOR_ID")
	private List<EIndicatorScoreItem> scores;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public String getPsequence() {
		return psequence;
	}

	public void setPsequence(String psequence) {
		this.psequence = psequence;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Const.Mandatory getMandatory() {
		return mandatory;
	}

	public void setMandatory(Const.Mandatory mandatory) {
		this.mandatory = mandatory;
	}

	public EIndicator getParent() {
		return parent;
	}

	public void setParent(EIndicator parent) {
		this.parent = parent;
	}

	public List<EIndicator> getChildren() {
		return children;
	}

	public void setChildren(List<EIndicator> children) {
		this.children = children;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public BigDecimal getAdjustWeight() {
		return adjustWeight;
	}

	public void setAdjustWeight(BigDecimal adjustWeight) {
		this.adjustWeight = adjustWeight;
	}

	public Double getAlarmScore() {
		return alarmScore;
	}

	public void setAlarmScore(Double alarmScore) {
		this.alarmScore = alarmScore;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public EIndicatorAssociate getAssociate() {
		return associate;
	}

	public void setAssociate(EIndicatorAssociate associate) {
		this.associate = associate;
	}

	public List<EIndicatorScoreItem> getScores() {
		return scores;
	}

	public void setScores(List<EIndicatorScoreItem> scores) {
		this.scores = scores;
	}

	public EIndicatorCategory getCategory() {
		return category;
	}

	public void setCategory(EIndicatorCategory category) {
		this.category = category;
	}

	public Const.IndicatorType getIndicatorType() {
		return indicatorType;
	}

	public void setIndicatorType(Const.IndicatorType indicatorType) {
		this.indicatorType = indicatorType;
	}
}
