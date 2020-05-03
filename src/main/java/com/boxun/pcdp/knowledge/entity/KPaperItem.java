package com.boxun.pcdp.knowledge.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "KN_PAPER_ITEM")
public class KPaperItem implements Serializable{

	private static final long serialVersionUID = 6178303284483189811L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true)
	private Long id;

	@Column(name = "SIZE")
	private Integer size;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SECTION_ID")
	private KSection section;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PAPER_ID")
	private KPaper paper;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public KPaper getPaper() {
		return paper;
	}

	public void setPaper(KPaper paper) {
		this.paper = paper;
	}

	public KSection getSection() {
		return section;
	}

	public void setSection(KSection section) {
		this.section = section;
	}
}
