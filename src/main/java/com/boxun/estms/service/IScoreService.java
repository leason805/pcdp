package com.boxun.estms.service;

import java.util.List;

import com.boxun.estms.entity.EScore;

public interface IScoreService {

	public void create(EScore score);
	
	public void update(EScore score);
	
	public void delete(EScore score);
	
	public EScore load(Long id);
	
	public List<EScore> list();

}
