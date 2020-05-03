package com.boxun.pcdp.performance.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boxun.estms.entity.Const;
import com.boxun.pcdp.basic.controller.BaseController;
import com.boxun.pcdp.capacity.pojo.ReviewItemPojo;
import com.boxun.pcdp.capacity.pojo.ReviewPojo;
import com.boxun.pcdp.estimate.entity.EAssess;
import com.boxun.pcdp.estimate.entity.EAssessProject;
import com.boxun.pcdp.estimate.entity.EIndicator;
import com.boxun.pcdp.estimate.entity.EIndicatorItem;
import com.boxun.pcdp.estimate.service.IAssessProjectService;
import com.boxun.pcdp.estimate.service.IAssessService;
import com.boxun.pcdp.estimate.service.IIndicatorItemService;
import com.boxun.pcdp.estimate.service.IIndicatorService;
import com.boxun.pcdp.performance.entity.PArrange;
import com.boxun.pcdp.performance.entity.PIndicatorScore;
import com.boxun.pcdp.performance.entity.PItemScore;
import com.boxun.pcdp.performance.pojo.ArrangePojo;
import com.boxun.pcdp.performance.pojo.ResultPojo;
import com.boxun.pcdp.performance.pojo.ResultSetPojo;
import com.boxun.pcdp.performance.service.IArrangeService;
import com.boxun.pcdp.performance.service.IIndicatorScoreService;
import com.boxun.pcdp.performance.service.IItemScoreService;

@Controller
@RequestMapping("/system/performance/result")
public class ResultController extends BaseController{

	@Autowired
	private IAssessProjectService assessProjectService;
	
	@Autowired
	private IAssessService assessService;
	
	@Autowired
	private IArrangeService parrangeService;
	
	@Autowired
	private IItemScoreService itemscoreService;
	
	@Autowired
	private IIndicatorScoreService pindicatorscoreService;
	
	@Autowired
	private IIndicatorService indicatorService;
	
