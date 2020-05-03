package com.boxun.pcdp.knowledge.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.boxun.pcdp.knowledge.entity.KQuestion;

public interface IQuestionService {

	public void create(KQuestion entity);
	
	public void update(KQuestion entity);
	
	public void saveOrUpdate(Collection<KQuestion> entities);
	
	public void delete(KQuestion entity);
	
	public KQuestion load(Long id);
	
	public List<KQuestion> list();
	
	public List<KQuestion> listBySectionId(Long sectionId);
	
	public List<KQuestion> list(List<Long> ids);
	
	public Map<String, Long> listQuestSizeBySection();
}
