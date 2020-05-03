package com.boxun.estms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxun.estms.service.ICommonService;
import com.boxun.pcdp.admin.dao.impl.MenuOperationDaoImpl;
import com.boxun.pcdp.admin.entity.TMenuOperation;

@Service("commonService")
public class CommonServiceImpl implements ICommonService{

	@Autowired
	private MenuOperationDaoImpl menuOperaDao;
	
	@Override
	public List<TMenuOperation> listMenuOperations() {
		return menuOperaDao.loadAll();
	}

	@Override
	public TMenuOperation loadMenuOperation(Long id) {
		return this.menuOperaDao.load(id);
	}

	

}
