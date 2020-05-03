package com.boxun.pcdp.estimate.controller;

import java.math.BigDecimal;
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

import com.boxun.estms.entity.Const;
import com.boxun.estms.pojo.UserInfo;
import com.boxun.estms.util.SortUtil;
import com.boxun.estms.util.StringUtil;
import com.boxun.pcdp.admin.entity.TUser;
import com.boxun.pcdp.admin.service.IUserService;
import com.boxun.pcdp.basic.controller.BaseController;
import com.boxun.pcdp.estimate.entity.EIndicator;
import com.boxun.pcdp.estimate.entity.EIndicatorCategory;
import com.boxun.pcdp.estimate.entity.EIndicatorImportance;
import com.boxun.pcdp.estimate.entity.EIndicatorImportanceItem;
import com.boxun.pcdp.estimate.pojo.IndicatorImpoPojo;
import com.boxun.pcdp.estimate.pojo.IndicatorImpoSet;
import com.boxun.pcdp.estimate.pojo.IndicatorImpoSetCategoryPojo;
import com.boxun.pcdp.estimate.pojo.IndicatorPojo;
import com.boxun.pcdp.estimate.pojo.IndicatorUserImpoPojo;
import com.boxun.pcdp.estimate.service.IIndicatorCategoryService;
import com.boxun.pcdp.estimate.service.IIndicatorImportanceItemService;
import com.boxun.pcdp.estimate.service.IIndicatorImportanceService;
import com.boxun.pcdp.estimate.service.IIndicatorService;
import com.boxun.pcdp.estimate.transformer.IndicatorImpoTransformer;
import com.boxun.pcdp.estimate.transformer.IndicatorTransformer;

@Controller
@RequestMapping("/system/estimate/indicatorimpo")
public class IndicatorImportanceController extends BaseController{

private static final Logger LOGGER = Logger.getLogger(IndicatorImportanceController.class);
	
	@Autowired
	private IIndicatorImportanceService indicatorimportanceService;
	
	@Autowired
	private IIndicatorImportanceItemService indicatorimportanceitemService;
	
	@Autowired
	private IIndicatorService indicatorService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IndicatorTransformer indicatorTransformer;
	
	@Autowired
	private IndicatorImpoTransformer indicatorImpoTransformer;
	
