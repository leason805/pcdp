package com.boxun.pcdp.admin.dao.impl;

import java.util.Collection;

import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.boxun.pcdp.basic.dao.impl.GenericDaoImpl;
import com.boxun.pcdp.admin.entity.TCompany;

@Repository("companyDao")
public class CompanyDaoImpl extends GenericDaoImpl<TCompany, Long>{

	@Override
	public Object getStackValue(DetachedCriteria criteria, String propertyName,
			String stackName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(Collection<TCompany> entitys)
			throws HibernateException {
		// TODO Auto-generated method stub
		
	}

}
