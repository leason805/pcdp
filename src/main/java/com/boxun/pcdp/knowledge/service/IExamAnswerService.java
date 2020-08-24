package com.boxun.pcdp.knowledge.service;

import java.util.List;

import com.boxun.pcdp.knowledge.entity.KExamAnswer;

public interface IExamAnswerService {

	public void create(KExamAnswer entity);
	
	public void update(KExamAnswer entity);
	
	public void delete(KExamAnswer entity);
	
	public KExamAnswer load(Long id);
	
	public List<KExamAnswer> list();
	
	public Long countByQuestionId(Long questionid);
}