	@Autowired
	private IIndicatorCategoryService indicatorcategoryService;
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap){
		List<IndicatorImpoSetCategoryPojo> mylist = new ArrayList<IndicatorImpoSetCategoryPojo>();
		List<EIndicatorCategory> catelist = this.indicatorcategoryService.listActive();
		
		UserInfo userInfo = (UserInfo)this.session.getAttribute("userInfo");
		
		for(EIndicatorCategory cat : catelist){
			IndicatorImpoSetCategoryPojo pojo = new IndicatorImpoSetCategoryPojo();
			pojo.setId(cat.getId());
			pojo.setName(cat.getName());
			
			List<EIndicator> tops = indicatorService.listTops4Category(cat.getId());
			SortUtil.sort(tops, "score", "desc");
			
			List<EIndicatorImportance> impoList = this.indicatorimportanceService.list4Category(cat.getId());
			
			List<IndicatorImpoSet> listdata = new ArrayList<IndicatorImpoSet>();
			getList(tops, listdata);
			setRating(listdata, impoList, false, userInfo.getUserId());
			SortUtil.sort(mylist, "level");
			
			pojo.setList(listdata);
//			Integer size = 0;
//			for(EIndicatorImportance in : impoList){
//				if(in.getItems() != null){
//					size = in.getItems().size();
//					break;
//				}
//			}
			
			mylist.add(pojo);
		}
		
		modelMap.addAttribute("mylist", mylist);
		//modelMap.addAttribute("size", size);
		
		return "/estimate/indicatorimportance/list";
	}
	
	@RequestMapping("/statics")
	public String statics(ModelMap modelMap){

		List<IndicatorImpoSetCategoryPojo> list = new ArrayList<IndicatorImpoSetCategoryPojo>();
		List<EIndicatorCategory> catelist = this.indicatorcategoryService.listActive();
		
		for(EIndicatorCategory cat : catelist){
			IndicatorImpoSetCategoryPojo pojo = new IndicatorImpoSetCategoryPojo();
			pojo.setId(cat.getId());
			pojo.setName(cat.getName());
			
			List<EIndicator> tops = indicatorService.listTops4Category(cat.getId());
			SortUtil.sort(tops, "score", "desc");
			
			List<EIndicatorImportance> impoList = this.indicatorimportanceService.list4Category(cat.getId());
			
			List<EIndicatorImportanceItem> itemList = indicatorimportanceitemService.list4Category(cat.getId());
			List<Long> userIds = new ArrayList<Long>();
			List<String> names = new ArrayList<String>();
			
			for(EIndicatorImportanceItem impo : itemList){
				if(!userIds.contains(impo.getUser().getId())){
					userIds.add(impo.getUser().getId());
					names.add(impo.getUser().getName());
				}
			}

			List<IndicatorImpoSet> listdata = new ArrayList<IndicatorImpoSet>();
			getList(tops, listdata);
			setRating4All(listdata, impoList, userIds);
			SortUtil.sort(listdata, "level");
			
			pojo.setList(listdata);
			pojo.setSize(names.size());
			pojo.setNames(names);
			
			list.add(pojo);
		}
		
		
//		List<EIndicator> indxList = indicatorService.listTops();
//		SortUtil.sort(indxList, "score", "desc");
//		
//		List<EIndicatorImportance> impoList = this.indicatorimportanceService.list();
//		
//		List<EIndicatorImportanceItem> itemList = indicatorimportanceitemService.list();
//		List<Long> userIds = new ArrayList<Long>();
//		List<String> names = new ArrayList<String>();
//		
//		for(EIndicatorImportanceItem impo : itemList){
//			if(!userIds.contains(impo.getUser().getId())){
//				userIds.add(impo.getUser().getId());
//				names.add(impo.getUser().getName());
//			}
//		}
//
//		List<IndicatorImpoSet> list = new ArrayList<IndicatorImpoSet>();
//		getList(indxList, list);
//		setRating4All(list, impoList, userIds);
//		SortUtil.sort(list, "level");
		
		modelMap.addAttribute("list", list);
//		modelMap.addAttribute("size", names.size());
//		modelMap.addAttribute("names", names);
		
		return "/estimate/indicatorimportance/statics";
	}
	
	@RequestMapping("/update")
	public @ResponseBody Map<String, Object> update(ModelMap modelMap){

		String paramName = null;
		String paramValue = null;
		String first_id = null;
		String second_id = null;
		String order = null;
		String[] names = null;
		
		Map<String, Object> map  = new HashMap<String, Object>();
		
		try{
			List<EIndicator> indicatorList = this.indicatorService.list();
			List<EIndicatorImportance> importanceList = this.indicatorimportanceService.list();
			
			Map<String, EIndicator> indicatorMaps = new HashMap<String, EIndicator>();
			for(EIndicator indicator : indicatorList){
				indicatorMaps.put(indicator.getId().toString(), indicator);
			}
			
			UserInfo userInfo = (UserInfo)this.session.getAttribute("userInfo");
			TUser user = userService.load(userInfo.getUserId());
			
			List<EIndicatorImportance> impoList = new ArrayList<EIndicatorImportance>();
			
			List<EIndicatorImportanceItem> impoItemList = new ArrayList<EIndicatorImportanceItem>();
			
			Long categoryId = null;
			
			Enumeration<String> enu = this.request.getParameterNames();  
			while(enu.hasMoreElements()){
				paramName = enu.nextElement();  
				paramValue = this.request.getParameter(paramName);
				if(StringUtil.isNotBlank(paramValue)){
					names = paramName.split("_");
					first_id = names[1];
					second_id = names[2];
					order = names[3];
					
					EIndicatorImportance impo = new EIndicatorImportance();
					//impo.setRating(Double.parseDouble(paramValue));
					impo.setOrder(Integer.parseInt(order));
					impo.setFirst(indicatorMaps.get(first_id));
					impo.setSecond(indicatorMaps.get(second_id));
					impo.setParent(impo.getFirst().getParent());
					impo.setCategory(indicatorMaps.get(first_id).getCategory());
					if(categoryId == null){
						categoryId = impo.getCategory().getId();
					}
					impoList.add(impo);
				}
			}
			
			List<EIndicatorImportance> addedList = new ArrayList<EIndicatorImportance>();
			if(!impoList.isEmpty()){
				boolean existing = false;
				for(EIndicatorImportance impo : impoList){
					existing = false;
					for(EIndicatorImportance dbRecord : importanceList){
						if(dbRecord.getFirst().getId().equals(impo.getFirst().getId()) && dbRecord.getSecond().getId().equals(impo.getSecond().getId())){
							existing = true;
							break;
						}
					}
					if(!existing){
						this.indicatorimportanceService.create(impo);
						addedList.add(impo);
					}
				}
				
				for(EIndicatorImportance dbRecord : importanceList){
					if(dbRecord.getCategory().getId().equals(categoryId)){
						existing = false;
						for(EIndicatorImportance impo : impoList){
							if(dbRecord.getFirst().getId().equals(impo.getFirst().getId()) && dbRecord.getSecond().getId().equals(impo.getSecond().getId())){
								existing = true;
								break;
							}
						}
						if(!existing){
							this.indicatorimportanceService.delete(dbRecord);
						}
					}
				}
			}
			
			importanceList.addAll(addedList);
			enu = this.request.getParameterNames();  
			while(enu.hasMoreElements()){
				paramName = enu.nextElement();  
				paramValue = this.request.getParameter(paramName);
				if(StringUtil.isNotBlank(paramValue)){
					names = paramName.split("_");
					first_id = names[1];
					second_id = names[2];
					order = names[3];
					
					EIndicatorImportanceItem impoItem = new EIndicatorImportanceItem();
					for(EIndicatorImportance dbRecord : importanceList){
						if(dbRecord.getFirst().getId().toString().equals(first_id) && dbRecord.getSecond().getId().toString().equals(second_id)){
							impoItem.setImportance(dbRecord);
							break;
						}
					}
					
					impoItem.setUser(user);
					impoItem.setRating(Double.parseDouble(paramValue));
					impoItemList.add(impoItem);
					//this.indicatorimportanceService.create(impo);
				}
			}
			
			if(!impoItemList.isEmpty()){
				this.indicatorimportanceitemService.delete(user.getId(), categoryId);
				this.indicatorimportanceitemService.create(impoItemList);
			}
			
			this.indicatorimportanceService.updateRating();
			adjustWeight();
			
			map.put("status", "success");  
		}
		catch(Exception e){
			e.printStackTrace();
			map.put("status", "fail");  
		}
		
		return map;
	}
	
	
	private void getList(List<EIndicator> ilist, List<IndicatorImpoSet> setList){
		if(ilist != null && !ilist.isEmpty()){
			
			List<EIndicator> copyList = new ArrayList<EIndicator>();
			List<IndicatorPojo> pojoList = new ArrayList<IndicatorPojo>();
			for(EIndicator indicator : ilist){
				if(!Const.Mandatory.YES.equals(indicator.getMandatory())){
					copyList.add(indicator);
					pojoList.add(indicatorTransformer.convert(indicator));
				}
			}
			
			if(!pojoList.isEmpty()){
				SortUtil.sort(pojoList, "score", "desc");
				
				IndicatorImpoSet set = new IndicatorImpoSet();
				List<IndicatorImpoPojo> impoList = new ArrayList<IndicatorImpoPojo>();
				for(int i=0; i<pojoList.size()-1; i++){
					IndicatorImpoPojo impo = new IndicatorImpoPojo();
					impo.setFirstId(pojoList.get(i).getId());
					impo.setFirstName(pojoList.get(i).getName());
					impo.setSecondId(pojoList.get(i+1).getId());
					impo.setSecondName(pojoList.get(i+1).getName());
//					for(EIndicatorImportance importance : impotantceList){
//						if(importance.getFirst().getId().equals(impo.getFirstId()) && importance.getSecond().getId().equals(impo.getSecondId())){
//							impo.setRating(importance.getRating());
//							break;
//						}
//					}
					
					impoList.add(impo);
				}
				set.setLevel(pojoList.get(0).getLevel());
				set.setLabel(getLable(copyList.get(0)));
				set.setList(impoList);
				setList.add(set);
				
				for(EIndicator indicator : copyList){
					getList(indicator.getChildren(), setList);
				}
			}
			
		}
	}
	
	private void setRating(List<IndicatorImpoSet> setList, List<EIndicatorImportance> impotantceList, boolean forAll, Long userId){
		for(EIndicatorImportance importance : impotantceList){
			for(IndicatorImpoSet set : setList){
				for(IndicatorImpoPojo impo : set.getList()){
					if(importance.getFirst().getId().equals(impo.getFirstId()) && importance.getSecond().getId().equals(impo.getSecondId())){
						if(forAll){
							impo.setRating(importance.getRating());
							for(EIndicatorImportanceItem item : importance.getItems()){
								IndicatorUserImpoPojo userScore = new IndicatorUserImpoPojo();
								userScore.setRating(item.getRating());
								impo.addUserScore(userScore);
							}
							
							break;
						}
						else{
							for(EIndicatorImportanceItem item : importance.getItems()){
								if(item.getUser().getId().equals(userId)){
									impo.setRating(item.getRating());
									break;
								}
							}
						}
						
					}
				}
			}
		}
	}
	
	private void setRating4All(List<IndicatorImpoSet> setList, List<EIndicatorImportance> impotantceList, List<Long> userIds){
		for(EIndicatorImportance importance : impotantceList){
			for(IndicatorImpoSet set : setList){
				for(IndicatorImpoPojo impo : set.getList()){
					if(importance.getFirst().getId().equals(impo.getFirstId()) && importance.getSecond().getId().equals(impo.getSecondId())){
						impo.setRating(importance.getRating());
						for(Long userId : userIds){
							IndicatorUserImpoPojo userScore = new IndicatorUserImpoPojo();
							for(EIndicatorImportanceItem item : importance.getItems()){
								if(userId.equals(item.getUser().getId())){
									userScore.setRating(item.getRating());
									break;
								}
							}
							impo.addUserScore(userScore);
						}
						break;
					}
				}
			}
		}
	}
	
	private String getLable(EIndicator indicator){
		if(indicator.getParent() == null){
			return indicator.getName();
		}
		return getLable(indicator.getParent());
	}
	
	private void adjustWeight(){
		List<EIndicatorImportance> list = this.indicatorimportanceService.list();
		List<EIndicator> indicatorList = indicatorService.list();
		List<Map<String, Object>> ratingList = this.indicatorimportanceService.queryListMap();
		
		Double rating = null;
		List<IndicatorImpoPojo> impoList = new ArrayList<IndicatorImpoPojo>();
		for(EIndicatorImportance impo : list){
			IndicatorImpoPojo pojo = this.indicatorImpoTransformer.convert(impo);
			for(Map<String, Object> ratingMap : ratingList){
				if(impo.getId().toString().equals(ratingMap.get("id").toString())){
					rating = (Double)ratingMap.get("rating");
					break;
				}
			}
			pojo.setRating(rating);
			impoList.add(pojo);
		}
		
		//group the importance by parent id
		Map<Long, List<IndicatorImpoPojo>> map = new HashMap<Long, List<IndicatorImpoPojo>>();
		Long parentId = null;
		for(IndicatorImpoPojo impo : impoList){
			if(impo.getParentId() == null){
				parentId = 0L;
			}
			else{
				parentId = impo.getParentId();
			}
			List<IndicatorImpoPojo> mapList = map.get(parentId);
			if(mapList == null){
				mapList = new ArrayList<IndicatorImpoPojo>();
				map.put(parentId, mapList);
			}
			mapList.add(impo);
		}
		
		
		//Map<Long, Double> lastWeightMap = new HashMap<Long, Double>();
		Map<Long, Double> weightMap = new HashMap<Long, Double>();
		
		for(List<IndicatorImpoPojo> is : map.values()){
			//SortUtil.sort(is, "order", "desc");
			SortUtil.sort(is, "order");
			
			//EIndicatorImportance lastImpo = is.get(0);
			//Long lastParentId = (lastImpo.getSecond().getParent() == null) ? 0l : lastImpo.getSecond().getParent().getId();
			//Double lastRating = lastImpo.getRating();
			
			Double lastWeight = 0d;
			
			lastWeight = calLastWeight(is, is.size()-1, lastWeight);
			
			//lastWeightMap.put(lastParentId, lastWeight);
			
			calAllWeight(is, lastWeight, weightMap);
		}
		
		for(Long key : weightMap.keySet()){
			for(EIndicator indicator : indicatorList){
				if(indicator.getId().equals(key)){
					if(weightMap.get(key) != null){
						BigDecimal decimal = new BigDecimal(new java.text.DecimalFormat("#.0000").format(weightMap.get(key))); 
						indicator.setWeight(decimal);
						this.indicatorService.update(indicator);
					}

					
				}
			}
		}
	}
	
	/**
	 * 计算最后一个的权重
	 * @param list
	 * @param index
	 * @param weight
	 */
	private Double calLastWeight(List<IndicatorImpoPojo> list, Integer index, Double weight){
		Double result = weight;
		if(index >= 0){
			Double w = 1d;
			for(int i=index; i>=0; i--){
				Double rating = list.get(i).getRating();
				w *= rating;
			}
			result += w;
			return calLastWeight(list, index-1, result);
		}	
		return 1/(1+result);
	}
	
	private void calAllWeight(List<IndicatorImpoPojo> list, Double lastWeight, Map<Long, Double> map){
		SortUtil.sort(list, "order", "desc");
		Double[] weights = new Double[list.size()+1];
		for(int i=0; i<list.size(); i++){
			if(i == 0){
				weights[0] = lastWeight;
				if(list.size() == 1){
					weights[1] = 1 - weights[0];
				}
			}
			else if(i == list.size()-1){
				weights[i] = weights[i-1]*list.get(i-1).getRating();
				weights[i+1] = weights[i]*list.get(i).getRating();
			}
			else{
				weights[i] = weights[i-1]*list.get(i-1).getRating();
			}
		}
		
		for(int i=0; i<weights.length; i++){
			if(i == weights.length-1){
				map.put(list.get(i-1).getFirstId(), weights[i]);
			}
			else{
				map.put(list.get(i).getSecondId(), weights[i]);
			}
		}
	}
}
