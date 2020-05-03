package com.boxun.pcdp.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxun.pcdp.admin.dao.impl.MenuDaoImpl;
import com.boxun.pcdp.admin.entity.TMenu;
import com.boxun.pcdp.admin.service.IMenuService;

@Service("menuService")
public class MenuServiceImpl implements IMenuService{

	@Autowired
	private MenuDaoImpl menuDao;

	@Override
	public void create(TMenu menu) {
		this.menuDao.save(menu);
	}

	@Override
	public void update(TMenu menu) {
		this.menuDao.saveOrUpdate(menu);
	}

	@Override
	public void delete(TMenu menu) {
		this.menuDao.delete(menu);
	}

	@Override
	public TMenu load(Long id) {
		return this.menuDao.load(id);
	}

	@Override
	public List<TMenu> list() {
		return this.menuDao.loadAll();
	}

	@Override
	public List<TMenu> listTops() {
		return this.menuDao.find("from TMenu where parent is null order by order asc");
	}
}
