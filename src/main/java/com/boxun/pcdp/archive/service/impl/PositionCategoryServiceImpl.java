package com.boxun.pcdp.archive.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxun.estms.entity.Const.PositionCategoryType;
import com.boxun.pcdp.archive.dao.impl.PositionCategoryDaoImpl;
import com.boxun.pcdp.archive.entity.APositionCategory;
import com.boxun.pcdp.archive.service.IPositionCategoryService;

@Service("positioncategoryService")
public class PositionCategoryServiceImpl implements IPositionCategoryService{

	@Autowired
	private PositionCategoryDaoImpl positioncategoryDao;
	
	@Override
	public void create(APositionCategory entity) {
		this.positioncategoryDao.save(entity);
	}

	@Override
	public void update(APositionCategory entity) {
		this.positioncategoryDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(APositionCategory entity) {
		this.positioncategoryDao.delete(entity);
	}

	@Override
	public APositionCategory load(Long id) {
		return this.positioncategoryDao.load(id);
	}

	@Override
	public List<APositionCategory> list() {
		return this.positioncategoryDao.loadAll();
	}

	@Override
	public List<APositionCategory> listByType(PositionCategoryType type) {
		DetachedCriteria criteria = this.positioncategoryDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("categoryType", type));
		return this.positioncategoryDao.findByCriteria(criteria);
	}

}
