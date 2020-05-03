package com.boxun.pcdp.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxun.pcdp.admin.dao.impl.ModuleDaoImpl;
import com.boxun.pcdp.admin.entity.TModule;
import com.boxun.pcdp.admin.service.IModuleService;

@Service("moduleService")
public class ModuleServiceImpl implements IModuleService{

	@Autowired
	private ModuleDaoImpl moduleDao;

	@Override
	public void create(TModule module) {
		this.moduleDao.save(module);
	}

	@Override
	public void update(TModule module) {
		this.moduleDao.saveOrUpdate(module);
	}

	@Override
	public void delete(TModule module) {
		this.moduleDao.delete(module);
	}

	@Override
	public TModule load(Long id) {
		return this.moduleDao.load(id);
	}

	@Override
	public List<TModule> list() {
		return this.moduleDao.find("from TModule order by order asc");
	}
}
