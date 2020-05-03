package com.boxun.pcdp.capacity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxun.pcdp.capacity.dao.impl.EvaluateProjectDaoImpl;
import com.boxun.pcdp.capacity.entity.CEvaluateProject;
import com.boxun.pcdp.capacity.service.IEvaluateProjectService;

@Service("evaluateprojectService")
public class EvaluateProjectServiceImpl implements IEvaluateProjectService{

	@Autowired
	private EvaluateProjectDaoImpl evaluateprojectDao;
	
	@Override
	public void create(CEvaluateProject entity) {
		this.evaluateprojectDao.save(entity);
	}

	@Override
	public void update(CEvaluateProject entity) {
		this.evaluateprojectDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(CEvaluateProject entity) {
		this.evaluateprojectDao.delete(entity);
	}

	@Override
	public CEvaluateProject load(Long id) {
		return this.evaluateprojectDao.load(id);
	}

	@Override
	public List<CEvaluateProject> list() {
		return this.evaluateprojectDao.loadAll();
	}

}
