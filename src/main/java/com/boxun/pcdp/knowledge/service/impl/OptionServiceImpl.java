package com.boxun.pcdp.knowledge.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxun.pcdp.knowledge.dao.impl.OptionDaoImpl;
import com.boxun.pcdp.knowledge.entity.KOption;
import com.boxun.pcdp.knowledge.service.IOptionService;

@Service("optionService")
public class OptionServiceImpl implements IOptionService{

	@Autowired
	private OptionDaoImpl optionDao;
	
	@Override
	public void create(KOption entity) {
		this.optionDao.save(entity);
	}

	@Override
	public void update(KOption entity) {
		this.optionDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(KOption entity) {
		this.optionDao.delete(entity);
	}

	@Override
	public KOption load(Long id) {
		return this.optionDao.load(id);
	}

	@Override
	public List<KOption> list() {
		return this.optionDao.loadAll();
	}

	@Override
	public void batchDelete(Long sectionId) {
		String sql = "delete from KOption where id in (select a.id from (select op.id from KOption op,  KQuestion ques WHERE op.question.id = ques.id AND ques.section.id = " + sectionId 
				+ " and (op.id not in (SELECT op.id FROM KOption op,  KQuestion ques, KExamAnswer ans WHERE ans.question.id = ques.id AND op.question.id = ques.id AND ques.section.id = " + sectionId + "))) AS a)";
		this.optionDao.bulkUpdate(sql);
	}

}