	@Autowired
	private IIndicatorItemService indicatoritemService;
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap, Long projectId){
		List<EAssessProject> projects = this.assessProjectService.list();
		modelMap.addAttribute("projects", projects);
		if(projectId == null){
			if(projects != null && !projects.isEmpty()){
				projectId = projects.get(0).getId();
			}
			else{
				projectId = 0l;
			}
			
		}
		modelMap.addAttribute("projectId", projectId);
		
		List<EAssess> assessList = assessService.list(projectId);
		
		List<PArrange> arranges = this.parrangeService.list4Project(projectId);
		
		List<ArrangePojo> list = new ArrayList<ArrangePojo>();
		for(EAssess assess : assessList){
			ArrangePojo pojo = new ArrangePojo();
			
			pojo.setUserName(assess.getUser().getName());
			
			for(PArrange arrange : arranges){
				if(assess.getId().equals(arrange.getAssess().getId())){
					pojo.setId(arrange.getId());
					pojo.setSupAssessor(arrange.getSupAssessor().getName());
					pojo.setColAssessor(arrange.getColAssessor().getName());
					pojo.setStatus(arrange.getStatus());
					break;
				}
			}
			list.add(pojo);
		}
		
		modelMap.addAttribute("list", list);
		
		return "/performance/result/list";
	}
	
	@RequestMapping("/show/{id}")
	public String show(ModelMap modelMap, @PathVariable Long id){
		PArrange model = this.parrangeService.load(id);
		
		List<EIndicator> tops = this.indicatorService.listTops4Category(model.getAssess().getProject().getCategory().getId(), Const.IndicatorType.PERFORMANCE);
		
		List<PItemScore> supitemScores = this.itemscoreService.list4ArrangeUser(id, model.getSupAssessor().getId());
		Map<Long, PItemScore> supitemScoreMap = new HashMap<Long, PItemScore>();
		for(PItemScore score : supitemScores){
			supitemScoreMap.put(score.getItem().getId(), score);
		}
		
		List<PIndicatorScore> supindicatorScores = this.pindicatorscoreService.list4ArrangeUser(id, model.getSupAssessor().getId());
		Map<Long, PIndicatorScore> supindicatorScoreMap = new HashMap<Long, PIndicatorScore>();
		for(PIndicatorScore score : supindicatorScores){
			supindicatorScoreMap.put(score.getIndicator().getId(), score);
		}
		
		List<PItemScore> colitemScores = this.itemscoreService.list4ArrangeUser(id, model.getColAssessor().getId());
		Map<Long, PItemScore> colitemScoreMap = new HashMap<Long, PItemScore>();
		for(PItemScore score : colitemScores){
			colitemScoreMap.put(score.getItem().getId(), score);
		}
		
		List<PIndicatorScore> colindicatorScores = this.pindicatorscoreService.list4ArrangeUser(id, model.getColAssessor().getId());
		Map<Long, PIndicatorScore> colindicatorScoreMap = new HashMap<Long, PIndicatorScore>();
		for(PIndicatorScore score : colindicatorScores){
			colindicatorScoreMap.put(score.getIndicator().getId(), score);
		}
		
		ResultSetPojo set = new ResultSetPojo();
		List<ResultPojo> list = new ArrayList<ResultPojo>();
		for(EIndicator in : tops){
			ResultPojo pojo = createPojo(in, supindicatorScoreMap, colindicatorScoreMap);
			list.add(pojo);
			children(pojo, in.getChildren(), supindicatorScoreMap, supitemScoreMap, colindicatorScoreMap, colitemScoreMap);
		}
		
		set.setList(list);
		modelMap.addAttribute("set", set);
		
		
		return "/performance/result/show";
	}
	
	private ResultPojo createPojo(EIndicator indicator, Map<Long, PIndicatorScore> supMap, Map<Long, PIndicatorScore> colMap){
		ResultPojo pojo = new ResultPojo();
		pojo.setId(indicator.getId());
		pojo.setName(indicator.getName());
		pojo.setSequence(indicator.getSequence());
		//pojo.setType(Const.ReviewType.SINGLE);
		if(supMap != null && !supMap.isEmpty() && colMap != null && !colMap.isEmpty()){
			Double result = 0d;
			if(supMap.get(indicator.getId()) != null && colMap.get(indicator.getId()) != null){
				result = supMap.get(indicator.getId()).getScore()*0.6d + colMap.get(indicator.getId()).getScore()*0.4d;
			}
			
			if(result >= 3.5d){
				pojo.setScore(4);
			}
			else if(result >= 2.5d){
				pojo.setScore(3);
			}
			else if(result >= 1.5d){
				pojo.setScore(2);
			}
			else if(result > 0d){
				pojo.setScore(1);
			}
		}
		return pojo;
	}
	
	private void children(ResultPojo parent, List<EIndicator> indicators, Map<Long, PIndicatorScore> supindicatorScoreMap, Map<Long, PItemScore> supitemScoreMap, Map<Long, PIndicatorScore> colindicatorScoreMap, Map<Long, PItemScore> colitemScoreMap){
		ResultPojo pojo = null;
		if(indicators != null && !indicators.isEmpty()){
			for(EIndicator in : indicators){
				pojo = createPojo(in, supindicatorScoreMap, colindicatorScoreMap);
				pojo.setParent(parent);
				parent.addChild(pojo);
				List<EIndicatorItem> items = this.indicatoritemService.list4Indicator(in.getId());

				for(EIndicatorItem item : items){
					ReviewItemPojo supitemPojo = new ReviewItemPojo();
					supitemPojo.setId(item.getId());
					supitemPojo.setSequence(item.getSequence());
					supitemPojo.setDescription(item.getDescription());
					if(supitemScoreMap != null && !supitemScoreMap.isEmpty()){
						if(supitemScoreMap.get(item.getId()) != null){
							supitemPojo.setScore(supitemScoreMap.get(item.getId()).getScore());
						}
						
					}
					pojo.addSupItem(supitemPojo);
					
					ReviewItemPojo colitemPojo = new ReviewItemPojo();
					colitemPojo.setId(item.getId());
					colitemPojo.setSequence(item.getSequence());
					colitemPojo.setDescription(item.getDescription());
					if(colitemScoreMap != null && !colitemScoreMap.isEmpty()){
						if(colitemScoreMap.get(item.getId()) != null){
							colitemPojo.setScore(colitemScoreMap.get(item.getId()).getScore());
						}
						
					}
					pojo.addColItem(colitemPojo);
				}
				children(pojo, in.getChildren(), supindicatorScoreMap, supitemScoreMap, colindicatorScoreMap, colitemScoreMap);
			}
		}
	}
}
