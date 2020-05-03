package com.boxun.pcdp.performance.service;

import java.util.List;

import com.boxun.pcdp.performance.entity.PIndicatorScore;

public interface IIndicatorScoreService {

	public void create(PIndicatorScore entity);
	
	public void update(PIndicatorScore entity);
	
	public void delete(PIndicatorScore entity);
	
	public PIndicatorScore load(Long id);
	
	public List<PIndicatorScore> list();
	
	public List<PIndicatorScore> list4ArrangeUser(Long arrangeId, Long userId);
	
	public List<PIndicatorScore> list4Assess(Long assessId, Long indicatorId);
}
