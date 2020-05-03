package com.boxun.pcdp.capacity.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxun.pcdp.capacity.dao.impl.EvaluateIndicatorScoreDaoImpl;
import com.boxun.pcdp.capacity.entity.CEvaluateIndicatorScore;
import com.boxun.pcdp.capacity.service.IEvaluateIndicatorScoreService;

@Service("evaluateindicatorscoreService")
public class EvaluateIndicatorScoreServiceImpl implements IEvaluateIndicatorScoreService{

	@Autowired
	private EvaluateIndicatorScoreDaoImpl evaluateindicatorscoreDao;
	
	@Override
	public void create(CEvaluateIndicatorScore entity) {
		this.evaluateindicatorscoreDao.save(entity);
	}

	@Override
	public void update(CEvaluateIndicatorScore entity) {
		this.evaluateindicatorscoreDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(CEvaluateIndicatorScore entity) {
		this.evaluateindicatorscoreDao.delete(entity);
	}

	@Override
	public CEvaluateIndicatorScore load(Long id) {
		return this.evaluateindicatorscoreDao.load(id);
	}

	@Override
	public List<CEvaluateIndicatorScore> list() {
		return this.evaluateindicatorscoreDao.loadAll();
	}

	@Override
	public List<CEvaluateIndicatorScore> list4ArrangeUser(Long arrangeUserId) {
		DetachedCriteria criteria = this.evaluateindicatorscoreDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("arrangeUser.id", arrangeUserId));
		return this.evaluateindicatorscoreDao.findByCriteria(criteria);
	}

	@Override
	public CEvaluateIndicatorScore loadByCategory(Long categoryId, Long indicatorId, Long userId) {
		DetachedCriteria criteria = this.evaluateindicatorscoreDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("indicator.id", indicatorId));
		DetachedCriteria subCriteria = criteria.createAlias("arrangeUser", "arrangeUser");
		subCriteria.add(Restrictions.eq("arrangeUser.user.id", userId));
		
		DetachedCriteria subCriteria_1 = subCriteria.createAlias("arrangeUser.arrange", "arrange").createAlias("arrange.project", "project").createAlias("project.category", "category");
		
		subCriteria_1.add(Restrictions.eq("category.id", categoryId));
	    List<CEvaluateIndicatorScore> list = this.evaluateindicatorscoreDao.findByCriteria(criteria);
	    if(list != null && !list.isEmpty()){
	    	return list.get(0);
	    }
	    return null;
	}

}
