package com.boxun.pcdp.performance.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxun.pcdp.performance.dao.impl.IndicatorScoreDaoImpl;
import com.boxun.pcdp.performance.entity.PIndicatorScore;
import com.boxun.pcdp.performance.service.IIndicatorScoreService;

@Service("pindicatorscoreService")
public class IndicatorScoreServiceImpl implements IIndicatorScoreService{

	@Autowired
	private IndicatorScoreDaoImpl pindicatorscoreDao;
	
	@Override
	public void create(PIndicatorScore entity) {
		this.pindicatorscoreDao.save(entity);
	}

	@Override
	public void update(PIndicatorScore entity) {
		this.pindicatorscoreDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(PIndicatorScore entity) {
		this.pindicatorscoreDao.delete(entity);
	}

	@Override
	public PIndicatorScore load(Long id) {
		return this.pindicatorscoreDao.load(id);
	}

	@Override
	public List<PIndicatorScore> list() {
		return this.pindicatorscoreDao.loadAll();
	}

	@Override
	public List<PIndicatorScore> list4ArrangeUser(Long arrangeId, Long userId) {
		DetachedCriteria criteria = this.pindicatorscoreDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("arrange.id", arrangeId));
		criteria.add(Restrictions.eq("user.id", userId));
		return this.pindicatorscoreDao.findByCriteria(criteria);
	}

	@Override
	public List<PIndicatorScore> list4Assess(Long assessId, Long indicatorId) {
		DetachedCriteria criteria = this.pindicatorscoreDao.createDetachedCriteria();
		DetachedCriteria subCriteria = criteria.createAlias("arrange", "arrange");
		subCriteria.add(Restrictions.eq("arrange.assess.id", assessId));
		criteria.add(Restrictions.eq("indicator.id", indicatorId));
		return this.pindicatorscoreDao.findByCriteria(criteria);
	}

}
