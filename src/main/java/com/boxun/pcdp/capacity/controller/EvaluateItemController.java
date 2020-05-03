package com.boxun.pcdp.capacity.controller;

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
import com.boxun.pcdp.basic.controller.BaseController;
import com.boxun.pcdp.estimate.entity.EIndicator;
import com.boxun.pcdp.estimate.entity.EIndicatorCategory;
import com.boxun.pcdp.estimate.entity.EIndicatorItem;
import com.boxun.pcdp.estimate.service.IIndicatorCategoryService;
import com.boxun.pcdp.estimate.service.IIndicatorItemService;
import com.boxun.pcdp.estimate.service.IIndicatorService;

@Controller
@RequestMapping("/system/capacity/item")
public class EvaluateItemController extends BaseController {
	
	private static final Logger LOGGER = Logger.getLogger(EvaluateItemController.class);
	
	@Autowired
	private IIndicatorCategoryService indicatorcategoryService;
	
	@Autowired
	private IIndicatorService indicatorService;
	
	@Autowired
	private IIndicatorItemService indicatoritemService;

	@RequestMapping("/list")
	public String list(ModelMap modelMap){
		return "/capacity/item/list";
	}
	
	@RequestMapping("/show/{id}")
	public String show(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get show id: " + id);
		if(id != null){
			//EIndicatorCategory model = indicatorcategoryService.load(id);
			//modelMap.addAttribute("model", model);
		}
		modelMap.addAttribute("id", id);
		return "/capacity/item/show";
	}
	
	@RequestMapping("/listdata")
	public @ResponseBody Map<String, Object> listData(){
		LOGGER.info("Get list data.");
		List<EIndicatorCategory> listdata = indicatorcategoryService.listActive();
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("sEcho", 1);  
		map.put("iTotalRecords", listdata.size());  
        map.put("iTotalDisplayRecords", listdata.size());  
		map.put("aaData", listdata);
		
		return map;
	}
	
	@RequestMapping("/nav/{id}")
	public String nav(ModelMap modelMap, @PathVariable Long id){
		List<EIndicator> list = this.indicatorService.listTops4Category(id, Const.IndicatorType.CAPACITY);
		modelMap.addAttribute("list", list);
		return "/capacity/item/nav";
	}
	
	@RequestMapping("/content/{id}")
	public String content(ModelMap modelMap, @PathVariable Long id){
		List<EIndicatorItem> list = this.indicatoritemService.list4Indicator(id);
		modelMap.addAttribute("id", id);
		modelMap.addAttribute("list", list);
		return "/capacity/item/content";
	}
	
	@RequestMapping("/add/{id}")
	public String add(ModelMap modelMap, @PathVariable Long id){
		modelMap.addAttribute("id", id);
		return "/capacity/item/add";
	}
	
	@RequestMapping("/createitem")
	public String createitem(ModelMap modelMap, EIndicatorItem model, Long indicatorId){
		if(indicatorId != null && indicatorId > 0){
			EIndicator indicator = this.indicatorService.load(indicatorId);
			model.setIndicator(indicator);
		}
		this.indicatoritemService.create(model);
		
		return "redirect:/system/capacity/item/content/"+indicatorId+".htm";
	}
	
	@RequestMapping("/edit/{id}")
	public String edit(ModelMap modelMap, @PathVariable Long id){
		EIndicatorItem model = this.indicatoritemService.load(id);
		modelMap.addAttribute("model", model);
		return "/capacity/item/edit";
	}
	
	@RequestMapping("/updateitem/{id}")
	public String updateitem(ModelMap modelMap, EIndicatorItem model, @PathVariable Long id){

		EIndicatorItem dbRecord = this.indicatoritemService.load(id);
		dbRecord.setSequence(model.getSequence());
		dbRecord.setDescription(model.getDescription());
		this.indicatoritemService.update(dbRecord);
		
		return "redirect:/system/capacity/item/content/"+dbRecord.getIndicator().getId()+".htm";
	}
	
	@RequestMapping("/delitem/{id}")
	public String delitem(ModelMap modelMap, EIndicatorItem model, @PathVariable Long id){

		Long indicatorId = 0l;
		EIndicatorItem dbRecord = this.indicatoritemService.load(id);
		if(dbRecord != null){
			indicatorId = dbRecord.getIndicator().getId();
		}
		this.indicatoritemService.delete(dbRecord);
		
		return "redirect:/system/capacity/item/content/"+indicatorId+".htm";
	}
}
