package com.boxun.pcdp.admin.entity;

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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "BX_MENU")
@JsonIgnoreProperties(value={"children", "module"})
public class TMenu implements Serializable{

	private static final long serialVersionUID = 202936184605715173L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true)
	private Long id;
	
	@Column(name = "CODE")
	private String code;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "URL")
	private String url;
	
	@Column(name = "ORDER_NO")
	private Integer order;
	
	@Column(name = "ICON")
	private String icon;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "LINKTYPE")
	private Const.LinkType linkType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID")
	private TMenu parent;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID")
	private List<TMenu> children;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MODULE_ID")
	private TModule module;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public TMenu getParent() {
		return parent;
	}

	public void setParent(TMenu parent) {
		this.parent = parent;
	}

	public List<TMenu> getChildren() {
		return children;
	}

	public void setChildren(List<TMenu> children) {
		this.children = children;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Const.LinkType getLinkType() {
		return linkType;
	}

	public void setLinkType(Const.LinkType linkType) {
		this.linkType = linkType;
	}

	public TModule getModule() {
		return module;
	}

	public void setModule(TModule module) {
		this.module = module;
	}
}
