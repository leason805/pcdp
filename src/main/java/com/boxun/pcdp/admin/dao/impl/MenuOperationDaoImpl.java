package com.boxun.pcdp.admin.dao.impl;

import java.util.Collection;

import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.boxun.pcdp.admin.entity.TMenuOperation;
import com.boxun.pcdp.basic.dao.impl.GenericDaoImpl;

@Repository("menuOperaDao")
public class MenuOperationDaoImpl extends GenericDaoImpl<TMenuOperation, Long>{

	@Override
	public Object getStackValue(DetachedCriteria criteria, String propertyName,
			String stackName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(Collection<TMenuOperation> entitys)
			throws HibernateException {
		// TODO Auto-generated method stub
		
	}

}
