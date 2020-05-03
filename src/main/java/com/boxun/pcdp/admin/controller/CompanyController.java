package com.boxun.pcdp.admin.controller;

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
import com.boxun.pcdp.admin.entity.TCompany;
import com.boxun.pcdp.admin.service.ICompanyService;

@Controller
@RequestMapping("/system/admin/company")
public class CompanyController extends BaseController{

private static final Logger LOGGER = Logger.getLogger(CompanyController.class);
	
	@Autowired
	private ICompanyService companyService;
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap){
		return "/admin/company/list";
	}
	
	@RequestMapping("/show/{id}")
	public String show(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get show id: " + id);
		if(id != null){
			TCompany model = companyService.load(id);
			modelMap.addAttribute("model", model);
		}
		
		return "/admin/company/show";
	}
	
	@RequestMapping("/listdata")
	public @ResponseBody Map<String, Object> listData(){
		LOGGER.info("Get list data.");
		List<TCompany> listdata = companyService.list();
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("sEcho", 1);  
		map.put("iTotalRecords", listdata.size());  
        map.put("iTotalDisplayRecords", listdata.size());  
		map.put("aaData", listdata);
		
		return map;
	}
	
	@RequestMapping("/create")
	public String create(ModelMap modelMap, TCompany model){

		companyService.create(model);
		return "/success";
	}
	
	@RequestMapping("/update/{id}")
	public String update(ModelMap modelMap, @PathVariable Long id, TCompany model){
		LOGGER.info("Get update id: " + id);
		LOGGER.info("Update id: " + id);
		if(id != null){
			TCompany dbRecord = companyService.load(id);
			dbRecord.setName(model.getName());
			dbRecord.setDescription(model.getDescription());
			companyService.update(dbRecord);
		}
		return "/success";
	}
}
