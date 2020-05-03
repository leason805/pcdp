package com.boxun.pcdp.capacity.dao.impl;

import java.util.Collection;

import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.boxun.pcdp.basic.dao.impl.GenericDaoImpl;
import com.boxun.pcdp.capacity.entity.CEvaluateProject;

@Repository("evaluateprojectDao")
public class EvaluateProjectDaoImpl extends GenericDaoImpl<CEvaluateProject, Long>{

	@Override
	public Object getStackValue(DetachedCriteria criteria, String propertyName,
			String stackName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(Collection<CEvaluateProject> entitys)
			throws HibernateException {
		// TODO Auto-generated method stub
		
	}

}
