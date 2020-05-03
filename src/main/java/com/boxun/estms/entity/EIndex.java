package com.boxun.estms.entity;

import java.io.Serializable;
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

@Entity
@Table(name = "ES_INDEX")
public class EIndex implements Serializable{

	private static final long serialVersionUID = 5832084831245024677L;

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
	
	@Enumerated(EnumType.STRING)
    @Column(name = "MANDATORY")
	private Const.Mandatory mandatory;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID")
	private EIndex parent;
	
	@OneToMany(fetch = FetchType.LAZY, cascade=(CascadeType.ALL))
	@JoinColumn(name = "PARENT_ID")
	@OrderBy("psequence asc, number asc")
	private List<EIndex> children;

	@OneToMany(mappedBy="index", fetch = FetchType.LAZY, cascade=(CascadeType.ALL))
	private List<EScore> scores;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EIndex getParent() {
		return parent;
	}

	public void setParent(EIndex parent) {
		this.parent = parent;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public List<EIndex> getChildren() {
		return children;
	}

	public void setChildren(List<EIndex> children) {
		this.children = children;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPsequence() {
		return psequence;
	}

	public void setPsequence(String psequence) {
		this.psequence = psequence;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Const.Mandatory getMandatory() {
		return mandatory;
	}

	public void setMandatory(Const.Mandatory mandatory) {
		this.mandatory = mandatory;
	}

	public List<EScore> getScores() {
		return scores;
	}

	public void setScores(List<EScore> scores) {
		this.scores = scores;
	}
}
