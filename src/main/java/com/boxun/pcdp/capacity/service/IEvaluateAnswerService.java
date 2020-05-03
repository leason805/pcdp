package com.boxun.pcdp.capacity.service;

import java.util.List;

import com.boxun.pcdp.capacity.entity.CEvaluateAnswer;

public interface IEvaluateAnswerService {

	public void create(CEvaluateAnswer entity);
	
	public void update(CEvaluateAnswer entity);
	
	public void delete(CEvaluateAnswer entity);
	
	public CEvaluateAnswer load(Long id);
	
	public List<CEvaluateAnswer> list();
	
	public List<CEvaluateAnswer> list(Long arrangeUserId);
}
