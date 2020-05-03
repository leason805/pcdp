package com.boxun.pcdp.admin.dao.impl;

import java.util.Collection;

import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.boxun.pcdp.admin.entity.TParams;
import com.boxun.pcdp.basic.dao.impl.GenericDaoImpl;

@Repository("paramsDao")
public class ParamsDaoImpl extends GenericDaoImpl<TParams, Long>{

	@Override
	public Object getStackValue(DetachedCriteria criteria, String propertyName,
			String stackName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(Collection<TParams> entitys)
			throws HibernateException {
		// TODO Auto-generated method stub
		
	}

}
