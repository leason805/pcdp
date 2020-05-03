package com.boxun.pcdp.knowledge.service;

import java.util.List;

import com.boxun.pcdp.knowledge.entity.KArrange;

public interface IArrangeService {

	public void create(KArrange entity);
	
	public void update(KArrange entity);
	
	public void delete(KArrange entity);
	
	public KArrange load(Long id);
	
	public List<KArrange> list();
	
	public void sendMail(List<String> tolist, List<String> cclist, String title, String content);
}
