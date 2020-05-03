package com.boxun.pcdp.capacity.service;

import java.util.List;

import com.boxun.pcdp.capacity.entity.CEvaluateProject;

public interface IEvaluateProjectService {

	public void create(CEvaluateProject entity);
	
	public void update(CEvaluateProject entity);
	
	public void delete(CEvaluateProject entity);
	
	public CEvaluateProject load(Long id);
	
	public List<CEvaluateProject> list();
}
