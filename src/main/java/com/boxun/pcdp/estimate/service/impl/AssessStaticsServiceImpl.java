package com.boxun.pcdp.estimate.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Service;

import com.boxun.estms.entity.Const;
import com.boxun.pcdp.estimate.dao.impl.AssessDaoImpl;
import com.boxun.pcdp.estimate.dao.impl.AssessItemDaoImpl;
import com.boxun.pcdp.estimate.dao.impl.AssessStaticsDaoImpl;
import com.boxun.pcdp.estimate.dao.impl.IndicatorDaoImpl;
import com.boxun.pcdp.estimate.entity.EAssess;
import com.boxun.pcdp.estimate.entity.EAssessDetail;
import com.boxun.pcdp.estimate.entity.EAssessItem;
import com.boxun.pcdp.estimate.entity.EAssessStatics;
import com.boxun.pcdp.estimate.entity.EIndicator;
import com.boxun.pcdp.estimate.pojo.AssessStaticsPojo;
import com.boxun.pcdp.estimate.pojo.AssessStaticsSetPojo;
import com.boxun.pcdp.estimate.pojo.AssessStaticsUserPojo;
import com.boxun.pcdp.estimate.service.IAssessStaticsService;

@Service("assesstaticsService")
public class AssessStaticsServiceImpl implements IAssessStaticsService{

	@Autowired
	private AssessStaticsDaoImpl assesstaticsDao;
	
	@Autowired
	private AssessDaoImpl assessDao;
	
	@Autowired
	private IndicatorDaoImpl indicatorDao;
	
	@Autowired
	private AssessItemDaoImpl assessitemDao;
	
	@Override
	public void create(EAssessStatics entity) {
		this.assesstaticsDao.save(entity);
	}

