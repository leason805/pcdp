package com.boxun.pcdp.archive.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxun.pcdp.archive.dao.impl.CertCategoryDaoImpl;
import com.boxun.pcdp.archive.entity.ACertCategory;
import com.boxun.pcdp.archive.service.ICertCategoryService;

@Service("certcategoryService")
public class CertCategoryServiceImpl implements ICertCategoryService{

	@Autowired
	private CertCategoryDaoImpl certcategoryDao;
	
	@Override
	public void create(ACertCategory entity) {
		this.certcategoryDao.save(entity);
	}

	@Override
	public void update(ACertCategory entity) {
		this.certcategoryDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(ACertCategory entity) {
		this.certcategoryDao.delete(entity);
	}

	@Override
	public ACertCategory load(Long id) {
		return this.certcategoryDao.load(id);
	}

	@Override
	public List<ACertCategory> list() {
		return this.certcategoryDao.loadAll();
	}

	@Override
	public List<ACertCategory> listTops() {
		return this.certcategoryDao.find("from ACertCategory where parent is null order by order asc");
	}

}
