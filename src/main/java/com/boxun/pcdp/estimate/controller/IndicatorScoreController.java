package com.boxun.pcdp.estimate.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boxun.estms.pojo.UserInfo;
import com.boxun.estms.util.StringUtil;
import com.boxun.pcdp.admin.entity.TUser;
import com.boxun.pcdp.admin.service.IUserService;
import com.boxun.pcdp.basic.controller.BaseController;
import com.boxun.pcdp.estimate.entity.EIndicator;
import com.boxun.pcdp.estimate.entity.EIndicatorCategory;
import com.boxun.pcdp.estimate.entity.EIndicatorScoreItem;
import com.boxun.pcdp.estimate.pojo.IndicatorCategoryPojo;
import com.boxun.pcdp.estimate.pojo.IndicatorScoreCategoryPojo;
import com.boxun.pcdp.estimate.pojo.IndicatorScorePojo;
import com.boxun.pcdp.estimate.pojo.IndicatorUserScorePojo;
import com.boxun.pcdp.estimate.service.IIndicatorCategoryService;
import com.boxun.pcdp.estimate.service.IIndicatorScoreService;
import com.boxun.pcdp.estimate.service.IIndicatorService;
import com.boxun.pcdp.estimate.transformer.IndicatorScoreTransformer;

@Controller
@RequestMapping("/system/estimate/indicatorscore")
public class IndicatorScoreController extends BaseController{

	private static final Logger LOGGER = Logger.getLogger(IndicatorScoreController.class);
	
	@Autowired
	private IUserService userService;

	@Autowired
	private IIndicatorService indicatorService;
	
	@Autowired
	private IIndicatorScoreService indicatorScoreService;
	
	@Autowired
	private IndicatorScoreTransformer indicatorScoreTransformer;
	
