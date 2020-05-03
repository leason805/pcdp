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
import org.springframework.web.bind.annotation.ResponseBody;

import com.boxun.estms.entity.Const;
import com.boxun.estms.util.DateUtil;
import com.boxun.estms.util.StringUtil;
import com.boxun.pcdp.archive.entity.ACertCategory;
import com.boxun.pcdp.archive.entity.AExpCategory;
import com.boxun.pcdp.archive.entity.APositionCategory;
import com.boxun.pcdp.archive.service.ICertCategoryService;
import com.boxun.pcdp.archive.service.IExpCategoryService;
import com.boxun.pcdp.archive.service.IPositionCategoryService;
import com.boxun.pcdp.basic.controller.BaseController;
import com.boxun.pcdp.estimate.entity.EAssociateItem;
import com.boxun.pcdp.estimate.entity.EIndicator;
import com.boxun.pcdp.estimate.entity.EIndicatorAssociate;
import com.boxun.pcdp.estimate.pojo.IndicatorAssociatePojo;
import com.boxun.pcdp.estimate.service.IIndicatorAssociateService;
import com.boxun.pcdp.estimate.service.IIndicatorService;
import com.boxun.pcdp.estimate.transformer.IndicatorAssociateTransformer;
import com.boxun.pcdp.knowledge.entity.KSection;
import com.boxun.pcdp.knowledge.service.ISectionService;

@Controller
@RequestMapping("/system/estimate/indicatorassociate")
public class IndicatorAssociateController extends BaseController{

private static final Logger LOGGER = Logger.getLogger(IndicatorAssociateController.class);
	
	@Autowired
	private IIndicatorAssociateService indicatorassociateService;
	
	@Autowired
	private IndicatorAssociateTransformer indicatorAssociateTransformer;
	
	@Autowired
	private IIndicatorService indicatorService;
	
	@Autowired
	private ICertCategoryService certcategoryService;
	
	@Autowired
	private IExpCategoryService expcategoryService;
	
	@Autowired
	private ISectionService sectionService;
	
