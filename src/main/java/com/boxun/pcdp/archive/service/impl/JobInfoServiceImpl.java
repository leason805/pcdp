package com.boxun.pcdp.archive.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxun.pcdp.archive.dao.impl.JobInfoDaoImpl;
import com.boxun.pcdp.archive.entity.AJobInfo;
import com.boxun.pcdp.archive.service.IJobInfoService;

@Service("jobinfoService")
public class JobInfoServiceImpl implements IJobInfoService{

	@Autowired
	private JobInfoDaoImpl jobinfoDao;
	
	@Override
	public void create(AJobInfo entity) {
		this.jobinfoDao.save(entity);
	}

	@Override
	public void update(AJobInfo entity) {
		this.jobinfoDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(AJobInfo entity) {
		this.jobinfoDao.delete(entity);
	}

	@Override
	public AJobInfo load(Long id) {
		return this.jobinfoDao.load(id);
	}

	@Override
	public List<AJobInfo> list() {
		return this.jobinfoDao.loadAll();
	}

}
