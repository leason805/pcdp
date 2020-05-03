package com.boxun.pcdp.performance.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxun.pcdp.performance.dao.impl.ArrangeDaoImpl;
import com.boxun.pcdp.performance.entity.PArrange;
import com.boxun.pcdp.performance.service.IArrangeService;

@Service("parrangeService")
public class ArrangeServiceImpl implements IArrangeService{

	@Autowired
	private ArrangeDaoImpl parrangeDao;
	
	@Override
	public void create(PArrange entity) {
		this.parrangeDao.save(entity);
	}

	@Override
	public void update(PArrange entity) {
		this.parrangeDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(PArrange entity) {
		this.parrangeDao.delete(entity);
	}

	@Override
	public PArrange load(Long id) {
		return this.parrangeDao.load(id);
	}

	@Override
	public List<PArrange> list() {
		return this.parrangeDao.loadAll();
	}

	@Override
	public List<PArrange> list4Project(Long projectId) {
		DetachedCriteria criteria = this.parrangeDao.createDetachedCriteria();
		DetachedCriteria assessCriteria = criteria.createAlias("assess", "assess");
		assessCriteria.add(Restrictions.eq("assess.project.id", projectId));
		return this.parrangeDao.findByCriteria(criteria);
	}

	@Override
	public PArrange loadByAssess(Long assessId) {
		DetachedCriteria criteria = this.parrangeDao.createDetachedCriteria();
		//DetachedCriteria assessCriteria = criteria.createAlias("assess", "assess");
		criteria.add(Restrictions.eq("assess.id", assessId));
		List<PArrange> list = this.parrangeDao.findByCriteria(criteria);
		if(list != null && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<PArrange> list4User(Long projectId, Long userId, boolean isSupper) {
		DetachedCriteria criteria = this.parrangeDao.createDetachedCriteria();
		DetachedCriteria assessCriteria = criteria.createAlias("assess", "assess");
		assessCriteria.add(Restrictions.eq("assess.project.id", projectId));
		if(isSupper){
			criteria.add(Restrictions.eq("supAssessor.id", userId));
		}
		else{
			criteria.add(Restrictions.eq("colAssessor.id", userId));
		}
		return this.parrangeDao.findByCriteria(criteria);
	}

	
}
