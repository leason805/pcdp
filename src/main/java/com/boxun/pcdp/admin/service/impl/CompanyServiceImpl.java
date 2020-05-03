package com.boxun.pcdp.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxun.pcdp.admin.dao.impl.CompanyDaoImpl;
import com.boxun.pcdp.admin.entity.TCompany;
import com.boxun.pcdp.admin.service.ICompanyService;

@Service("companyService")
public class CompanyServiceImpl implements ICompanyService{

	@Autowired
	private CompanyDaoImpl companyDao;
	
	@Override
	public void create(TCompany entity) {
		this.companyDao.save(entity);
	}

	@Override
	public void update(TCompany entity) {
		this.companyDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(TCompany entity) {
		this.companyDao.delete(entity);
	}

	@Override
	public TCompany load(Long id) {
		return this.companyDao.load(id);
	}

	@Override
	public List<TCompany> list() {
		return this.companyDao.loadAll();
	}

}
