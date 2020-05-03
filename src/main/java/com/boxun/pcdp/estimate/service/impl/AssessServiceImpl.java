package com.boxun.pcdp.estimate.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxun.estms.entity.Const;
import com.boxun.estms.util.DateUtil;
import com.boxun.estms.util.SortUtil;
import com.boxun.estms.util.StringUtil;
import com.boxun.pcdp.archive.entity.ACertification;
import com.boxun.pcdp.archive.entity.AExperience;
import com.boxun.pcdp.archive.entity.AJobInfo;
import com.boxun.pcdp.archive.entity.APositionCategory;
import com.boxun.pcdp.archive.entity.AUserInfo;
import com.boxun.pcdp.archive.service.ICertificationService;
import com.boxun.pcdp.archive.service.IExperienceService;
import com.boxun.pcdp.archive.service.IUserInfoService;
import com.boxun.pcdp.capacity.entity.CEvaluateIndicatorScore;
import com.boxun.pcdp.capacity.service.IEvaluateIndicatorScoreService;
import com.boxun.pcdp.estimate.dao.impl.AssessDaoImpl;
import com.boxun.pcdp.estimate.dao.impl.IndicatorDaoImpl;
import com.boxun.pcdp.estimate.entity.EAssess;
import com.boxun.pcdp.estimate.entity.EAssessDetail;
import com.boxun.pcdp.estimate.entity.EAssociateItem;
import com.boxun.pcdp.estimate.entity.EIndicator;
import com.boxun.pcdp.estimate.pojo.AssessPojo;
import com.boxun.pcdp.estimate.service.IAssessService;
import com.boxun.pcdp.knowledge.service.ISectionService;
import com.boxun.pcdp.performance.entity.PArrange;
import com.boxun.pcdp.performance.entity.PIndicatorScore;
import com.boxun.pcdp.performance.service.IArrangeService;
import com.boxun.pcdp.performance.service.IIndicatorScoreService;
import com.boxun.pcdp.training.entity.TArrangeUser;
import com.boxun.pcdp.training.service.IArrangeUserService;

@Service("assessService")
public class AssessServiceImpl implements IAssessService{

	@Autowired
	private AssessDaoImpl assessDao;
	
	@Autowired
	private IndicatorDaoImpl indicatorDao;
	
	@Autowired
	private ICertificationService certificationService;
	
	@Autowired
	private IExperienceService experienceService;
	
	@Autowired
	private IUserInfoService userinfoService;
	
	@Autowired
	private IArrangeUserService tarrangeuserService;
	
	@Autowired
	private ISectionService sectionService;
	
	@Autowired
	private IEvaluateIndicatorScoreService evaluateindicatorscoreService;
	
	@Autowired
	private IArrangeService parrangeService;
	
	@Autowired
	private IIndicatorScoreService pindicatorscoreService;
	
	@Override
	public void create(EAssess entity) {
		this.assessDao.save(entity);
	}

	@Override
	public void update(EAssess entity) {
		this.assessDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(EAssess entity) {
		this.assessDao.delete(entity);
	}

	@Override
	public EAssess load(Long id) {
		return this.assessDao.load(id);
	}

	@Override
	public List<EAssess> list() {
		return this.assessDao.loadAll();
	}

	@Override
	public List<EAssess> list(Long projectId) {
		DetachedCriteria criteria = this.assessDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("project.id", projectId));
		criteria.add(Restrictions.isNotNull("user.id"));
		return this.assessDao.findByCriteria(criteria);
	}

	@Override
	public List<AssessPojo> assessList(Long userId, Long categoryId, Long assessId) {
		//List<EIndicator> tops = this.indicatorDao.find("from EIndicator where parent is null order by sequence asc");
		List<EIndicator> tops = this.indicatorDao.find("from EIndicator where parent is null and category.id = " +  categoryId + " order by sequence asc");
		List<AssessPojo> list = assessList(userId, categoryId, assessId, tops);
		
		return list;
	}

