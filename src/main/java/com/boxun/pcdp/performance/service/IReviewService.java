package com.boxun.pcdp.performance.service;

import java.util.List;

import com.boxun.pcdp.performance.entity.PReview;

public interface IReviewService {

	public void create(PReview entity);
	
	public void update(PReview entity);
	
	public void delete(PReview entity);
	
	public PReview load(Long id);
	
	public List<PReview> list();
}
