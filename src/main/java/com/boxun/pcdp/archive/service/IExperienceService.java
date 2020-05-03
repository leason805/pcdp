package com.boxun.pcdp.archive.service;

import java.util.List;

import com.boxun.pcdp.archive.entity.AExperience;

public interface IExperienceService {

	public void create(AExperience entity);
	
	public void update(AExperience entity);
	
	public void delete(AExperience entity);
	
	public AExperience load(Long id);
	
	public List<AExperience> list();
	
	public List<AExperience> list(Long userId);
	
	public List<AExperience> list(Long userId, Long categoryId);
}
