package com.boxun.pcdp.performance.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boxun.estms.entity.Const;
import com.boxun.estms.pojo.UserInfo;
import com.boxun.estms.util.StringUtil;
import com.boxun.pcdp.admin.entity.TUser;
import com.boxun.pcdp.admin.service.IUserService;
import com.boxun.pcdp.basic.controller.BaseController;
import com.boxun.pcdp.capacity.pojo.ReviewItemPojo;
import com.boxun.pcdp.capacity.pojo.ReviewPojo;
import com.boxun.pcdp.capacity.pojo.ReviewSetPojo;
import com.boxun.pcdp.estimate.entity.EAssessProject;
import com.boxun.pcdp.estimate.entity.EIndicator;
import com.boxun.pcdp.estimate.entity.EIndicatorItem;
import com.boxun.pcdp.estimate.service.IAssessProjectService;
import com.boxun.pcdp.estimate.service.IIndicatorItemService;
import com.boxun.pcdp.estimate.service.IIndicatorService;
import com.boxun.pcdp.performance.entity.PArrange;
import com.boxun.pcdp.performance.entity.PIndicatorScore;
import com.boxun.pcdp.performance.entity.PItemScore;
import com.boxun.pcdp.performance.pojo.ArrangePojo;
import com.boxun.pcdp.performance.service.IArrangeService;
import com.boxun.pcdp.performance.service.IIndicatorScoreService;
import com.boxun.pcdp.performance.service.IItemScoreService;
import com.boxun.pcdp.performance.service.IReviewService;

@Controller
@RequestMapping("/system/performance/review")
public class ReviewController extends BaseController{

private static final Logger LOGGER = Logger.getLogger(ReviewController.class);
	
	@Autowired
	private IReviewService reviewService;
	
	@Autowired
	private IAssessProjectService assessProjectService;
	
	@Autowired
	private IArrangeService parrangeService;
	
	@Autowired
	private IIndicatorService indicatorService;
	
	@Autowired
	private IIndicatorItemService indicatoritemService;

