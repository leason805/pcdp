package com.boxun.pcdp.admin.service;

import java.util.List;

import com.boxun.pcdp.admin.entity.TCompany;

public interface ICompanyService {

	public void create(TCompany entity);
	
	public void update(TCompany entity);
	
	public void delete(TCompany entity);
	
	public TCompany load(Long id);
	
	public List<TCompany> list();
}
