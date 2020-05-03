package com.boxun.pcdp.estimate.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxun.pcdp.estimate.dao.impl.IndicatorScoreDaoImpl;
import com.boxun.pcdp.estimate.entity.EIndicatorScoreItem;
import com.boxun.pcdp.estimate.service.IIndicatorScoreService;

@Service("indicatorScoreService")
public class IndicatorScoreServiceImpl implements IIndicatorScoreService{

	@Autowired
	private IndicatorScoreDaoImpl indicatorScoreDao;

	@Override
	public void create(EIndicatorScoreItem entity) {
		this.indicatorScoreDao.save(entity);
	}

	@Override
	public void update(EIndicatorScoreItem entity) {
		this.indicatorScoreDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(EIndicatorScoreItem entity) {
		this.indicatorScoreDao.delete(entity);
	}

	@Override
	public EIndicatorScoreItem load(Long id) {
		return this.indicatorScoreDao.load(id);
	}

	@Override
	public List<EIndicatorScoreItem> list() {
		return this.indicatorScoreDao.loadAll();
	}

	@Override
	public List<EIndicatorScoreItem> list(Long userId) {
		DetachedCriteria criteria = this.indicatorScoreDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("user.id", userId));
		return this.indicatorScoreDao.findByCriteria(criteria);
	}

	@Override
	public List<EIndicatorScoreItem> listByIndicators(List<Long> ids) {
		DetachedCriteria criteria = this.indicatorScoreDao.createDetachedCriteria();
		criteria.add(Restrictions.in("indicator.id", ids));
		criteria.addOrder(Order.asc("user.id"));
		return this.indicatorScoreDao.findByCriteria(criteria);
	}

	@Override
	public List<EIndicatorScoreItem> listOrderByUser() {
		DetachedCriteria criteria = this.indicatorScoreDao.createDetachedCriteria();
		criteria.addOrder(Order.asc("user.id"));
		return this.indicatorScoreDao.findByCriteria(criteria);
	}

	@Override
	public List<EIndicatorScoreItem> listOrderByUser(List<Long> indicatorIds) {
		DetachedCriteria criteria = this.indicatorScoreDao.createDetachedCriteria();
		criteria.add(Restrictions.in("indicator.id", indicatorIds));
		criteria.addOrder(Order.asc("user.id"));
		return this.indicatorScoreDao.findByCriteria(criteria);
	}
	
}
