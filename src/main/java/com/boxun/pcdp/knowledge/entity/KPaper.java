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
import javax.persistence.Table;

import com.boxun.pcdp.admin.entity.TCompany;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "KN_PAPER")
@JsonIgnoreProperties(value={"items","section"})
public class KPaper implements Serializable{

	private static final long serialVersionUID = 6178303284483189811L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true)
	private Long id;

	@Column(name = "NAME")
	private String name;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "MINUTES")
	private Integer minutes;
	
	@Column(name = "SIZE")
	private Integer size;
	
	@Column(name = "LIMITS")
	private Integer limits;
	
	@Column(name = "STATUS")
	private Integer status;
	
	@Column(name = "SCORE")
	private Integer score;
	
	@Column(name = "PASSS_CORE")
	private Double passscore;
	
	@ManyToOne//(fetch = FetchType.LAZY) 
	@JoinColumn(name = "COMPANY_ID")
	private TCompany company;
	
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name = "SECTION_ID")
	private KSection section;	//the top parent section
	
	@OneToMany(fetch = FetchType.LAZY, cascade={ CascadeType.ALL })
	@JoinColumn(name = "PAPER_ID")
	private List<KPaperItem> items;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getMinutes() {
		return minutes;
	}

	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getLimits() {
		return limits;
	}

	public void setLimits(Integer limits) {
		this.limits = limits;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Double getPassscore() {
		return passscore;
	}

	public void setPassscore(Double passscore) {
		this.passscore = passscore;
	}

	public List<KPaperItem> getItems() {
		return items;
	}

	public void setItems(List<KPaperItem> items) {
		this.items = items;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public TCompany getCompany() {
		return company;
	}

	public void setCompany(TCompany company) {
		this.company = company;
	}

	public KSection getSection() {
		return section;
	}

	public void setSection(KSection section) {
		this.section = section;
	}
}
