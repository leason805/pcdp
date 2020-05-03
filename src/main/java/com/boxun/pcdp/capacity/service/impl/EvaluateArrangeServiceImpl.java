package com.boxun.pcdp.capacity.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxun.pcdp.capacity.dao.impl.EvaluateArrangeDaoImpl;
import com.boxun.pcdp.capacity.entity.CEvaluateArrange;
import com.boxun.pcdp.capacity.service.IEvaluateArrangeService;

@Service("evaluatearrangeService")
public class EvaluateArrangeServiceImpl implements IEvaluateArrangeService{

	@Autowired
	private EvaluateArrangeDaoImpl evaluatearrangeDao;
	
	@Override
	public void create(CEvaluateArrange entity) {
		this.evaluatearrangeDao.save(entity);
	}

	@Override
	public void update(CEvaluateArrange entity) {
		this.evaluatearrangeDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(CEvaluateArrange entity) {
		this.evaluatearrangeDao.delete(entity);
	}

	@Override
	public CEvaluateArrange load(Long id) {
		return this.evaluatearrangeDao.load(id);
	}

	@Override
	public List<CEvaluateArrange> list() {
		return this.evaluatearrangeDao.loadAll();
	}

	@Override
	public List<CEvaluateArrange> list4User(Long userId) {
		DetachedCriteria criteria = this.evaluatearrangeDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("adjudicator.id", userId));
		return this.evaluatearrangeDao.findByCriteria(criteria);
	}

}
