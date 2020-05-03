package com.boxun.pcdp.capacity.service;

import java.util.List;

import com.boxun.pcdp.capacity.entity.CEvaluateReview;

public interface IEvaluateReviewService {

	public void create(CEvaluateReview entity);
	
	public void update(CEvaluateReview entity);
	
	public void delete(CEvaluateReview entity);
	
	public CEvaluateReview load(Long id);
	
	public List<CEvaluateReview> list();
}