	@Autowired
	private IItemScoreService itemscoreService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IIndicatorScoreService pindicatorscoreService;
	
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
		return "/performance/review/list";
	}
	
	@RequestMapping("/show/{id}")
	public String show(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get show id: " + id);
		if(id != null){
			PArrange model = parrangeService.load(id);
			modelMap.addAttribute("model", model);
			
			List<EIndicator> tops = this.indicatorService.listTops4Category(model.getAssess().getProject().getCategory().getId(), Const.IndicatorType.PERFORMANCE);
			
			ReviewSetPojo set = new ReviewSetPojo();
			List<ReviewPojo> list = new ArrayList<ReviewPojo>();
			for(EIndicator in : tops){
				ReviewPojo pojo = createPojo(in, null);
				list.add(pojo);
				children(pojo, in.getChildren(), null, null);
			}
			
			set.setList(list);
			modelMap.addAttribute("set", set);
		}
		
		return "/performance/review/show";
	}
	
	@RequestMapping("/listdata")
	public @ResponseBody Map<String, Object> listData(Long projectId){
		LOGGER.info("Get list data.");
		UserInfo userInfo = (UserInfo)this.session.getAttribute("userInfo");
		
		List<PArrange> supArranges = this.parrangeService.list4User(projectId, userInfo.getUserId(), true);
		List<PArrange> colArranges = this.parrangeService.list4User(projectId, userInfo.getUserId(), false);
		
		List<ArrangePojo> listdata = new ArrayList<ArrangePojo>();
		
		for(PArrange arrange : supArranges){
			ArrangePojo pojo = new ArrangePojo();
			pojo.setId(arrange.getId());
			pojo.setUserName(arrange.getAssess().getUser().getName());
			pojo.setStatus(arrange.getSupStatus());
			pojo.setPercentage("60%");
			
			listdata.add(pojo);
		}
		
		for(PArrange arrange : colArranges){
			ArrangePojo pojo = new ArrangePojo();
			pojo.setId(arrange.getId());
			pojo.setUserName(arrange.getAssess().getUser().getName());
			pojo.setStatus(arrange.getColStatus());
			pojo.setPercentage("40%");
			
			listdata.add(pojo);
		}
		
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("sEcho", 1);  
		map.put("iTotalRecords", listdata.size());  
        map.put("iTotalDisplayRecords", listdata.size());  
		map.put("aaData", listdata);
		
		return map;
	}
	
	@RequestMapping("/update/{id}")
	public String update(ModelMap modelMap, @PathVariable Long id){
		UserInfo userInfo = (UserInfo)this.session.getAttribute("userInfo");
		TUser user = this.userService.load(userInfo.getUserId());
		PArrange arrange = parrangeService.load(id);
		String paramName = null;
		String paramValue = null;
		String paramType = null;
		String paramId = null;
		
		String[] arr = null;
		Enumeration<String> enu = this.request.getParameterNames();  
		while(enu.hasMoreElements()){  
			paramName = enu.nextElement();  
			paramValue = this.request.getParameter(paramName);
			if(StringUtil.isNotBlank(paramValue)){
				arr =  paramName.split("_");
				paramType = arr[0];
				
				if("item".equals(paramType)){
					paramId = paramName.split("_")[2];
					EIndicatorItem item = this.indicatoritemService.load(Long.valueOf(paramId));
					PItemScore score = new PItemScore();
					score.setItem(item);
					score.setArrange(arrange);
					score.setScore(Integer.valueOf(paramValue));
					score.setUser(user);
					
					itemscoreService.create(score);
				}
				else if("indicator".equals(paramType)){
					paramId = paramName.split("_")[1];
					EIndicator indicator = this.indicatorService.load(Long.valueOf(paramId));
					PIndicatorScore score = new PIndicatorScore();
					score.setIndicator(indicator);
					score.setArrange(arrange);
					score.setScore(Integer.valueOf(paramValue));
					score.setUser(user);
					
					pindicatorscoreService.create(score);
				}
			}
		}
		
		
		if(arrange.getSupAssessor().getId().equals(userInfo.getUserId())){
			arrange.setSupStatus(Const.AssessStatus.COMPLETED);
		}
		else if(arrange.getColAssessor().getId().equals(userInfo.getUserId())){
			arrange.setColStatus(Const.AssessStatus.COMPLETED);
		}
		
		if(Const.AssessStatus.COMPLETED.equals(arrange.getSupStatus()) && Const.AssessStatus.COMPLETED.equals(arrange.getColStatus())){
			arrange.setStatus(Const.AssessStatus.COMPLETED);
		}
		this.parrangeService.update(arrange);
		return "/success";
	}
	
	@RequestMapping("/review/{id}")
	public String review(ModelMap modelMap, @PathVariable Long id){
		UserInfo userInfo = (UserInfo)this.session.getAttribute("userInfo");
		
		PArrange model = parrangeService.load(id);
		modelMap.addAttribute("model", model);
		
		List<EIndicator> tops = this.indicatorService.listTops4Category(model.getAssess().getProject().getCategory().getId(), Const.IndicatorType.PERFORMANCE);
		
		List<PItemScore> itemScores = this.itemscoreService.list4ArrangeUser(id, userInfo.getUserId());
		Map<Long, PItemScore> itemScoreMap = new HashMap<Long, PItemScore>();
		for(PItemScore score : itemScores){
			itemScoreMap.put(score.getItem().getId(), score);
		}
		
		List<PIndicatorScore> indicatorScores = this.pindicatorscoreService.list4ArrangeUser(id, userInfo.getUserId());
		Map<Long, PIndicatorScore> indicatorScoreMap = new HashMap<Long, PIndicatorScore>();
		for(PIndicatorScore score : indicatorScores){
			indicatorScoreMap.put(score.getIndicator().getId(), score);
		}
		
		ReviewSetPojo set = new ReviewSetPojo();
		List<ReviewPojo> list = new ArrayList<ReviewPojo>();
		for(EIndicator in : tops){
			ReviewPojo pojo = createPojo(in, indicatorScoreMap);
			list.add(pojo);
			children(pojo, in.getChildren(), indicatorScoreMap, itemScoreMap);
		}
		
		set.setList(list);
		modelMap.addAttribute("set", set);
		
		
		return "/performance/review/review";
	}
	private ReviewPojo createPojo(EIndicator indicator, Map<Long, PIndicatorScore> indicatorScoreMap){
		ReviewPojo pojo = new ReviewPojo();
		pojo.setId(indicator.getId());
		pojo.setName(indicator.getName());
		pojo.setSequence(indicator.getSequence());
		pojo.setType(Const.ReviewType.SINGLE);
		if(indicatorScoreMap != null && !indicatorScoreMap.isEmpty()){
			if(indicatorScoreMap.get(indicator.getId()) != null){
				pojo.setScore(indicatorScoreMap.get(indicator.getId()).getScore());
			}
		}
		return pojo;
	}
	
	private void children(ReviewPojo parent, List<EIndicator> indicators, Map<Long, PIndicatorScore> indicatorScoreMap, Map<Long, PItemScore> scoreMap){
		ReviewPojo pojo = null;
		if(indicators != null && !indicators.isEmpty()){
			for(EIndicator in : indicators){
				pojo = createPojo(in, indicatorScoreMap);
				pojo.setParent(parent);
				parent.addChild(pojo);
				List<EIndicatorItem> items = this.indicatoritemService.list4Indicator(in.getId());
				if(items != null && !items.isEmpty()){
					pojo.setType(Const.ReviewType.MULTIPLE);
				}
				for(EIndicatorItem item : items){
					ReviewItemPojo itemPojo = new ReviewItemPojo();
					itemPojo.setId(item.getId());
					itemPojo.setSequence(item.getSequence());
					itemPojo.setDescription(item.getDescription());
					if(scoreMap != null && !scoreMap.isEmpty()){
						if(scoreMap.get(item.getId()) != null){
							itemPojo.setScore(scoreMap.get(item.getId()).getScore());
						}
						
					}
					pojo.addItem(itemPojo);
				}
				children(pojo, in.getChildren(), indicatorScoreMap, scoreMap);
			}
		}
	}
}
