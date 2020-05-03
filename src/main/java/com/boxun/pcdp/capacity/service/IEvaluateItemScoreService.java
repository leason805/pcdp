package com.boxun.pcdp.capacity.service;

import java.util.List;

import com.boxun.pcdp.capacity.entity.CEvaluateItemScore;

public interface IEvaluateItemScoreService {

	public void create(CEvaluateItemScore entity);
	
	public void update(CEvaluateItemScore entity);
	
	public void delete(CEvaluateItemScore entity);
	
	public CEvaluateItemScore load(Long id);
	
	public List<CEvaluateItemScore> list();
	
	public List<CEvaluateItemScore> list4ArrangeUser(Long arrangeUserId);
}