	@Override
	public void update(EAssessStatics entity) {
		this.assesstaticsDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(EAssessStatics entity) {
		this.assesstaticsDao.delete(entity);
	}

	@Override
	public EAssessStatics load(Long id) {
		return this.assesstaticsDao.load(id);
	}

	@Override
	public List<EAssessStatics> list() {
		return this.assesstaticsDao.loadAll();
	}

	@Override
	public Boolean calcResult(EAssess assess) {
		
		List<EAssessItem> items = assess.getItems();
		boolean allCompleted = false;
		if(items != null && !items.isEmpty()){
			allCompleted = true;
			//this logic need to revised, since hibernate cache
			for(EAssessItem item : items){
				if(!Const.AssessStatus.COMPLETED.equals(item.getStatus())){
					allCompleted = false;
					break;
				}
			}
			
			if(allCompleted){
//				List<EIndicator> indicators = this.indicatorDao.loadAll();
				final Long assessId = assess.getId();
				
				this.assesstaticsDao.getHibernateTemplate().execute(new HibernateCallback<Object>(){   
		            public Object doInHibernate(Session session) throws HibernateException{ 
		            	String sql = "DELETE FROM es_assess_result WHERE ASSESS_ID = " + assessId;
		            	session.createSQLQuery(sql).executeUpdate(); 
		            	
		            	sql = "INSERT INTO es_assess_result (ASSESS_ID, INDICATOR_ID, RESULT) SELECT item.ASSESS_ID, dtl.INDICATOR_ID, ROUND(AVG(dtl.CALCULATION),3) result FROM es_assess_dtl dtl,es_assess_item item WHERE dtl.ASSESS_ITEM_ID = item.ID AND item.ASSESS_ID = " + assessId + " GROUP BY dtl.INDICATOR_ID";
		            	session.createSQLQuery(sql).executeUpdate(); 
		            	
		            	sql = "UPDATE es_assess SET ASSESS_SCORE = (SELECT ROUND(AVG(item.ASSESS_SCORE),3) FROM es_assess_item item WHERE ASSESS_ID = " + assessId + " ), status = '"+ Const.AssessStatus.COMPLETED.toString() + "' WHERE ID = " + assessId;
		            	session.createSQLQuery(sql).executeUpdate(); 
		            			
		            	session.flush();
		            	return null;
		            }
				});
			}
		}
		
		
		//assess.setStatus();
		//this.assessDao.update(assess);
		
		return null;
	}

	@Override
	public AssessStaticsSetPojo assessList(EAssess assess) {
		AssessStaticsSetPojo set = new AssessStaticsSetPojo();
		set.addScore(assess.getAssessScore());
		
		Map<Long, List<EAssessDetail>> indDtlMaps = new HashMap<Long, List<EAssessDetail>>();

		for(EAssessItem item : assess.getItems()){
			for(EAssessDetail dtl : item.getDetails()){
				if(indDtlMaps.get(dtl.getIndicator().getId()) == null){
					indDtlMaps.put(dtl.getIndicator().getId(), new ArrayList<EAssessDetail>());
				}
				indDtlMaps.get(dtl.getIndicator().getId()).add(dtl);
			}
			set.addScore(item.getAssessScore());
		}

		
		List<AssessStaticsPojo> list = new ArrayList<AssessStaticsPojo>();
		List<EAssessStatics> statics = assess.getStatics();
		List<EIndicator> tops = this.indicatorDao.find("from EIndicator where parent is null order by sequence asc");
		if(tops != null && !tops.isEmpty()){
			list = assessList(tops, statics, indDtlMaps);
		}
		set.setList(list);
		set.setUserSize(assess.getItems().size());
		return set;
	}

	private List<AssessStaticsPojo> assessList(List<EIndicator> indicators, List<EAssessStatics> details, Map<Long, List<EAssessDetail>> indDtlMaps){
		List<AssessStaticsPojo> list = new ArrayList<AssessStaticsPojo>();
		Map<Long, EAssessStatics> detailMap = new HashMap<Long, EAssessStatics>();
		if(details != null && !details.isEmpty()){
			for(EAssessStatics detail : details){
				detailMap.put(detail.getIndicator().getId(), detail);
			}
		}
		
		AssessStaticsPojo pojo = null;
		for(EIndicator in : indicators){
			pojo = calculate(in, detailMap);
			List<EAssessDetail> userScores = indDtlMaps.get(pojo.getId());
			if(userScores != null){
				for(EAssessDetail item : userScores){
					AssessStaticsUserPojo userscore = new AssessStaticsUserPojo();
					userscore.setScore(item.getCalculation());
					pojo.addUserScore(userscore);
				}
			}
			
			list.add(pojo);
			children(pojo, in.getChildren(), detailMap, indDtlMaps);
		}
		return list;
	}
	
	private AssessStaticsPojo calculate(EIndicator indicator, Map<Long, EAssessStatics> detailMap){
		AssessStaticsPojo pojo = new AssessStaticsPojo();
		pojo.setId(indicator.getId());
		pojo.setName(indicator.getName());
		pojo.setSequence(indicator.getSequence());
		if(detailMap.get(indicator.getId()) != null){
			pojo.setResult(detailMap.get(indicator.getId()).getResult());
		}
		return pojo;
	}
	
	
	private void children(AssessStaticsPojo staticPojo, List<EIndicator> indicators, Map<Long, EAssessStatics> detailMap, Map<Long, List<EAssessDetail>> indDtlMaps){
		AssessStaticsPojo pojo = null;
		if(indicators != null && !indicators.isEmpty()){
			for(EIndicator in : indicators){
				pojo = calculate(in, detailMap);
				pojo.setParent(staticPojo);
				staticPojo.addChild(pojo);
				List<EAssessDetail> userScores = indDtlMaps.get(pojo.getId());
				if(userScores != null){
					for(EAssessDetail item : userScores){
						AssessStaticsUserPojo userscore = new AssessStaticsUserPojo();
						userscore.setScore(item.getCalculation());
						pojo.addUserScore(userscore);
					}
				}
				children(pojo, in.getChildren(), detailMap, indDtlMaps);
			}
		}
	}

	@Override
	public List<EAssessStatics> list(Long assessId, List<Long> indicators) {
		DetachedCriteria criteria = this.assesstaticsDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("assess.id", assessId));
		if(indicators != null && !indicators.isEmpty()){
			criteria.add(Restrictions.in("indicator.id", indicators));
		}
		return this.assesstaticsDao.findByCriteria(criteria);
	}
}
