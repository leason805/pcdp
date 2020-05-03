package com.boxun.pcdp.estimate.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxun.pcdp.estimate.dao.impl.IndicatorItemDaoImpl;
import com.boxun.pcdp.estimate.entity.EIndicatorItem;
import com.boxun.pcdp.estimate.service.IIndicatorItemService;

@Service("indicatoritemService")
public class IndicatorItemServiceImpl implements IIndicatorItemService{

	@Autowired
	private IndicatorItemDaoImpl indicatoritemDao;
	
	@Override
	public void create(EIndicatorItem entity) {
		this.indicatoritemDao.save(entity);
	}

	@Override
	public void update(EIndicatorItem entity) {
		this.indicatoritemDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(EIndicatorItem entity) {
		this.indicatoritemDao.delete(entity);
	}

	@Override
	public EIndicatorItem load(Long id) {
		return this.indicatoritemDao.load(id);
	}

	@Override
	public List<EIndicatorItem> list() {
		return this.indicatoritemDao.loadAll();
	}

	@Override
	public List<EIndicatorItem> list4Indicator(Long indicatorId) {
		DetachedCriteria criteria = this.indicatoritemDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("indicator.id", indicatorId));
		criteria.addOrder(Order.asc("sequence"));
		return this.indicatoritemDao.findByCriteria(criteria);
	}

}
