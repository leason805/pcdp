package com.boxun.pcdp.performance.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxun.pcdp.performance.dao.impl.ItemScoreDaoImpl;
import com.boxun.pcdp.performance.entity.PItemScore;
import com.boxun.pcdp.performance.service.IItemScoreService;

@Service("itemscoreService")
public class ItemScoreServiceImpl implements IItemScoreService{

	@Autowired
	private ItemScoreDaoImpl itemscoreDao;
	
	@Override
	public void create(PItemScore entity) {
		this.itemscoreDao.save(entity);
	}

	@Override
	public void update(PItemScore entity) {
		this.itemscoreDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(PItemScore entity) {
		this.itemscoreDao.delete(entity);
	}

	@Override
	public PItemScore load(Long id) {
		return this.itemscoreDao.load(id);
	}

	@Override
	public List<PItemScore> list() {
		return this.itemscoreDao.loadAll();
	}

	@Override
	public List<PItemScore> list4ArrangeUser(Long arrangeId, Long userId) {
		DetachedCriteria criteria = this.itemscoreDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("arrange.id", arrangeId));
		criteria.add(Restrictions.eq("user.id", userId));
		return this.itemscoreDao.findByCriteria(criteria);
	}

}
