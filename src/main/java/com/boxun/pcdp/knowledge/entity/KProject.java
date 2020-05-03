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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.boxun.estms.entity.Const;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "KN_PROJECT")
@JsonIgnoreProperties(value={"sections"})
public class KProject implements Serializable{

	private static final long serialVersionUID = 6178303284483189811L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true)
	private Long id;
	
	@Column(name = "TITLE")
	private String title;
	
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
	
	@Enumerated(EnumType.STRING)
	@Column(name = "PROJECT_TYPE")
	private Const.ProjectType projectType;
	
	@Column(name = "SCORE")
	private Integer score;
	
	@Column(name = "PASSS_CORE")
	private Integer passscore;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJECT_ID")
	private List<KSection> sections;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getPassscore() {
		return passscore;
	}

	public void setPassscore(Integer passscore) {
		this.passscore = passscore;
	}

	public Const.ProjectType getProjectType() {
		return projectType;
	}

	public void setProjectType(Const.ProjectType projectType) {
		this.projectType = projectType;
	}

	public List<KSection> getSections() {
		return sections;
	}

	public void setSections(List<KSection> sections) {
		this.sections = sections;
	}
}