	@Autowired
	private IPositionCategoryService positioncategoryService;
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap){
		return "/estimate/indicatorassociate/list";
	}
	
	@RequestMapping("/show/{id}")
	public String show(ModelMap modelMap, @PathVariable Long id){
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
		
		List<APositionCategory> positions = this.positioncategoryService.listByType(Const.PositionCategoryType.POSITIONLEVEL);
		modelMap.addAttribute("positions", positions);
		
		List<APositionCategory> techs = this.positioncategoryService.listByType(Const.PositionCategoryType.TECHLEVEL);
		modelMap.addAttribute("techs", techs);
		
		return "/estimate/indicatorassociate/show";
	}
	
	@RequestMapping("/listdata")
	public @ResponseBody Map<String, Object> listData(){
		LOGGER.info("Get list data.");
		List<EIndicatorAssociate> listdata = indicatorassociateService.list();
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("sEcho", 1);  
		map.put("iTotalRecords", listdata.size());  
        map.put("iTotalDisplayRecords", listdata.size());  
		map.put("aaData", listdata);
		
		return map;
	}
	
	@RequestMapping("/create")
	public String create(ModelMap modelMap, IndicatorAssociatePojo model){
		EIndicatorAssociate associate = this.createAssociate(model);
		if(associate != null ){
			indicatorassociateService.create(associate);
		}
		modelMap.addAttribute("refresh", "page");
		modelMap.addAttribute("url", "/system/estimate/indicator/list");
		return "/success";
	}
	
	@RequestMapping("/update/{id}")
	public String update(ModelMap modelMap, @PathVariable Long id, IndicatorAssociatePojo model){
		LOGGER.info("Get update id: " + id);
		LOGGER.info("Update id: " + id);
		if(id != null){
			EIndicatorAssociate dbRecord = indicatorassociateService.load(id);
			this.updateAssociate(model, dbRecord);
			indicatorassociateService.update(dbRecord);
		}
		modelMap.addAttribute("refresh", "page");
		modelMap.addAttribute("url", "/system/estimate/indicator/list");
		return "/success";
	}
	
	private EIndicatorAssociate createAssociate(IndicatorAssociatePojo model){
		EIndicatorAssociate associate = null;
		List<EAssociateItem> items = null;
		if(Const.AssociateType.CERTIFICATION.equals(model.getAssociateType())){
			if(StringUtil.isNotBlank(model.getItems())){
				List<Long> idList = StringUtil.split4Long(model.getItems(), Const.COMMA_DELIMITER);
				EIndicator indicator = this.indicatorService.load(model.getIndicator());
				items = new ArrayList<EAssociateItem>();
				associate = new EIndicatorAssociate();
				associate.setIndicator(indicator);
				associate.setAssociateType(model.getAssociateType());
				for(Long cId : idList){
					EAssociateItem item = new EAssociateItem();
					item.setItemId(cId);
					item.setAssociate(associate);
					items.add(item);
				}
				associate.setItems(items);
			}
		}
		else if(Const.AssociateType.QUALIFICATION.equals(model.getAssociateType())){
			EIndicator indicator = this.indicatorService.load(model.getIndicator());
			associate = new EIndicatorAssociate();
			associate.setIndicator(indicator);
			associate.setAssociateType(model.getAssociateType());
			
			items = new ArrayList<EAssociateItem>();
			
			EAssociateItem item1 = new EAssociateItem();
			item1.setScore(1);
			item1.setAssociate(associate);
			if(StringUtil.isNotBlank(model.getVal1_1())){
				item1.setVal1(model.getVal1_1());
			}
			if(StringUtil.isNotBlank(model.getVal2_1())){
				item1.setVal2(model.getVal2_1());
			}
			items.add(item1);
			
			EAssociateItem item2 = new EAssociateItem();
			item2.setScore(2);
			item2.setAssociate(associate);
			if(StringUtil.isNotBlank(model.getVal1_2())){
				item2.setVal1(model.getVal1_2());
			}
			if(StringUtil.isNotBlank(model.getVal2_2())){
				item2.setVal2(model.getVal2_2());
			}
			items.add(item2);
			
			EAssociateItem item3 = new EAssociateItem();
			item3.setScore(3);
			item3.setAssociate(associate);
			if(StringUtil.isNotBlank(model.getVal1_3())){
				item3.setVal1(model.getVal1_2());
			}
			if(StringUtil.isNotBlank(model.getVal2_3())){
				item3.setVal2(model.getVal2_3());
			}
			items.add(item3);
			
			EAssociateItem item4 = new EAssociateItem();
			item4.setScore(4);
			item4.setAssociate(associate);
			if(StringUtil.isNotBlank(model.getVal1_2())){
				item4.setVal1(model.getVal1_4());
			}
			if(StringUtil.isNotBlank(model.getVal2_2())){
				item4.setVal2(model.getVal2_4());
			}
			items.add(item4);
			
			associate.setItems(items);
		}
		else{
			//if(StringUtil.isNotBlank(model.getItems())){
				EIndicator indicator = this.indicatorService.load(model.getIndicator());
				associate = new EIndicatorAssociate();
				associate.setIndicator(indicator);
				associate.setAssociateType(model.getAssociateType());
				if(Const.AssociateType.KNOWLEDGE.equals(model.getAssociateType()) || Const.AssociateType.TRAINING.equals(model.getAssociateType())){
					if(model.getStartDate() != null){
						associate.setStartDate(DateUtil.formatDate(model.getStartDate(), com.boxun.estms.util.Const.DATE_FORMAT_2));
					}
					if(model.getEndDate() != null){
						associate.setEndDate(DateUtil.formatDate(model.getEndDate(), com.boxun.estms.util.Const.DATE_FORMAT_2));
					}
				}
				
				items = new ArrayList<EAssociateItem>();
				
				EAssociateItem item1 = new EAssociateItem();
				item1.setScore(1);
				item1.setAssociate(associate);
				if(StringUtil.isNotBlank(model.getItems())){
					item1.setItemId(Long.valueOf(model.getItems()));
				}
				item1.setFormula(model.getFormula1());
				item1.setBenchmark(Double.valueOf(model.getBenchmark1()));
				items.add(item1);
				
				EAssociateItem item2 = new EAssociateItem();
				item2.setAssociate(associate);
				item2.setScore(2);
				if(StringUtil.isNotBlank(model.getItems())){
					item2.setItemId(Long.valueOf(model.getItems()));
				}
				item2.setFormula(model.getFormula2());
				item2.setBenchmark(Double.valueOf(model.getBenchmark2()));
				items.add(item2);
				
				EAssociateItem item3 = new EAssociateItem();
				item3.setAssociate(associate);
				item3.setScore(3);
				if(StringUtil.isNotBlank(model.getItems())){
					item3.setItemId(Long.valueOf(model.getItems()));
				}
				item3.setFormula(model.getFormula3());
				item3.setBenchmark(Double.valueOf(model.getBenchmark3()));
				items.add(item3);
				
				EAssociateItem item4 = new EAssociateItem();
				item4.setAssociate(associate);
				item4.setScore(4);
				if(StringUtil.isNotBlank(model.getItems())){
					item4.setItemId(Long.valueOf(model.getItems()));
				}
				item4.setFormula(model.getFormula4());
				item4.setBenchmark(Double.valueOf(model.getBenchmark4()));
				items.add(item4);
				
				associate.setItems(items);
			//}
		}
		return associate;
	}
	
	private void updateAssociate(IndicatorAssociatePojo model, EIndicatorAssociate dbRecord){
		dbRecord.setAssociateType(model.getAssociateType());
		if(Const.AssociateType.KNOWLEDGE.equals(model.getAssociateType()) || Const.AssociateType.TRAINING.equals(model.getAssociateType())){
			if(model.getStartDate() != null){
				dbRecord.setStartDate(DateUtil.formatDate(model.getStartDate(), com.boxun.estms.util.Const.DATE_FORMAT_2));
			}
			if(model.getEndDate() != null){
				dbRecord.setEndDate(DateUtil.formatDate(model.getEndDate(), com.boxun.estms.util.Const.DATE_FORMAT_2));
			}
		}
		if(dbRecord.getItems() != null){
			dbRecord.getItems().clear();
		}
		
		List<EAssociateItem> items = null;
		if(Const.AssociateType.CERTIFICATION.equals(model.getAssociateType())){
			if(StringUtil.isNotBlank(model.getItems())){
				List<Long> idList = StringUtil.split4Long(model.getItems(), Const.COMMA_DELIMITER);
				EIndicator indicator = this.indicatorService.load(model.getIndicator());
				items = new ArrayList<EAssociateItem>();
				for(Long cId : idList){
					EAssociateItem item = new EAssociateItem();
					item.setItemId(cId);
					item.setAssociate(dbRecord);
					items.add(item);
				}
				dbRecord.setItems(items);
			}
		}
		else if(Const.AssociateType.QUALIFICATION.equals(model.getAssociateType())){

			items = new ArrayList<EAssociateItem>();
			
			EAssociateItem item1 = new EAssociateItem();
			item1.setScore(1);
			item1.setAssociate(dbRecord);
			if(StringUtil.isNotBlank(model.getVal1_1())){
				item1.setVal1(model.getVal1_1());
			}
			if(StringUtil.isNotBlank(model.getVal2_1())){
				item1.setVal2(model.getVal2_1());
			}
			items.add(item1);
			
			EAssociateItem item2 = new EAssociateItem();
			item2.setScore(2);
			item2.setAssociate(dbRecord);
			if(StringUtil.isNotBlank(model.getVal1_2())){
				item2.setVal1(model.getVal1_2());
			}
			if(StringUtil.isNotBlank(model.getVal2_2())){
				item2.setVal2(model.getVal2_2());
			}
			items.add(item2);
			
			EAssociateItem item3 = new EAssociateItem();
			item3.setScore(3);
			item3.setAssociate(dbRecord);
			if(StringUtil.isNotBlank(model.getVal1_3())){
				item3.setVal1(model.getVal1_2());
			}
			if(StringUtil.isNotBlank(model.getVal2_3())){
				item3.setVal2(model.getVal2_3());
			}
			items.add(item3);
			
			EAssociateItem item4 = new EAssociateItem();
			item4.setScore(4);
			item4.setAssociate(dbRecord);
			if(StringUtil.isNotBlank(model.getVal1_2())){
				item4.setVal1(model.getVal1_4());
			}
			if(StringUtil.isNotBlank(model.getVal2_2())){
				item4.setVal2(model.getVal2_4());
			}
			items.add(item4);
			
			dbRecord.setItems(items);
		}
		else{
			//if(StringUtil.isNotBlank(model.getItems())){
				items = new ArrayList<EAssociateItem>();
				
				EAssociateItem item1 = new EAssociateItem();
				item1.setAssociate(dbRecord);
				item1.setScore(1);
				if(StringUtil.isNotBlank(model.getItems())){
					item1.setItemId(Long.valueOf(model.getItems()));
				}
				item1.setFormula(model.getFormula1());
				item1.setBenchmark(Double.valueOf(model.getBenchmark1()));
				items.add(item1);
				
				EAssociateItem item2 = new EAssociateItem();
				item2.setAssociate(dbRecord);
				item2.setScore(2);
				if(StringUtil.isNotBlank(model.getItems())){
					item2.setItemId(Long.valueOf(model.getItems()));
				}
				item2.setFormula(model.getFormula2());
				item2.setBenchmark(Double.valueOf(model.getBenchmark2()));
				items.add(item2);
				
				EAssociateItem item3 = new EAssociateItem();
				item3.setAssociate(dbRecord);
				item3.setScore(3);
				if(StringUtil.isNotBlank(model.getItems())){
					item3.setItemId(Long.valueOf(model.getItems()));
				}
				item3.setFormula(model.getFormula3());
				item3.setBenchmark(Double.valueOf(model.getBenchmark3()));
				items.add(item3);
				
				EAssociateItem item4 = new EAssociateItem();
				item4.setAssociate(dbRecord);
				item4.setScore(4);
				if(StringUtil.isNotBlank(model.getItems())){
					item4.setItemId(Long.valueOf(model.getItems()));
				}
				item4.setFormula(model.getFormula4());
				item4.setBenchmark(Double.valueOf(model.getBenchmark4()));
				items.add(item4);
				
				dbRecord.setItems(items);
			//}
		}
	}
}
