package com.boxun.pcdp.admin.dao.impl;

import java.util.Collection;

import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.boxun.pcdp.admin.entity.TUser;
import com.boxun.pcdp.basic.dao.impl.GenericDaoImpl;

@Repository("userDao")
public class UserDaoImpl extends GenericDaoImpl<TUser, Long>{

	@Override
	public Object getStackValue(DetachedCriteria criteria, String propertyName,
			String stackName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(Collection<TUser> entitys) throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	

}
