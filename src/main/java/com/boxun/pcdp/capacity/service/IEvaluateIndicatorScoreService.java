package com.boxun.pcdp.capacity.service;

import java.util.List;

import com.boxun.pcdp.capacity.entity.CEvaluateIndicatorScore;

public interface IEvaluateIndicatorScoreService {

	public void create(CEvaluateIndicatorScore entity);
	
	public void update(CEvaluateIndicatorScore entity);
	
	public void delete(CEvaluateIndicatorScore entity);
	
	public CEvaluateIndicatorScore load(Long id);
	
	public CEvaluateIndicatorScore loadByCategory(Long categoryId, Long indicatorId, Long userId);
	
	public List<CEvaluateIndicatorScore> list();
	
	public List<CEvaluateIndicatorScore> list4ArrangeUser(Long arrangeUserId);
}
