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

import com.boxun.estms.util.StringUtil;
import com.boxun.pcdp.basic.controller.BaseController;
import com.boxun.pcdp.estimate.entity.EIndicator;
import com.boxun.pcdp.estimate.entity.EIndicatorAlarm;
import com.boxun.pcdp.estimate.entity.EIndicatorCategory;
import com.boxun.pcdp.estimate.pojo.IndicatorAlarmCategoryPojo;
import com.boxun.pcdp.estimate.pojo.IndicatorAlarmPojo;
import com.boxun.pcdp.estimate.service.IIndicatorAlarmService;
import com.boxun.pcdp.estimate.service.IIndicatorCategoryService;
import com.boxun.pcdp.estimate.service.IIndicatorService;

@Controller
@RequestMapping("/system/estimate/indicatoralarm")
public class IndicatorAlarmController extends BaseController{

private static final Logger LOGGER = Logger.getLogger(IndicatorAlarmController.class);
	
	@Autowired
	private IIndicatorAlarmService indicatoralarmService;
	
	@Autowired
	private IIndicatorService indicatorService;
	
	@Autowired
	private IIndicatorCategoryService indicatorcategoryService;
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap){
		
		List<IndicatorAlarmCategoryPojo> list = new ArrayList<IndicatorAlarmCategoryPojo>();
		
		List<EIndicatorCategory> catelist = this.indicatorcategoryService.listActive();
		List<EIndicatorAlarm> alarms = indicatoralarmService.list();
		
		for(EIndicatorCategory cat : catelist){
			IndicatorAlarmCategoryPojo pojo = new IndicatorAlarmCategoryPojo();
			pojo.setId(cat.getId());
			pojo.setName(cat.getName());
			
			List<EIndicator> tops = indicatorService.listTops4Category(cat.getId());
			
			for(EIndicator indicator : tops){
				IndicatorAlarmPojo alarmPojo = new IndicatorAlarmPojo();
				alarmPojo.setName(indicator.getName());
				alarmPojo.setId(indicator.getId());
				for(EIndicatorAlarm alarm : alarms){
					if(alarm.getIndicator() != null && alarm.getIndicator().getId().equals(indicator.getId())){
						alarmPojo.setAlarm(alarm.getAlarm());
						break;
					}
				}
				pojo.addAlarm(alarmPojo);
			}
			
			for(EIndicatorAlarm alarm : alarms){
				if(alarm.getIndicator() == null && alarm.getCategory().getId().equals(cat.getId())){
					pojo.setParentAlaram(alarm.getAlarm());
					break;
				}
			}
			
			list.add(pojo);
		}
		
//		
//		List<EIndicator> list = indicatorService.listTops();
//		List<EIndicatorAlarm> alarms = indicatoralarmService.list();
//		modelMap.addAttribute("alarms", alarms);
		modelMap.addAttribute("list", list);
		return "/estimate/indicatoralarm/list";
	}
	
	@RequestMapping("/update")
	public @ResponseBody Map<String, Object> update(ModelMap modelMap, EIndicatorAlarm model){
		String paramName = null;
		String paramValue = null;
		String id = null;
		String categoryId = null;
		
		Map<String, Object> map  = new HashMap<String, Object>();
		try{
			List<EIndicator> list = indicatorService.list();
			Map<String, EIndicator> indMaps = new HashMap<String, EIndicator>();
			for(EIndicator indicator : list){
				indMaps.put(indicator.getId().toString(), indicator);
			}

			Map<String, EIndicatorAlarm> alarmMaps = new HashMap<String, EIndicatorAlarm>();
			List<EIndicatorAlarm> alarmList = this.indicatoralarmService.list();
			for(EIndicatorAlarm alarm : alarmList){
				if(alarm.getIndicator() != null){
					alarmMaps.put(alarm.getCategory().getId() + "_" + alarm.getIndicator().getId().toString(), alarm);
				}
				else{
					alarmMaps.put(alarm.getCategory().getId() + "_" + "0", alarm);
				}
			}
			
			EIndicatorAlarm alarm = null;
			EIndicator indicator = null;
			EIndicatorCategory category = null;
			Enumeration<String> enu = this.request.getParameterNames();  
			while(enu.hasMoreElements()){  
				paramName = enu.nextElement();  
				paramValue = this.request.getParameter(paramName);
				if(StringUtil.isNotBlank(paramValue)){
					categoryId = paramName.split("_")[1];
					id = paramName.split("_")[2];
					
					alarm = alarmMaps.get(categoryId + "_" + id);
					indicator = indMaps.get(id);
					if(alarm == null){
						alarm = new EIndicatorAlarm();
						alarm.setIndicator(indicator);
						
						if(StringUtil.isNotBlank(categoryId)){
							category = this.indicatorcategoryService.load(Long.valueOf(categoryId));
						}
						alarm.setCategory(category);
						
					}
					alarm.setAlarm(Double.parseDouble(paramValue));
					this.indicatoralarmService.update(alarm);
					
				}
			}

			map.put("status", "success");  
		}
		catch(Exception e){
			map.put("status", "fail");  
		}
		return map;
	}
}
