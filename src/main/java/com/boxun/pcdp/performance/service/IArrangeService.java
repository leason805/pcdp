package com.boxun.pcdp.performance.service;

import java.util.List;

import com.boxun.pcdp.performance.entity.PArrange;

public interface IArrangeService {

	public void create(PArrange entity);
	
	public void update(PArrange entity);
	
	public void delete(PArrange entity);
	
	public PArrange load(Long id);
	
	public PArrange loadByAssess(Long assessId);
	
	public List<PArrange> list();
	
	public List<PArrange> list4Project(Long projectId);
	
	public List<PArrange> list4User(Long projectId, Long userId, boolean isSupper);
}
