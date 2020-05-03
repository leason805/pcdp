package com.boxun.pcdp.estimate.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxun.pcdp.estimate.dao.impl.AssessDetailDaoImpl;
import com.boxun.pcdp.estimate.entity.EAssessDetail;
import com.boxun.pcdp.estimate.service.IAssessDetailService;

@Service("assessdetailService")
public class AssessDetailServiceImpl implements IAssessDetailService{

	@Autowired
	private AssessDetailDaoImpl assessdetailDao;
	
	@Override
	public void create(EAssessDetail entity) {
		this.assessdetailDao.save(entity);
	}

	@Override
	public void update(EAssessDetail entity) {
		this.assessdetailDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(EAssessDetail entity) {
		this.assessdetailDao.delete(entity);
	}

	@Override
	public EAssessDetail load(Long id) {
		return this.assessdetailDao.load(id);
	}

	@Override
	public List<EAssessDetail> list() {
		return this.assessdetailDao.loadAll();
	}

	@Override
	public List<EAssessDetail> list(Long assessId) {
		DetachedCriteria criteria = this.assessdetailDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("assess.id", assessId));
		return this.assessdetailDao.findByCriteria(criteria);
	}

}
