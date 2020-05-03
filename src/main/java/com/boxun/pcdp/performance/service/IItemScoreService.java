package com.boxun.pcdp.performance.service;

import java.util.List;

import com.boxun.pcdp.performance.entity.PItemScore;

public interface IItemScoreService {

	public void create(PItemScore entity);
	
	public void update(PItemScore entity);
	
	public void delete(PItemScore entity);
	
	public PItemScore load(Long id);
	
	public List<PItemScore> list();
	
	public List<PItemScore> list4ArrangeUser(Long arrangeId, Long userId);
}
