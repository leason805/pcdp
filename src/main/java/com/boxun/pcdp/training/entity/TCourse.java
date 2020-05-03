package com.boxun.pcdp.training.entity;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.boxun.estms.entity.Const;
import com.boxun.pcdp.admin.entity.TUser;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "TR_COURSE")
@JsonIgnoreProperties(value={"arranges"})
public class TCourse implements Serializable{

	private static final long serialVersionUID = 6178303284483189811L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true)
	private Long id;

	@Column(name = "NAME")
	private String name;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
	private Const.CourseStatus status;
	
	@ManyToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATOR_ID")
	private TUser creator;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "COURSE_ID")
	private List<TArrange> arranges;
	
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

	public Const.CourseStatus getStatus() {
		return status;
	}

	public void setStatus(Const.CourseStatus status) {
		this.status = status;
	}

	public List<TArrange> getArranges() {
		return arranges;
	}

	public void setArranges(List<TArrange> arranges) {
		this.arranges = arranges;
	}

	public TUser getCreator() {
		return creator;
	}

	public void setCreator(TUser creator) {
		this.creator = creator;
	}

}
