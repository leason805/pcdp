package com.boxun.pcdp.knowledge.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxun.pcdp.knowledge.dao.impl.ExamAnswerDaoImpl;
import com.boxun.pcdp.knowledge.entity.KExamAnswer;
import com.boxun.pcdp.knowledge.service.IExamAnswerService;

@Service("examanswerService")
public class ExamAnswerServiceImpl implements IExamAnswerService{

	@Autowired
	private ExamAnswerDaoImpl examanswerDao;
	
	@Override
	public void create(KExamAnswer entity) {
		this.examanswerDao.save(entity);
	}

	@Override
	public void update(KExamAnswer entity) {
		this.examanswerDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(KExamAnswer entity) {
		this.examanswerDao.delete(entity);
	}

	@Override
	public KExamAnswer load(Long id) {
		return this.examanswerDao.load(id);
	}

	@Override
	public List<KExamAnswer> list() {
		return this.examanswerDao.loadAll();
	}

}
