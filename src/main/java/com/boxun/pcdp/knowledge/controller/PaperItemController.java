package com.boxun.pcdp.knowledge.controller;

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

import com.boxun.pcdp.basic.controller.BaseController;
import com.boxun.pcdp.knowledge.entity.KPaperItem;
import com.boxun.pcdp.knowledge.service.IPaperItemService;

@Controller
@RequestMapping("/system/knowledge/paperitem")
public class PaperItemController extends BaseController{

private static final Logger LOGGER = Logger.getLogger(PaperItemController.class);
	
	@Autowired
	private IPaperItemService paperitemService;
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap){
		return "/knowledge/paperitem/list";
	}
	
	@RequestMapping("/show/{id}")
	public String show(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get show id: " + id);
		if(id != null){
			KPaperItem model = paperitemService.load(id);
			modelMap.addAttribute("model", model);
		}
		
		return "/knowledge/paperitem/show";
	}
	
	@RequestMapping("/listdata")
	public @ResponseBody Map<String, Object> listData(){
		LOGGER.info("Get list data.");
		List<KPaperItem> listdata = paperitemService.list();
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("sEcho", 1);  
		map.put("iTotalRecords", listdata.size());  
        map.put("iTotalDisplayRecords", listdata.size());  
		map.put("aaData", listdata);
		
		return map;
	}
	
	@RequestMapping("/create")
	public String create(ModelMap modelMap, KPaperItem model){

		paperitemService.create(model);
		return "/success";
	}
	
	@RequestMapping("/update/{id}")
	public String update(ModelMap modelMap, @PathVariable Long id, KPaperItem model){
		LOGGER.info("Get update id: " + id);
		LOGGER.info("Update id: " + id);
		if(id != null){
			KPaperItem dbRecord = paperitemService.load(id);
//			dbRecord.setDescription(model.getDescription());
			paperitemService.update(dbRecord);
		}
		return "/success";
	}
}
