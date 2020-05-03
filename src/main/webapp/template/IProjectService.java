package com.boxun.pcdp.knowledge.service;

import java.util.List;

import com.boxun.pcdp.knowledge.entity.KProject;

public interface IProjectService {

	public void create(KProject entity);
	
	public void update(KProject entity);
	
	public void delete(KProject entity);
	
	public KProject load(Long id);
	
	public List<KProject> list();
}
