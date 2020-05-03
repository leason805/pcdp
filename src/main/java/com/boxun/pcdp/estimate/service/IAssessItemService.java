package com.boxun.pcdp.estimate.service;

import java.util.List;

import com.boxun.pcdp.admin.entity.TUser;
import com.boxun.pcdp.estimate.entity.EAssessItem;

public interface IAssessItemService {

	public void create(EAssessItem entity);
	
	public void update(EAssessItem entity);
	
	public void delete(EAssessItem entity);
	
	public EAssessItem load(Long id);
	
	public List<EAssessItem> list();
	
	public List<EAssessItem> list4User(Long userId, Long projectId);
	
	public List<EAssessItem> list4Assess(Long assessId);
	
	public Long getSize4Assess(Long assessId, Boolean assigned);
	
	public List<TUser> listUsers4Assess(Long assessId, Boolean assigned);
}
