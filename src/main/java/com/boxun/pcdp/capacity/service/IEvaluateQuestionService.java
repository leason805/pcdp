package com.boxun.pcdp.capacity.service;

import java.util.List;

import com.boxun.pcdp.capacity.entity.CEvaluateQuestion;

public interface IEvaluateQuestionService {

	public void create(CEvaluateQuestion entity);
	
	public void update(CEvaluateQuestion entity);
	
	public void delete(CEvaluateQuestion entity);
	
	public CEvaluateQuestion load(Long id);
	
	public List<CEvaluateQuestion> list();
	
	public List<CEvaluateQuestion> list(Long projectId);
}
