package com.boxun.pcdp.archive.dao.impl;

import java.util.Collection;

import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.boxun.pcdp.basic.dao.impl.GenericDaoImpl;
import com.boxun.pcdp.archive.entity.AEducationInfo;

@Repository("educationinfoDao")
public class EducationInfoDaoImpl extends GenericDaoImpl<AEducationInfo, Long>{

	@Override
	public Object getStackValue(DetachedCriteria criteria, String propertyName,
			String stackName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(Collection<AEducationInfo> entitys)
			throws HibernateException {
		// TODO Auto-generated method stub
		
	}

}
