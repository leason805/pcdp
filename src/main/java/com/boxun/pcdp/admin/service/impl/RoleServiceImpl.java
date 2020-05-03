package com.boxun.pcdp.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxun.pcdp.admin.dao.impl.RoleDaoImpl;
import com.boxun.pcdp.admin.entity.TRole;
import com.boxun.pcdp.admin.service.IRoleService;

@Service("roleService")
public class RoleServiceImpl implements IRoleService{

	@Autowired
	private RoleDaoImpl roleDao;
	
	@Override
	public void create(TRole role) {
		this.roleDao.save(role);
	}

	@Override
	public void update(TRole role) {
		this.roleDao.saveOrUpdate(role);
		
	}

	@Override
	public void delete(TRole role) {
		this.roleDao.delete(role);
		
	}

	@Override
	public TRole load(Long id) {
		return this.roleDao.load(id);
		
	}

	@Override
	public List<TRole> list() {
		return this.roleDao.loadAll();
		
	}

}
