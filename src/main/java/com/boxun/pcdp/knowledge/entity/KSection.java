package com.boxun.pcdp.knowledge.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.boxun.pcdp.admin.entity.TCompany;
import com.boxun.pcdp.estimate.entity.EIndicator;

@Entity
@Table(name = "KN_SECTION")
public class KSection implements Serializable{

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
	
	@ManyToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name = "COMPANY_ID")
	private TCompany company;
	 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID")
	private KSection parent;
	
	@OneToMany(fetch = FetchType.LAZY, cascade=(CascadeType.ALL))
	@JoinColumn(name = "PARENT_ID")
	@OrderBy("psequence asc, number asc")
	private List<KSection> children;
	 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TCompany getCompany() {
		return company;
	}

	public void setCompany(TCompany company) {
		this.company = company;
	}

	public List<KSection> getChildren() {
		return children;
	}

	public void setChildren(List<KSection> children) {
		this.children = children;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
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

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public KSection getParent() {
		return parent;
	}

	public void setParent(KSection parent) {
		this.parent = parent;
	}
}
