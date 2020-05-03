package com.boxun.pcdp.capacity.service;

import java.util.List;

import com.boxun.pcdp.capacity.entity.CEvaluateArrange;

public interface IEvaluateArrangeService {

	public void create(CEvaluateArrange entity);
	
	public void update(CEvaluateArrange entity);
	
	public void delete(CEvaluateArrange entity);
	
	public CEvaluateArrange load(Long id);
	
	public List<CEvaluateArrange> list();
	
	public List<CEvaluateArrange> list4User(Long userId);
}
