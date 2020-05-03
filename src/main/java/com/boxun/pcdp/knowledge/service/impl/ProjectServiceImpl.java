package com.boxun.pcdp.knowledge.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxun.pcdp.knowledge.dao.impl.ProjectDaoImpl;
import com.boxun.pcdp.knowledge.entity.KProject;
import com.boxun.pcdp.knowledge.service.IProjectService;

@Service("projectService")
public class ProjectServiceImpl implements IProjectService{

	@Autowired
	private ProjectDaoImpl projectDao;
	
	@Override
	public void create(KProject entity) {
		this.projectDao.save(entity);
	}

	@Override
	public void update(KProject entity) {
		this.projectDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(KProject entity) {
		this.projectDao.delete(entity);
	}

	@Override
	public KProject load(Long id) {
		return this.projectDao.load(id);
	}

	@Override
	public List<KProject> list() {
		return this.projectDao.loadAll();
	}

}
