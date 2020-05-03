package com.boxun.pcdp.knowledge.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxun.pcdp.knowledge.dao.impl.PaperDaoImpl;
import com.boxun.pcdp.knowledge.entity.KPaper;
import com.boxun.pcdp.knowledge.service.IPaperService;

@Service("paperService")
public class PaperServiceImpl implements IPaperService{

	@Autowired
	private PaperDaoImpl paperDao;
	
	@Override
	public void create(KPaper entity) {
		this.paperDao.save(entity);
	}

	@Override
	public void update(KPaper entity) {
		this.paperDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(KPaper entity) {
		this.paperDao.delete(entity);
	}

	@Override
	public KPaper load(Long id) {
		return this.paperDao.load(id);
	}

	@Override
	public List<KPaper> list() {
		return this.paperDao.loadAll();
	}

}
