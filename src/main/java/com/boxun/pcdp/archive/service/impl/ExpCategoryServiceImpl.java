package com.boxun.pcdp.archive.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxun.pcdp.archive.dao.impl.ExpCategoryDaoImpl;
import com.boxun.pcdp.archive.entity.AExpCategory;
import com.boxun.pcdp.archive.service.IExpCategoryService;

@Service("expcategoryService")
public class ExpCategoryServiceImpl implements IExpCategoryService{

	@Autowired
	private ExpCategoryDaoImpl expcategoryDao;
	
	@Override
	public void create(AExpCategory entity) {
		this.expcategoryDao.save(entity);
	}

	@Override
	public void update(AExpCategory entity) {
		this.expcategoryDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(AExpCategory entity) {
		this.expcategoryDao.delete(entity);
	}

	@Override
	public AExpCategory load(Long id) {
		return this.expcategoryDao.load(id);
	}

	@Override
	public List<AExpCategory> list() {
		return this.expcategoryDao.loadAll();
	}

	@Override
	public List<AExpCategory> listTops() {
		return this.expcategoryDao.find("from AExpCategory where parent is null order by order asc");
	}

}
