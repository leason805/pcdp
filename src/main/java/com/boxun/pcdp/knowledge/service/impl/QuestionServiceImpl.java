package com.boxun.pcdp.knowledge.service.impl;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxun.pcdp.knowledge.dao.impl.QuestionDaoImpl;
import com.boxun.pcdp.knowledge.entity.KQuestion;
import com.boxun.pcdp.knowledge.service.IQuestionService;

@Service("questionService")
public class QuestionServiceImpl implements IQuestionService{

	@Autowired
	private QuestionDaoImpl questionDao;
	
	@Override
	public void create(KQuestion entity) {
		this.questionDao.save(entity);
	}

	@Override
	public void update(KQuestion entity) {
		this.questionDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(KQuestion entity) {
		this.questionDao.delete(entity);
	}

	@Override
	public KQuestion load(Long id) {
		return this.questionDao.load(id);
	}

	@Override
	public List<KQuestion> list() {
		return this.questionDao.loadAll();
	}

	@Override
	public List<KQuestion> listBySectionId(Long sectionId) {
		DetachedCriteria criteria = this.questionDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("section.id", sectionId));
		return this.questionDao.findByCriteria(criteria);
	}

	@Override
	public List<KQuestion> list(List<Long> ids) {
		DetachedCriteria criteria = this.questionDao.createDetachedCriteria();
		criteria.add(Restrictions.in("id", ids));
		return this.questionDao.findByCriteria(criteria);
	}

	@Override
	public void saveOrUpdate(Collection<KQuestion> entities) {
		this.questionDao.saveOrUpdate(entities);
	}

	@Override
	public Map<String, Long> listQuestSizeBySection() {
		Map<String, Long> map = new HashMap<String, Long>();
		String sql = "SELECT section_id SECTION, COUNT(*) SIZE FROM kn_question GROUP BY section_id";
		List<Map<String, Object>> result = this.questionDao.getQueryResultToListMap(sql);
		Map<String, Long> item = null;
		for(Map<String, Object> resultMap : result){
			map.put(resultMap.get("SECTION").toString(), ((BigInteger)resultMap.get("SIZE")).longValue());
		}
		return map;
	}

	@Override
	public void batchDelete(Long sectionId) {
		String sql = "delete from KQuestion where id not in (SELECT ques.id from KQuestion ques, KExamAnswer ans WHERE ans.question.id = ques.id) AND ques.section.id = " + sectionId ;
		this.questionDao.bulkUpdate(sql);		
	}

}
