package com.boxun.pcdp.capacity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxun.pcdp.capacity.dao.impl.EvaluateReviewDaoImpl;
import com.boxun.pcdp.capacity.entity.CEvaluateReview;
import com.boxun.pcdp.capacity.service.IEvaluateReviewService;

@Service("evaluatereviewService")
public class EvaluateReviewServiceImpl implements IEvaluateReviewService{

	@Autowired
	private EvaluateReviewDaoImpl evaluatereviewDao;
	
	@Override
	public void create(CEvaluateReview entity) {
		this.evaluatereviewDao.save(entity);
	}

	@Override
	public void update(CEvaluateReview entity) {
		this.evaluatereviewDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(CEvaluateReview entity) {
		this.evaluatereviewDao.delete(entity);
	}

	@Override
	public CEvaluateReview load(Long id) {
		return this.evaluatereviewDao.load(id);
	}

	@Override
	public List<CEvaluateReview> list() {
		return this.evaluatereviewDao.loadAll();
	}

}
