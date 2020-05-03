package com.boxun.estms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxun.estms.dao.impl.IndexDaoImpl;
import com.boxun.estms.entity.EIndex;
import com.boxun.estms.service.IIndexService;

@Service("indexService")
public class IndexServiceImpl implements IIndexService{

	@Autowired
	private IndexDaoImpl indexDao;
	
	@Override
	public void create(EIndex index) {
		this.indexDao.save(index);
	}

	@Override
	public void update(EIndex index) {
		this.indexDao.saveOrUpdate(index);
	}

	@Override
	public void delete(EIndex index) {
		this.indexDao.delete(index);
	}

	@Override
	public EIndex load(Long id) {
		return this.indexDao.load(id);
	}

	@Override
	public List<EIndex> list() {
		return this.indexDao.loadAll();
	}
	
	@Override
	public List<EIndex> listTops(){
		return this.indexDao.find("from EIndex where parent is null order by sequence asc");
	}

}
