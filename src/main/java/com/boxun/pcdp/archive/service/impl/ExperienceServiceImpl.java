package com.boxun.pcdp.archive.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxun.pcdp.archive.dao.impl.ExperienceDaoImpl;
import com.boxun.pcdp.archive.entity.AExperience;
import com.boxun.pcdp.archive.service.IExperienceService;

@Service("experienceService")
public class ExperienceServiceImpl implements IExperienceService{

	@Autowired
	private ExperienceDaoImpl experienceDao;
	
	@Override
	public void create(AExperience entity) {
		this.experienceDao.save(entity);
	}

	@Override
	public void update(AExperience entity) {
		this.experienceDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(AExperience entity) {
		this.experienceDao.delete(entity);
	}

	@Override
	public AExperience load(Long id) {
		return this.experienceDao.load(id);
	}

	@Override
	public List<AExperience> list() {
		return this.experienceDao.loadAll();
	}

	@Override
	public List<AExperience> list(Long userId) {
		DetachedCriteria criteria = this.experienceDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("user.id", userId));
		return this.experienceDao.findByCriteria(criteria);
	}

	@Override
	public List<AExperience> list(Long userId, Long categoryId) {
		DetachedCriteria criteria = this.experienceDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("user.id", userId));
		criteria.add(Restrictions.eq("category.id", categoryId));
		return this.experienceDao.findByCriteria(criteria);
	}

}
