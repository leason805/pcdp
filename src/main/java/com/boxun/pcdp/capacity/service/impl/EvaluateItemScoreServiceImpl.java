package com.boxun.pcdp.capacity.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxun.pcdp.capacity.dao.impl.EvaluateItemScoreDaoImpl;
import com.boxun.pcdp.capacity.entity.CEvaluateItemScore;
import com.boxun.pcdp.capacity.service.IEvaluateItemScoreService;

@Service("evaluateitemscoreService")
public class EvaluateItemScoreServiceImpl implements IEvaluateItemScoreService{

	@Autowired
	private EvaluateItemScoreDaoImpl evaluateitemscoreDao;
	
	@Override
	public void create(CEvaluateItemScore entity) {
		this.evaluateitemscoreDao.save(entity);
	}

	@Override
	public void update(CEvaluateItemScore entity) {
		this.evaluateitemscoreDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(CEvaluateItemScore entity) {
		this.evaluateitemscoreDao.delete(entity);
	}

	@Override
	public CEvaluateItemScore load(Long id) {
		return this.evaluateitemscoreDao.load(id);
	}

	@Override
	public List<CEvaluateItemScore> list() {
		return this.evaluateitemscoreDao.loadAll();
	}

	@Override
	public List<CEvaluateItemScore> list4ArrangeUser(Long arrangeUserId) {
		DetachedCriteria criteria = this.evaluateitemscoreDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("arrangeUser.id", arrangeUserId));
		return this.evaluateitemscoreDao.findByCriteria(criteria);
	}

}
