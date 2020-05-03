package com.boxun.pcdp.capacity.controller;

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

import com.boxun.estms.entity.Const;
import com.boxun.estms.pojo.UserInfo;
import com.boxun.estms.util.StringUtil;
import com.boxun.pcdp.basic.controller.BaseController;
import com.boxun.pcdp.capacity.entity.CEvaluateAnswer;
import com.boxun.pcdp.capacity.entity.CEvaluateArrange;
import com.boxun.pcdp.capacity.entity.CEvaluateArrangeUser;
import com.boxun.pcdp.capacity.entity.CEvaluateIndicatorScore;
import com.boxun.pcdp.capacity.entity.CEvaluateItemScore;
import com.boxun.pcdp.capacity.pojo.ReviewItemPojo;
import com.boxun.pcdp.capacity.pojo.ReviewPojo;
import com.boxun.pcdp.capacity.pojo.ReviewSetPojo;
import com.boxun.pcdp.capacity.service.IEvaluateAnswerService;
import com.boxun.pcdp.capacity.service.IEvaluateArrangeService;
import com.boxun.pcdp.capacity.service.IEvaluateArrangeUserService;
import com.boxun.pcdp.capacity.service.IEvaluateIndicatorScoreService;
import com.boxun.pcdp.capacity.service.IEvaluateItemScoreService;
import com.boxun.pcdp.capacity.service.IEvaluateProjectService;
import com.boxun.pcdp.capacity.service.IEvaluateReviewService;
import com.boxun.pcdp.estimate.entity.EIndicator;
import com.boxun.pcdp.estimate.entity.EIndicatorItem;
import com.boxun.pcdp.estimate.service.IIndicatorItemService;
import com.boxun.pcdp.estimate.service.IIndicatorService;

@Controller
@RequestMapping("/system/capacity/evaluatereview")
public class EvaluateReviewController extends BaseController{

private static final Logger LOGGER = Logger.getLogger(EvaluateReviewController.class);
	
	@Autowired
	private IEvaluateReviewService evaluatereviewService;
	
	@Autowired
	private IEvaluateArrangeService evaluatearrangeService;
	
	@Autowired
	private IEvaluateProjectService evaluateprojectService;
	
	@Autowired
	private IEvaluateArrangeUserService carrangeuserService;
	
	@Autowired
	private IEvaluateAnswerService evaluateanswerService;
	
	@Autowired
	private IIndicatorService indicatorService;
	
	@Autowired
	private IIndicatorItemService indicatoritemService;
	
	@Autowired
	private IEvaluateItemScoreService evaluateitemscoreService;
	
