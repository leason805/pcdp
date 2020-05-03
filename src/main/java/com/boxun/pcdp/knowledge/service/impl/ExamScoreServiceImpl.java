package com.boxun.pcdp.knowledge.service.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxun.pcdp.knowledge.dao.impl.ExamScoreDaoImpl;
import com.boxun.pcdp.knowledge.entity.KExamScore;
import com.boxun.pcdp.knowledge.service.IExamScoreService;

@Service("examscoreService")
public class ExamScoreServiceImpl implements IExamScoreService{

	@Autowired
	private ExamScoreDaoImpl examscoreDao;
	
	@Override
	public void create(KExamScore entity) {
		this.examscoreDao.save(entity);
	}

	@Override
	public void update(KExamScore entity) {
		this.examscoreDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(KExamScore entity) {
		this.examscoreDao.delete(entity);
	}

	@Override
	public KExamScore load(Long id) {
		return this.examscoreDao.load(id);
	}

	@Override
	public List<KExamScore> list() {
		return this.examscoreDao.loadAll();
	}

	@Override
	public KExamScore loadByArrangeUser(Long arrangeUserId) {
		DetachedCriteria criteria = this.examscoreDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("arrangeUser.id", arrangeUserId));
		List<KExamScore> list =this.examscoreDao.findByCriteria(criteria);
		if(list != null && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	/**
	 * 根据arrangeId,统计出该次考试所有人，每个章节的正确数量
	 */
	@Override
	public List<Map<String, Object>> listScoreByPaper(Long paperId) {
		//Map<String, Integer> map = new HashMap<String, Integer>();
		//String sql = "SELECT sect.ID SECTION, COUNT(*) SIZE  FROM kn_exam_score score, kn_paper paper, kn_exam_answer answer, kn_question ques, kn_section sect WHERE score.PAPER_ID = paper.ID AND answer.SCORE_ID = score.ID AND ques.ID = answer.QUESTION_ID AND sect.ID = ques.SECTION_ID AND paper.ID = " + paperId + " AND answer.CORRECT_TYPE = '" + Const.CorrectType.YES.toString() + "' GROUP BY sect.ID";
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT sect.ID SECTION, COUNT(*) SIZE, answer.CORRECT_TYPE CORRECT  FROM kn_exam_score score, kn_paper paper,kn_arrange arr, kn_exam_answer answer, kn_question ques, kn_section sect ")
		.append( "WHERE score.PAPER_ID = paper.ID ")
		.append(" AND arr.PAPER_ID = paper.ID ")
		.append(" AND answer.SCORE_ID = score.ID ")
		.append(" AND ques.ID = answer.QUESTION_ID ")
		.append(" AND sect.ID = ques.SECTION_ID ")
		//.append(" AND answer.CORRECT_TYPE = '").append(Const.CorrectType.YES.toString()).append("' ")
		.append(" AND paper.ID = ").append(paperId)
		.append(" GROUP BY sect.ID, answer.CORRECT_TYPE ");

		List<Map<String, Object>> result = this.examscoreDao.getQueryResultToListMap(sql.toString());
		return result;
	}

	@Override
	public List<Map<String, Object>> listScoreByArrange(Long arrangeId) {
		//Map<String, Integer> map = new HashMap<String, Integer>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT sect.ID SECTION, COUNT(*) SIZE, answer.CORRECT_TYPE CORRECT  FROM kn_exam_score score, kn_arrange_user arru, kn_arrange arr, kn_exam_answer answer, kn_question ques, kn_section sect ")
				.append( "WHERE arr.ID = arru.ARRANGE_ID ")
				.append(" AND score.ARRANGE_USER_ID = arru.ID ")
				.append(" AND answer.SCORE_ID = score.ID ")
				.append(" AND answer.QUESTION_ID = ques.ID ")
				.append(" AND ques.SECTION_ID = sect.ID ")
				//.append(" AND answer.CORRECT_TYPE = '").append(Const.CorrectType.YES.toString()).append("' ")
				.append(" AND arr.ID = ").append(arrangeId)
				.append(" GROUP BY sect.ID, answer.CORRECT_TYPE ");
		List<Map<String, Object>> result = this.examscoreDao.getQueryResultToListMap(sql.toString());
//		for(Map<String, Object> resultMap : result){
//			map.put(resultMap.get("SECTION").toString(), ((BigInteger)resultMap.get("SIZE")).intValue());
//		}
		return result;
	}

	@Override
	public List<Map<String, Object>> listScoreByArrangeUser(Long arrangeUserId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT sect.ID SECTION, COUNT(*) SIZE, answer.CORRECT_TYPE CORRECT  FROM kn_exam_score score, kn_arrange_user arru, kn_arrange arr, kn_exam_answer answer, kn_question ques, kn_section sect ")
				.append( "WHERE arr.ID = arru.ARRANGE_ID ")
				.append(" AND score.ARRANGE_USER_ID = arru.ID ")
				.append(" AND answer.SCORE_ID = score.ID ")
				.append(" AND answer.QUESTION_ID = ques.ID ")
				.append(" AND ques.SECTION_ID = sect.ID ")
				.append(" AND arru.ID = ").append(arrangeUserId)
				.append(" GROUP BY sect.ID, answer.CORRECT_TYPE ");
		List<Map<String, Object>> result = this.examscoreDao.getQueryResultToListMap(sql.toString());
		return result;
	}

}
