package com.boxun.pcdp.estimate.service;

import java.util.List;

import com.boxun.pcdp.admin.entity.TUser;
import com.boxun.pcdp.estimate.entity.EAssessProject;

public interface IAssessProjectService {

	public void create(EAssessProject entity);
	
	public void update(EAssessProject entity);
	
	public void delete(EAssessProject entity);
	
	public EAssessProject load(Long id);
	
	public List<EAssessProject> list();
	
	public Long getSize4Assess(Long assessId, Boolean assigned);
	
	public List<TUser> listUsers4Assess(Long assessId, Boolean assigned);
}
