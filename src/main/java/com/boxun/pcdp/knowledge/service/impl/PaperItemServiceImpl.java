package com.boxun.pcdp.knowledge.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxun.pcdp.knowledge.dao.impl.PaperItemDaoImpl;
import com.boxun.pcdp.knowledge.entity.KPaperItem;
import com.boxun.pcdp.knowledge.service.IPaperItemService;

@Service("paperitemService")
public class PaperItemServiceImpl implements IPaperItemService{

	@Autowired
	private PaperItemDaoImpl paperitemDao;
	
	@Override
	public void create(KPaperItem entity) {
		this.paperitemDao.save(entity);
	}

	@Override
	public void update(KPaperItem entity) {
		this.paperitemDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(KPaperItem entity) {
		this.paperitemDao.delete(entity);
	}

	@Override
	public KPaperItem load(Long id) {
		return this.paperitemDao.load(id);
	}

	@Override
	public List<KPaperItem> list() {
		return this.paperitemDao.loadAll();
	}

}
