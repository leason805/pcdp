package com.boxun.pcdp.estimate.service.impl;

import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxun.pcdp.estimate.dao.impl.IndicatorImportanceItemDaoImpl;
import com.boxun.pcdp.estimate.entity.EIndicatorImportanceItem;
import com.boxun.pcdp.estimate.service.IIndicatorImportanceItemService;

@Service("indicatorimportanceitemService")
public class IndicatorImportanceItemServiceImpl implements IIndicatorImportanceItemService{

	@Autowired
	private IndicatorImportanceItemDaoImpl indicatorimportanceitemDao;
	
	@Override
	public void create(EIndicatorImportanceItem entity) {
		this.indicatorimportanceitemDao.save(entity);
	}

	@Override
	public void update(EIndicatorImportanceItem entity) {
		this.indicatorimportanceitemDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(EIndicatorImportanceItem entity) {
		this.indicatorimportanceitemDao.delete(entity);
	}

	@Override
	public EIndicatorImportanceItem load(Long id) {
		return this.indicatorimportanceitemDao.load(id);
	}

	@Override
	public List<EIndicatorImportanceItem> list() {
		return this.indicatorimportanceitemDao.loadAll();
	}

	@Override
	public List<EIndicatorImportanceItem> list(Long userId) {
		DetachedCriteria criteria = this.indicatorimportanceitemDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("user.id", userId));
		return this.indicatorimportanceitemDao.findByCriteria(criteria);
	}

	@Override
	public void delete(Long userId, Long categoryId) {
		this.indicatorimportanceitemDao.bulkUpdate("delete from EIndicatorImportanceItem where user.id = " + userId + " and importance.id in (select id from  EIndicatorImportance where category.id =" + categoryId + ")");
	}

	@Override
	public void create(Collection<EIndicatorImportanceItem> entities) {
		this.indicatorimportanceitemDao.saveOrUpdate(entities);
	}

	@Override
	public List<EIndicatorImportanceItem> list4Category(Long categoryId) {
		DetachedCriteria criteria = this.indicatorimportanceitemDao.createDetachedCriteria();
		criteria.createAlias("importance", "impo"); 
		criteria.add(Restrictions.eq("impo.category.id", categoryId));
		return this.indicatorimportanceitemDao.findByCriteria(criteria);
	}

}
