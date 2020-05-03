package com.boxun.pcdp.knowledge.service;

import java.util.List;
import java.util.Map;

import com.boxun.pcdp.knowledge.entity.KExamScore;

public interface IExamScoreService {

	public void create(KExamScore entity);
	
	public void update(KExamScore entity);
	
	public void delete(KExamScore entity);
	
	public KExamScore load(Long id);
	
	public KExamScore loadByArrangeUser(Long arrangeUserId);
	
	public List<KExamScore> list();
	
	public List<Map<String, Object>> listScoreByPaper(Long paperId);
	
	public List<Map<String, Object>> listScoreByArrange(Long arrangeId);
	
	public List<Map<String, Object>> listScoreByArrangeUser(Long arrangeUserId);
}
