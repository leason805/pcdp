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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boxun.estms.util.StringUtil;
import com.boxun.pcdp.basic.controller.BaseController;
import com.boxun.pcdp.estimate.entity.EIndicator;
import com.boxun.pcdp.estimate.entity.EIndicatorCategory;
import com.boxun.pcdp.estimate.pojo.IndicatorCategoryPojo;
import com.boxun.pcdp.estimate.service.IIndicatorCategoryService;
import com.boxun.pcdp.estimate.service.IIndicatorService;

@Controller
@RequestMapping("/system/estimate/indicatorweight")
public class IndicatorWeightController extends BaseController{

	private static final Logger LOGGER = Logger.getLogger(IndicatorWeightController.class);
	
	@Autowired
	private IIndicatorService indicatorService;
	
	@Autowired
	private IIndicatorCategoryService indicatorcategoryService;
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap){
		
		List<IndicatorCategoryPojo> list = new ArrayList<IndicatorCategoryPojo>();
		
		List<EIndicatorCategory> catelist = this.indicatorcategoryService.listActive();
		
		for(EIndicatorCategory cat : catelist){
			IndicatorCategoryPojo pojo = new IndicatorCategoryPojo();
			pojo.setId(cat.getId());
			pojo.setName(cat.getName());
			List<EIndicator> tops = indicatorService.listTops4Category(cat.getId());
			pojo.setList(tops);
			
			list.add(pojo);
		}
		
		
//		List<EIndicator> list = indicatorService.listTops();
		modelMap.addAttribute("list", list);
		return "/estimate/indicatorweight/list";
	}
	
	@RequestMapping("/show/{id}")
	public String show(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get show id: " + id);
		List<EIndicator> list = null;
		if(id != null){
			EIndicator model = indicatorService.load(id);
			if(model.getParent() == null){
				list = indicatorService.listTops4Category(model.getCategory().getId());
			}
			else{
				list = model.getParent().getChildren();
			}
			modelMap.addAttribute("list", list);
		}
		
		return "/estimate/indicatorweight/show";
	}
	
	@RequestMapping("/update")
	public String update(ModelMap modelMap){
		String paramName = null;
		String paramValue = null;
		String id = null;
		
		List<EIndicator> list = indicatorService.list();
		Map<String, EIndicator> indMaps = new HashMap<String, EIndicator>();
		for(EIndicator indicator : list){
			indMaps.put(indicator.getId().toString(), indicator);
		}
		
		EIndicator indicator = null;
		Enumeration<String> enu = this.request.getParameterNames();  
		while(enu.hasMoreElements()){  
			paramName = enu.nextElement();  
			paramValue = this.request.getParameter(paramName);
			
			id = paramName.split("_")[1];
			indicator = indMaps.get(id);
			if(indicator != null){
				if(StringUtil.isNotBlank(paramValue)){
					indicator.setAdjustWeight(BigDecimal.valueOf(Double.valueOf(paramValue)));
				}
				else{
					indicator.setAdjustWeight(null);
				}
				this.indicatorService.update(indicator);
			}
		}
		
		modelMap.addAttribute("refresh", "page");
		modelMap.addAttribute("url", "/system/estimate/indicatorweight/list");

		return "/success";
	}
}
