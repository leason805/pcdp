package com.boxun.pcdp.performance.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxun.pcdp.performance.dao.impl.ReviewDaoImpl;
import com.boxun.pcdp.performance.entity.PReview;
import com.boxun.pcdp.performance.service.IReviewService;

@Service("reviewService")
public class ReviewServiceImpl implements IReviewService{

	@Autowired
	private ReviewDaoImpl reviewDao;
	
	@Override
	public void create(PReview entity) {
		this.reviewDao.save(entity);
	}

	@Override
	public void update(PReview entity) {
		this.reviewDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(PReview entity) {
		this.reviewDao.delete(entity);
	}

	@Override
	public PReview load(Long id) {
		return this.reviewDao.load(id);
	}

	@Override
	public List<PReview> list() {
		return this.reviewDao.loadAll();
	}

}