	private List<AssessPojo> assessList(Long userId, Long categoryId, Long assessId, List<EIndicator> indicators){
		List<AssessPojo> list = new ArrayList<AssessPojo>();
		AssessPojo pojo = null;
		for(EIndicator in : indicators){
			pojo = calculate(categoryId, assessId, userId, in);
			list.add(pojo);
			children(pojo, categoryId, assessId, userId, in.getChildren());
		}
		return list;
	}
	
	private void children(AssessPojo assess, Long categoryId, Long assessId, Long userId, List<EIndicator> indicators){
		AssessPojo pojo = null;
		if(indicators != null && !indicators.isEmpty()){
			for(EIndicator in : indicators){
				pojo = calculate(categoryId, assessId, userId, in);
				pojo.setParent(assess);
				assess.addChild(pojo);
				children(pojo, categoryId, assessId, userId, in.getChildren());
			}
		}
	}
	
	private AssessPojo calculate(Long categoryId, Long assessId, Long userId, EIndicator indicator){
		AssessPojo pojo = null;
		pojo = new AssessPojo();
		pojo.setId(indicator.getId());
		pojo.setMandatory(indicator.getMandatory());
		pojo.setName(indicator.getName());
		pojo.setSequence(indicator.getSequence());
		pojo.setDescription(indicator.getDescription());
		if(indicator.getAssociate() != null){
			if(indicator.getAssociate().getItems() != null && !indicator.getAssociate().getItems().isEmpty()){
				if(Const.AssociateType.CERTIFICATION.equals(indicator.getAssociate().getAssociateType())){
					List<Long> ids = new ArrayList<Long>();
					for(EAssociateItem item : indicator.getAssociate().getItems()){
						ids.add(item.getItemId());
					}
					int totalSize = ids.size();
					List<ACertification> certList = this.certificationService.list(userId, ids);
					int passSize = 0;
					for(ACertification cert : certList){
						if(Const.CertificationStatus.PASS.equals(cert.getStatus())){
							passSize++;
						}
					}
					if(passSize < totalSize){
						pojo.setScore(0d);
						pojo.setSummary("资格认证已通过 " + passSize + "/" + totalSize + ", 尚有 " + (totalSize-passSize) + "/" + totalSize + "未认证通过。");
					}
					else{
						pojo.setScore(1d);
						pojo.setSummary("全部资格认证已通过 " + passSize + "/" + totalSize);
					}
					pojo.setAuto(true);
				}
				else if(Const.AssociateType.EXPERIENCE.equals(indicator.getAssociate().getAssociateType())){
					Long category = indicator.getAssociate().getItems().get(0).getItemId();
					List<AExperience> list = this.experienceService.list(userId, category);
					Double months = 0d;
					for(AExperience exp : list){
						if(Const.CertificationStatus.PASS.equals(exp.getStatus())){
							months += DateUtil.timeDiffInMonth(exp.getStartDate(), exp.getEndDate());
						}
					}
					
					if(months > 0){
						setScore(pojo, months, indicator.getAssociate().getItems(), "相关经历为", "月");
					}
					else{
						//pojo.setScore(1);
						pojo.setSummary("<span style='color:red'>未检测到通过审核的相关经历。请进行验证。</span>");
					}
				}
				else if(Const.AssociateType.KNOWLEDGE.equals(indicator.getAssociate().getAssociateType())){
					Long section = indicator.getAssociate().getItems().get(0).getItemId();
					
					List<Long> sectionIds = this.sectionService.listChildIds(section, true);
					
					StringBuffer sections = new StringBuffer("(");
					for(int i=0; i<sectionIds.size(); i++){
						sections.append(sectionIds.get(i));
						if(i < sectionIds.size()-1){
							sections.append(",");
						}
						if(i == sectionIds.size()-1){
							sections.append(")");
						}
					}
					
					//String sql = "SELECT 'ALL' NAME, COUNT(an.id) SIZE FROM kn_exam_answer an, kn_question que WHERE an.question_id = que.ID AND que.SECTION_ID = " + section +" AND an.USER_ID = " + userId +
					//		" UNION " +
					//		"SELECT an.CORRECT_TYPE NAME, COUNT(an.id) SIZE FROM kn_exam_answer an, kn_question que WHERE an.question_id = que.ID AND que.SECTION_ID = " + section + " AND an.USER_ID = " + userId + " GROUP BY an.CORRECT_TYPE";
					String sql = "SELECT 'ALL' NAME, COUNT(an.id) SIZE FROM kn_exam_answer an, kn_question que, kn_exam_score score, kn_arrange_user au, kn_arrange arr WHERE arr.ID = au.ARRANGE_ID AND au.ID = score.ARRANGE_USER_ID AND an.question_id = que.ID AND que.SECTION_ID in " + sections + " AND au.USER_ID = " + userId + " AND arr.EXAM_DATE >= '" + DateUtil.formatDateTime(indicator.getAssociate().getStartDate(), com.boxun.estms.util.Const.DATE_FORMAT_2) + "' AND arr.EXAM_DATE <= '" + DateUtil.formatDateTime(indicator.getAssociate().getEndDate(), com.boxun.estms.util.Const.DATE_FORMAT_2) + "'"
								+ " UNION " + 
								 "SELECT an.CORRECT_TYPE NAME, COUNT(an.id) SIZE FROM kn_exam_answer an, kn_question que, kn_exam_score score, kn_arrange_user au, kn_arrange arr WHERE arr.ID = au.ARRANGE_ID AND au.ID = score.ARRANGE_USER_ID AND an.question_id = que.ID AND que.SECTION_ID in " + sections + " AND au.USER_ID = " + userId + " AND arr.EXAM_DATE >= '" + DateUtil.formatDateTime(indicator.getAssociate().getStartDate(), com.boxun.estms.util.Const.DATE_FORMAT_2) + "' AND arr.EXAM_DATE <= '" + DateUtil.formatDateTime(indicator.getAssociate().getEndDate(), com.boxun.estms.util.Const.DATE_FORMAT_2) + "' GROUP BY an.CORRECT_TYPE";
					List<Map<String, Object>> result = this.assessDao.getQueryResultToListMap(sql);
					Double allSize = 0d;
					Double yesSize = 0d;
					Double noSize = 0d;
					
					for(Map<String, Object> resultMap : result){
						if("ALL".equals(resultMap.get("NAME"))){
							allSize = ((BigInteger)resultMap.get("SIZE")).doubleValue();
						}
						if("YES".equals(resultMap.get("NAME"))){
							yesSize = ((BigInteger)resultMap.get("SIZE")).doubleValue();
						}
						if("NO".equals(resultMap.get("NAME"))){
							noSize = ((BigInteger)resultMap.get("SIZE")).doubleValue();
						}
					}
					
					if(allSize > 0){
						BigDecimal b = new BigDecimal(yesSize/allSize * 100);  
						Double rate = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();  
						
						setScore(pojo, rate, indicator.getAssociate().getItems(), "知识点通过率", "%");
						pojo.setAuto(true);
					}
					else{
						pojo.setSummary("<span style='color:red'>未检测到该知识点考核成绩。请进行验证。</span>");
					}
				}
				else if(Const.AssociateType.TRAINING.equals(indicator.getAssociate().getAssociateType())){
					List<TArrangeUser> arrangeUsers = this.tarrangeuserService.list4User(userId, indicator.getAssociate().getStartDate(), indicator.getAssociate().getEndDate());
					Double allSize = 0d;
					Double yesSize = 0d;
					Double noSize = 0d;
					for(TArrangeUser arrange : arrangeUsers){
						allSize++;
						if(Const.ArrangeUserStatus.COMPLETED.equals(arrange.getStatus()) || Const.ArrangeUserStatus.SIGNED.equals(arrange.getStatus())){
							yesSize++;
						}
					}
					if(allSize > 0){
						BigDecimal b = new BigDecimal(yesSize/allSize * 100);  
						Double rate = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();  
						
						setScore(pojo, rate, indicator.getAssociate().getItems(), "培训签到率", "%");
						pojo.setAuto(true);
					}
					else{
						pojo.setSummary("<span style='color:red'>未检测到有相关培训记录。请进行验证。</span>");
					}
				}
				else if(Const.AssociateType.QUALIFICATION.equals(indicator.getAssociate().getAssociateType())){
					AUserInfo userInfo = this.userinfoService.loadByUser(userId);
					if(userInfo != null){
						AJobInfo jobInfo = userInfo.getJobInfo();
						boolean valid = false;
						if(jobInfo != null){
							if(Const.CertificationStatus.PASS.equals(jobInfo.getStatus())){
								valid = true;
							}
						}
						if(valid){
							Integer rate = jobInfo.getPositionLevel().getOrder() >= jobInfo.getTechLevel().getOrder() ? jobInfo.getPositionLevel().getOrder() : jobInfo.getTechLevel().getOrder();
							setPosScore(pojo, jobInfo.getPositionLevel(), jobInfo.getTechLevel(), indicator.getAssociate().getItems());
						}
						else{
							pojo.setSummary("<span style='color:red'>未检测到通过审核的资格等级。请进行验证。</span>");
						}
					}
				}
			}
		}
		else{
			//能力 和 绩效
			if(indicator.getChildren() == null || indicator.getChildren().isEmpty()){
				CEvaluateIndicatorScore capactiyScore = evaluateindicatorscoreService.loadByCategory(categoryId, indicator.getId(), userId);
				if(capactiyScore != null){
					pojo.setScore(capactiyScore.getScore()*1d);
					pojo.setSummary("<span>综合得分："+ capactiyScore.getScore() + "</span>");
				}
				else{
					PArrange arrange = parrangeService.loadByAssess(assessId);
					
					if(arrange!= null && Const.AssessStatus.COMPLETED.equals(arrange.getStatus())){
						List<PIndicatorScore> list = pindicatorscoreService.list4Assess(assessId, indicator.getId());
						
						Double supScore = 1d;
						Double colScore = 1d;
						for(PIndicatorScore score : list){
							if(score.getUser().getId().equals(arrange.getSupAssessor().getId())){
								supScore *= score.getScore();
							}
							if(score.getUser().getId().equals(arrange.getColAssessor().getId())){
								colScore *= score.getScore();
							}
						}
						
						Double result = supScore*0.6d + colScore*0.4d;
						if(result >= 3.5d){
							result = 4d;
						}
						else if(result >= 2.5d){
							result = 3d;
						}
						else if(result >= 1.5d){
							result = 2d;
						}
						else if(result > 0d){
							result = 1d;
						}
						pojo.setScore(result);
						pojo.setSummary("<span>综合得分："+ result + "</span>");
					}
					
				}
			}
		}
		return pojo;
	}
	