	@Autowired
	private IIndicatorCategoryService indicatorcategoryService;
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap){
		
		List<IndicatorScoreCategoryPojo> mylist = new ArrayList<IndicatorScoreCategoryPojo>();
		List<EIndicatorCategory> catelist = this.indicatorcategoryService.listActive();
		UserInfo userInfo = (UserInfo)this.session.getAttribute("userInfo");
		List<EIndicatorScoreItem> scoreList = this.indicatorScoreService.list(userInfo.getUserId());
		
		for(EIndicatorCategory cat : catelist){
			IndicatorScoreCategoryPojo pojo = new IndicatorScoreCategoryPojo();
			pojo.setId(cat.getId());
			pojo.setName(cat.getName());
			
			List<EIndicator> tops = indicatorService.listTops4Category(cat.getId());

			Map<Long, EIndicatorScoreItem> indScoreMaps = new HashMap<Long, EIndicatorScoreItem>();
			
			for(EIndicatorScoreItem score : scoreList){
				indScoreMaps.put(score.getIndicator().getId(), score);
			}
			List<IndicatorScorePojo> listdata = scoreList(tops, indScoreMaps);
			pojo.setList(listdata);
			mylist.add(pojo);
		}

		modelMap.addAttribute("mylist", mylist);
		return "/estimate/indicatorscore/list";
	}
	
	@RequestMapping("/statics")
	public String statics(ModelMap modelMap){
		
		List<IndicatorScoreCategoryPojo> list = new ArrayList<IndicatorScoreCategoryPojo>();
		
		List<EIndicatorCategory> catelist = this.indicatorcategoryService.listActive();
		
		for(EIndicatorCategory cat : catelist){
			IndicatorScoreCategoryPojo pojo = new IndicatorScoreCategoryPojo();
			pojo.setId(cat.getId());
			pojo.setName(cat.getName());
			
			List<EIndicator> tops = indicatorService.listTops4Category(cat.getId());
			
			List<Long> indIds = this.indicatorService.listIds(tops);
			if(indIds.isEmpty()){
				indIds.add(-1l);
			}
			
			List<EIndicatorScoreItem> scoreList = this.indicatorScoreService.listOrderByUser(indIds);
			Map<Long, List<EIndicatorScoreItem>> indScoreMaps = new HashMap<Long, List<EIndicatorScoreItem>>();

			List<Long> userIds = new ArrayList<Long>();
			List<String> names = new ArrayList<String>();
			
			Map<Long, Map<Long, EIndicatorScoreItem>> maps = new HashMap<Long, Map<Long, EIndicatorScoreItem>>();
			
			for(EIndicatorScoreItem score : scoreList){
				if(indScoreMaps.get(score.getIndicator().getId()) == null){
					indScoreMaps.put(score.getIndicator().getId(), new ArrayList<EIndicatorScoreItem>());
				}
				indScoreMaps.get(score.getIndicator().getId()).add(score);
				
				
				if(!userIds.contains(score.getUser().getId())){
					userIds.add(score.getUser().getId());
					names.add(score.getUser().getName());
				}
				
				Map<Long, EIndicatorScoreItem> map = maps.get(score.getUser().getId());
				if(map == null){
					map = new HashMap<Long, EIndicatorScoreItem>();
					maps.put(score.getUser().getId(), map);
				}
				map.put(score.getIndicator().getId(), score);
			}
			
			List<IndicatorScorePojo> listdata = staticScoreList(tops, userIds, maps);
			pojo.setList(listdata);
			pojo.setSize(names.size());
			pojo.setNames(names);

			list.add(pojo);
		}

		modelMap.addAttribute("list", list);
		
		return "/estimate/indicatorscore/statics";
	}
	
	@RequestMapping("/update")
	public @ResponseBody Map<String, Object> update(ModelMap modelMap){

		String paramName = null;
		String paramValue = null;
		String id = null;
		
		Map<String, Object> map  = new HashMap<String, Object>();
		try{
			List<EIndicator> list = indicatorService.list();
			Map<String, EIndicator> indMaps = new HashMap<String, EIndicator>();
			for(EIndicator indicator : list){
				indMaps.put(indicator.getId().toString(), indicator);
			}
			
			UserInfo userInfo = (UserInfo)this.session.getAttribute("userInfo");
			TUser user = userService.load(userInfo.getUserId());
			
			Map<String, EIndicatorScoreItem> indScoreMaps = new HashMap<String, EIndicatorScoreItem>();
			List<EIndicatorScoreItem> scoreList = this.indicatorScoreService.list(userInfo.getUserId());
			for(EIndicatorScoreItem score : scoreList){
				indScoreMaps.put(score.getIndicator().getId().toString(), score);
			}
			
			EIndicatorScoreItem score = null;
			EIndicator indicator = null;
			Enumeration<String> enu = this.request.getParameterNames();  
			while(enu.hasMoreElements()){  
				paramName = enu.nextElement();  
				paramValue = this.request.getParameter(paramName);
				if(StringUtil.isNotBlank(paramValue)){
					id = paramName.split("_")[1];
					
					score = indScoreMaps.get(id);
					indicator = indMaps.get(id);
					if(score == null){
						score = new EIndicatorScoreItem();
						score.setIndicator(indicator);
						score.setUser(user);
					}
					score.setScore(Integer.parseInt(paramValue));
					this.indicatorScoreService.update(score);
				}
			}
			
			this.indicatorService.updateScore();
			map.put("status", "success");  
		}
		catch(Exception e){
			map.put("status", "fail");  
		}
		return map; 
	}
	
	private List<IndicatorScorePojo> staticScoreList(List<EIndicator> indicators, List<Long> users, Map<Long, Map<Long, EIndicatorScoreItem>> indScoreMaps){
		List<IndicatorScorePojo> list = new ArrayList<IndicatorScorePojo>();
		
		if(indScoreMaps != null && !indScoreMaps.isEmpty() && users != null && !users.isEmpty()){
			IndicatorScorePojo score = null;
			for(EIndicator ind : indicators){
				score = this.indicatorScoreTransformer.convert(ind);
				
				for(Long user : users){
					Map<Long, EIndicatorScoreItem> map = indScoreMaps.get(user);
					if(map != null){
						
						IndicatorUserScorePojo userscore = new IndicatorUserScorePojo();
						EIndicatorScoreItem userScore = map.get(score.getId());
						if(userScore != null){
							userscore.setUserName(userScore.getUser().getName());
							userscore.setScore(userScore.getScore());
						}
						score.addUserScore(userscore);
					}
				}
				list.add(score);
				
				if(score != null){
					staticChildren(score, ind.getChildren(), users, indScoreMaps);
				}
			}
		}
		
		return list;
	}
	
	private List<IndicatorScorePojo> scoreList(List<EIndicator> indicators, Map<Long, EIndicatorScoreItem> indScoreMaps){
		List<IndicatorScorePojo> list = new ArrayList<IndicatorScorePojo>();
		IndicatorScorePojo score = null;
		for(EIndicator ind : indicators){
			score = this.indicatorScoreTransformer.convert(ind);
			if(indScoreMaps.get(score.getId()) != null){
				score.setScore(indScoreMaps.get(score.getId()).getScore());
			}
			else{
				score.setScore(null);
			}
			list.add(score);
			if(score != null){
				children(score, ind.getChildren(), indScoreMaps);
			}
		}
		return list;
	}
	
	private void staticChildren(IndicatorScorePojo parent, List<EIndicator> indicators, List<Long> users, Map<Long, Map<Long, EIndicatorScoreItem>> indScoreMaps){
		if(indicators != null && !indicators.isEmpty()){
			for(EIndicator ind : indicators){
				IndicatorScorePojo score = this.indicatorScoreTransformer.convert(ind);
				for(Long user : users){
					Map<Long, EIndicatorScoreItem> map = indScoreMaps.get(user);
					if(map != null){
						IndicatorUserScorePojo userscore = new IndicatorUserScorePojo();
						EIndicatorScoreItem userScore = map.get(score.getId());
						if(userScore != null){
							userscore.setUserName(userScore.getUser().getName());
							userscore.setScore(userScore.getScore());
						}
						
						score.addUserScore(userscore);
					}
				}
//				
//				
//				List<EIndicatorScoreItem> userScores = indScoreMaps.get(pojo.getId());
//				if(userScores != null){
//					for(EIndicatorScoreItem item : userScores){
//						IndicatorUserScorePojo userscore = new IndicatorUserScorePojo();
//						userscore.setUserName(item.getUser().getName());
//						userscore.setScore(item.getScore());
//						pojo.addUserScore(userscore);
//					}
//				}
				score.setParent(parent);
				parent.addChild(score);
				staticChildren(score, ind.getChildren(), users, indScoreMaps);
			}
		}
	}
	
	private void children(IndicatorScorePojo score, List<EIndicator> indicators, Map<Long, EIndicatorScoreItem> indScoreMaps){
		if(indicators != null && !indicators.isEmpty()){
			for(EIndicator ind : indicators){
				IndicatorScorePojo pojo = this.indicatorScoreTransformer.convert(ind);
				pojo.setParent(score);
				if(indScoreMaps.get(pojo.getId()) != null){
					pojo.setScore(indScoreMaps.get(pojo.getId()).getScore());
				}
				else{
					pojo.setScore(null);
				}
				score.addChild(pojo);
				children(pojo, ind.getChildren(), indScoreMaps);
			}
		}
	}
}
