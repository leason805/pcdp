package com.boxun.pcdp.estimate.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boxun.estms.util.StringUtil;
import com.boxun.pcdp.archive.entity.ACertCategory;
import com.boxun.pcdp.archive.entity.AExpCategory;
import com.boxun.pcdp.archive.service.ICertCategoryService;
import com.boxun.pcdp.archive.service.IExpCategoryService;
import com.boxun.pcdp.basic.controller.BaseController;
import com.boxun.pcdp.estimate.entity.EIndicator;
import com.boxun.pcdp.estimate.entity.EIndicatorCategory;
import com.boxun.pcdp.estimate.pojo.IndicatorCategoryPojo;
import com.boxun.pcdp.estimate.service.IIndicatorCategoryService;
import com.boxun.pcdp.estimate.service.IIndicatorService;
import com.boxun.pcdp.knowledge.entity.KSection;
import com.boxun.pcdp.knowledge.service.ISectionService;

@Controller
@RequestMapping("/system/estimate/indicator")
public class IndicatorController extends BaseController{

private static final Logger LOGGER = Logger.getLogger(IndicatorController.class);
	
	@Autowired
	private IIndicatorService indicatorService;
	
	@Autowired
	private ICertCategoryService certcategoryService;
	
	@Autowired
	private IExpCategoryService expcategoryService;
	
	@Autowired
	private ISectionService sectionService;
	
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
		
		//modelMap.addAttribute("catelist", catelist);
		modelMap.addAttribute("list", list);
		return "/estimate/indicator/list";
	}
	
	@RequestMapping("/pdf")
	public String pdf(ModelMap modelMap){
		modelMap.put("FILE_NAME", "test.pdf");
		return "mypdf";
	}
	
	@RequestMapping("/show/{id}")
	public String show(ModelMap modelMap, @PathVariable Long id, Long categoryId){
		LOGGER.info("Get show id: " + id);
		if(categoryId != null){
			EIndicatorCategory category = indicatorcategoryService.load(categoryId);
			modelMap.addAttribute("category", category);
		}
		if(id != null){
			EIndicator model = indicatorService.load(id);
			modelMap.addAttribute("model", model);
		}
		
		return "/estimate/indicator/show";
	}
	
	@RequestMapping("/listdata")
	public @ResponseBody Map<String, Object> listData(){
		LOGGER.info("Get list data.");
		List<EIndicator> listdata = indicatorService.list();
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("sEcho", 1);  
		map.put("iTotalRecords", listdata.size());  
        map.put("iTotalDisplayRecords", listdata.size());  
		map.put("aaData", listdata);
		
		return map;
	}
	
	@RequestMapping("/create")
	public String create(ModelMap modelMap, @RequestParam Long parentId, @RequestParam Long categoryId, EIndicator model){

		if(StringUtil.isNotBlank(model.getName()) && model.getNumber() != null){
			if(categoryId != null && categoryId > 0){
				EIndicatorCategory category = indicatorcategoryService.load(categoryId);
				model.setCategory(category);
			}
			
			if(parentId != null && parentId > 0){
				EIndicator parent = indicatorService.load(parentId);
				model.setParent(parent);
				model.setCategory(parent.getCategory());
				model.setPsequence(parent.getSequence());
				model.setSequence(parent.getSequence() + "." + model.getNumber());
				model.setLevel(parent.getLevel() + 1);
			
			}
			else{
				model.setSequence(model.getNumber()+"");
			}
			
			indicatorService.create(model);
			
			modelMap.addAttribute("refresh", "page");
			modelMap.addAttribute("url", "/system/estimate/indicator/list");
		}
		
		//indicatorService.create(model);
		return "/success";
	}
	
	@RequestMapping("/update/{id}")
	public String update(ModelMap modelMap, @PathVariable Long id, EIndicator model){
		LOGGER.info("Get update id: " + id);
		
		if(id != null){
			EIndicator dbRecord = indicatorService.load(id);
			
			if(dbRecord.getParent() != null){
				dbRecord.setSequence(dbRecord.getParent().getSequence() + "." + model.getNumber());
			}
			else{
				dbRecord.setSequence(model.getNumber()+"");
			}
			
			dbRecord.setNumber(model.getNumber());
			dbRecord.setName(model.getName());
			dbRecord.setIndicatorType(model.getIndicatorType());
			dbRecord.setMandatory(model.getMandatory());
			dbRecord.setDescription(model.getDescription());
			
			indicatorService.update(dbRecord);
			
			modelMap.addAttribute("refresh", "page");
			modelMap.addAttribute("url", "/system/estimate/indicator/list");
		}
		return "/success";
	}
	
	@RequestMapping("/add/{id}")
	public String add(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get id: " + id);
		if(id != null){
			EIndicator model = indicatorService.load(id);
			modelMap.addAttribute("model", model);
		}
		return "/estimate/indicator/add";
	}
	
	@RequestMapping("/del/{id}")
	public String del(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Del id: " + id);
		if(id != null){
			EIndicator index = indicatorService.load(id);
			modelMap.addAttribute("index", index);
		}
		return "/estimate/indicator/delete";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Delete id: " + id);
		if(id != null){
			EIndicator index = indicatorService.load(id);
			indicatorService.delete(index);
			
			modelMap.addAttribute("refresh", "page");
			modelMap.addAttribute("url", "/system/estimate/indicator/list");
		}
		
		
		return "/admin/success";
	}
	
	
	@RequestMapping("/associate/{id}")
	public String associate(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get show id: " + id);
		if(id != null){
			EIndicator model = indicatorService.load(id);
			modelMap.addAttribute("model", model);
		}
		
		List<ACertCategory> cateList = certcategoryService.listTops();
		modelMap.addAttribute("cateList", cateList);
		
		List<AExpCategory> expList = expcategoryService.listTops();
		modelMap.addAttribute("expList", expList);
		
		List<KSection> sections = this.sectionService.list();
		modelMap.addAttribute("sections", sections);
		
		return "/estimate/indicator/associate";
	}
	
	
	@RequestMapping("/save/{id}")
	public String save(ModelMap modelMap, @PathVariable Long id){
		modelMap.addAttribute("refresh", "page");
		modelMap.addAttribute("url", "/system/estimate/indicator/list");
		return "/success";
	}
}