	private void setPosScore(AssessPojo pojo, APositionCategory poslevel, APositionCategory techlevel, List<EAssociateItem> items){
		String summary = null;
		List<EAssociateItem> list = new ArrayList<EAssociateItem>();
		list.addAll(items);
		
		SortUtil.sort(list, "score", "desc");
		Double score = 4d;
		for(EAssociateItem item : list){
			if(StringUtil.isNotBlank(item.getVal1())){
				List<Long> val1Ids = StringUtil.split4Long(item.getVal1(), Const.COMMA_DELIMITER);
				if(val1Ids != null && !val1Ids.isEmpty()){
					if(poslevel != null && val1Ids.contains(poslevel.getId())){
						pojo.setScore(score);
						summary = poslevel.getName();
						break;
					}
				}
			}
			if(StringUtil.isNotBlank(item.getVal2())){
				List<Long> val2Ids = StringUtil.split4Long(item.getVal2(), Const.COMMA_DELIMITER);
				if(val2Ids != null && !val2Ids.isEmpty()){
					if(techlevel != null && val2Ids.contains(techlevel.getId())){
						pojo.setScore(score);
						summary = techlevel.getName();
						break;
					}
				}
			}
			score--;
		}
		pojo.setSummary(summary);
	}
	
	private void setScore(AssessPojo pojo, Double result, List<EAssociateItem> items, String prefix, String unit){
		String summary = null;
		Double score = 1d;
		List<EAssociateItem> list = new ArrayList<EAssociateItem>();
		list.addAll(items);
		
		SortUtil.sort(list, "score", "desc");
		for(EAssociateItem item : list){
			if(item.getFormula().equals(">")){
				if(result > item.getBenchmark()){
					score = item.getScore()*1d;
					//summary = prefix + result + unit +", >标准 " + item.getBenchmark() + unit;
					break;
				}
			}
			else if(item.getFormula().equals(">=")){
				if(result >= item.getBenchmark()){
					score = item.getScore()*1d;
					//summary = prefix + result + unit + ", >=标准 " + item.getBenchmark() + unit;
					break;
				}
			}
			else if(item.getFormula().equals("<")){
				if(result < item.getBenchmark()){
					score = item.getScore()*1d;
					//summary = prefix + result + unit + ", <标准 " + item.getBenchmark() + unit;
					break;
				}
			}
			else if(item.getFormula().equals("<=")){
				if(result <= item.getBenchmark()){
					score = item.getScore()*1d;
					//summary = prefix + result + unit + ", <=标准 " + item.getBenchmark() + unit;
					break;
				}
			}
			else if(item.getFormula().equals("==")){
				if(result == item.getBenchmark()){
					score = item.getScore()*1d;
					//summary = prefix + result + unit + ", ==标准 " + item.getBenchmark() + unit;
					break;
				}
			}
		}
		
		summary = prefix + StringUtil.getDouble(result, 2) + unit + "。 ";
		pojo.setAuto(true);
		pojo.setScore(score);
		pojo.setSummary(summary);
	}

