package com.boxun.pcdp.archive.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxun.pcdp.archive.dao.impl.EducationInfoDaoImpl;
import com.boxun.pcdp.archive.entity.AEducationInfo;
import com.boxun.pcdp.archive.service.IEducationInfoService;

@Service("educationinfoService")
public class EducationInfoServiceImpl implements IEducationInfoService{

	@Autowired
	private EducationInfoDaoImpl educationinfoDao;
	
	@Override
	public void create(AEducationInfo entity) {
		this.educationinfoDao.save(entity);
	}

	@Override
	public void update(AEducationInfo entity) {
		this.educationinfoDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(AEducationInfo entity) {
		this.educationinfoDao.delete(entity);
	}

	@Override
	public AEducationInfo load(Long id) {
		return this.educationinfoDao.load(id);
	}

	@Override
	public List<AEducationInfo> list() {
		return this.educationinfoDao.loadAll();
	}

}
