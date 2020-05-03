package com.boxun.estms.service;

import java.util.List;

import com.boxun.pcdp.admin.entity.TMenuOperation;

public interface ICommonService {

	public TMenuOperation loadMenuOperation(Long id);
	
	public List<TMenuOperation> listMenuOperations();
}