	@Override
	public List<AssessPojo> assessList(List<EAssessDetail> details, Long categoryId) {
		List<AssessPojo> list = new ArrayList<AssessPojo>();
		List<EIndicator> tops = this.indicatorDao.find("from EIndicator where parent is null and category.id = " +  categoryId + " order by sequence asc");
		if(tops != null && !tops.isEmpty()){
			list = assessList(tops, details);
		}
		return list;
	}
	
	private List<AssessPojo> assessList(List<EIndicator> indicators, List<EAssessDetail> details){
		List<AssessPojo> list = new ArrayList<AssessPojo>();
		Map<Long, EAssessDetail> detailMap = new HashMap<Long, EAssessDetail>();
		if(details != null && !details.isEmpty()){
			for(EAssessDetail detail : details){
				detailMap.put(detail.getIndicator().getId(), detail);
			}
		}
		
		AssessPojo pojo = null;
		for(EIndicator in : indicators){
			pojo = calculate(in, detailMap);
			list.add(pojo);
			children(pojo, in.getChildren(), detailMap);
		}
		return list;
	}
	
	private AssessPojo calculate(EIndicator indicator, Map<Long, EAssessDetail> detailMap){
		AssessPojo pojo = new AssessPojo();
		pojo.setId(indicator.getId());
		pojo.setMandatory(indicator.getMandatory());
		pojo.setName(indicator.getName());
		pojo.setSequence(indicator.getSequence());
		pojo.setDescription(indicator.getDescription());
		if(detailMap.get(indicator.getId()) != null){
			pojo.setScore(detailMap.get(indicator.getId()).getScore());
			pojo.setCalculation(detailMap.get(indicator.getId()).getCalculation());
		}
		pojo.setAuto(true);
		return pojo;
	}
	
	
	private void children(AssessPojo assess, List<EIndicator> indicators, Map<Long, EAssessDetail> detailMap){
		AssessPojo pojo = null;
		if(indicators != null && !indicators.isEmpty()){
			for(EIndicator in : indicators){
				pojo = calculate(in, detailMap);
				pojo.setParent(assess);
				assess.addChild(pojo);
				children(pojo, in.getChildren(), detailMap);
			}
		}
	}

	@Override
	public Double cauculate(Long assessId) {
		String sql = "select SCORE, INDICATOR_ID from es_assess_project where ASSESS_ID = " + assessId;
		List<Map<String, Object>> result = this.assessDao.getQueryResultToListMap(sql);
		
		List<EIndicator>indicators = this.indicatorDao.find("from EIndicator");
		return null;
	}
}