	@Autowired
	private IEvaluateIndicatorScoreService evaluateindicatorscoreService;
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap, Long arrId){
		UserInfo userInfo = (UserInfo)this.session.getAttribute("userInfo");
		Long userId = 0L;
		if(userInfo != null){
			userId = userInfo.getUserId();
		}
		
		List<CEvaluateArrange> arranges = evaluatearrangeService.list4User(userId);
		modelMap.addAttribute("arranges", arranges);
		
		if(arrId == null){
			if(arranges != null && !arranges.isEmpty()){
				arrId = arranges.get(0).getId();
			}
			else{
				arrId = 0l;
			}
			
		}
		modelMap.addAttribute("arrId", arrId);
		
		if(arrId != null){
//			KArrange arrange = this.carrangeuserService.load(arrId);
			List<CEvaluateArrangeUser> list = this.carrangeuserService.list(arrId);
			modelMap.addAttribute("list", list);
		}
		
		return "/capacity/evaluatereview/list";
	}
	
	
	@RequestMapping("/show/{id}")
	public String show(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get show id: " + id);
		modelMap.addAttribute("id", id);
		return "/capacity/evaluatereview/show";
	}
	
	@RequestMapping("/view/{id}")
	public String view(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get show id: " + id);
		modelMap.addAttribute("id", id);
		return "/capacity/evaluatereview/view";
	}
	
	@RequestMapping("/detail/{id}")
	public String detail(ModelMap modelMap, @PathVariable Long id){
		if(id != null){
			CEvaluateArrangeUser model = carrangeuserService.load(id);
			modelMap.addAttribute("model", model);
			
			List<CEvaluateAnswer> answers = this.evaluateanswerService.list(model.getId());
			modelMap.addAttribute("answers", answers);
		}
		
		return "/capacity/evaluatereview/detail";
	}
	
	@RequestMapping("/content/{id}")
	public String content(ModelMap modelMap, @PathVariable Long id){
		CEvaluateArrangeUser model = carrangeuserService.load(id);
		modelMap.addAttribute("model", model);
		
		List<EIndicator> tops = this.indicatorService.listTops4Category(model.getArrange().getProject().getCategory().getId(), Const.IndicatorType.CAPACITY);
		
		ReviewSetPojo set = new ReviewSetPojo();
		List<ReviewPojo> list = new ArrayList<ReviewPojo>();
		for(EIndicator in : tops){
			ReviewPojo pojo = createPojo(in, null);
			list.add(pojo);
			children(pojo, in.getChildren(), null, null);
		}
		
		set.setList(list);
		modelMap.addAttribute("set", set);
		
		
		return "/capacity/evaluatereview/content";
	}
	
	@RequestMapping("/update/{id}")
	public String update(ModelMap modelMap, @PathVariable Long id){
		
		CEvaluateArrangeUser arrangeUser = carrangeuserService.load(id);
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
					CEvaluateItemScore score = new CEvaluateItemScore();
					score.setItem(item);
					score.setArrangeUser(arrangeUser);
					score.setScore(Integer.valueOf(paramValue));
					
					evaluateitemscoreService.create(score);
				}
				
				else if("indicator".equals(paramType)){
					paramId = paramName.split("_")[1];
					EIndicator indicator = this.indicatorService.load(Long.valueOf(paramId));
					CEvaluateIndicatorScore score = new CEvaluateIndicatorScore();
					score.setIndicator(indicator);
					score.setArrangeUser(arrangeUser);
					score.setScore(Integer.valueOf(paramValue));
					
					evaluateindicatorscoreService.create(score);
				}
			}
		}
		
		arrangeUser.setCheckStatus(Const.ArrangeUserStatus.COMPLETED);
		this.carrangeuserService.update(arrangeUser);
		return "/success";
	}
	
	
	@RequestMapping("/review/{id}")
	public String review(ModelMap modelMap, @PathVariable Long id){
		CEvaluateArrangeUser model = carrangeuserService.load(id);
		modelMap.addAttribute("model", model);
		
		List<EIndicator> tops = this.indicatorService.listTops4Category(model.getArrange().getProject().getCategory().getId(), Const.IndicatorType.CAPACITY);
		
		List<CEvaluateItemScore> itemScores = this.evaluateitemscoreService.list4ArrangeUser(id);
		Map<Long, CEvaluateItemScore> itemScoreMap = new HashMap<Long, CEvaluateItemScore>();
		for(CEvaluateItemScore score : itemScores){
			itemScoreMap.put(score.getItem().getId(), score);
		}
		
		List<CEvaluateIndicatorScore> indicatorScores = this.evaluateindicatorscoreService.list4ArrangeUser(id);
		Map<Long, CEvaluateIndicatorScore> indicatorScoreMap = new HashMap<Long, CEvaluateIndicatorScore>();
		for(CEvaluateIndicatorScore score : indicatorScores){
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
		
		
		return "/capacity/evaluatereview/review";
	}
	
	
	private ReviewPojo createPojo(EIndicator indicator, Map<Long, CEvaluateIndicatorScore> indicatorScoreMap){
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
	
	private void children(ReviewPojo parent, List<EIndicator> indicators, Map<Long, CEvaluateIndicatorScore> indicatorScoreMap, Map<Long, CEvaluateItemScore> itemScoreMap){
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
					if(itemScoreMap != null && !itemScoreMap.isEmpty()){
						if(itemScoreMap.get(item.getId()) != null){
							itemPojo.setScore(itemScoreMap.get(item.getId()).getScore());
						}
						
					}
					pojo.addItem(itemPojo);
				}
				children(pojo, in.getChildren(), indicatorScoreMap, itemScoreMap);
			}
		}
	}
}
