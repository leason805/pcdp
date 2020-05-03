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

import com.boxun.estms.util.SortUtil;
import com.boxun.estms.util.StringUtil;
import com.boxun.pcdp.archive.entity.AUserInfo;
import com.boxun.pcdp.archive.service.IUserInfoService;
import com.boxun.pcdp.basic.controller.BaseController;
import com.boxun.pcdp.estimate.entity.EAssess;
import com.boxun.pcdp.estimate.entity.EAssessProject;
import com.boxun.pcdp.estimate.entity.EAssessStatics;
import com.boxun.pcdp.estimate.entity.EIndicator;
import com.boxun.pcdp.estimate.pojo.AssessStaticsSetPojo;
import com.boxun.pcdp.estimate.pojo.ReportPojo;
import com.boxun.pcdp.estimate.pojo.ReportSetPojo;
import com.boxun.pcdp.estimate.service.IAssessProjectService;
import com.boxun.pcdp.estimate.service.IAssessService;
import com.boxun.pcdp.estimate.service.IAssessStaticsService;
import com.boxun.pcdp.estimate.service.IIndicatorService;

@Controller
@RequestMapping("/system/estimate/assesstatics")
public class AssessStaticsController extends BaseController{

private static final Logger LOGGER = Logger.getLogger(AssessStaticsController.class);
	
	@Autowired
	private IAssessStaticsService assesstaticsService;
	
	@Autowired
	private IAssessService assessService;
	
	@Autowired
	private IAssessProjectService assessProjectService;
	
	@Autowired
	private IUserInfoService userinfoService;
	
	@Autowired
	private IIndicatorService indicatorService;
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap, Long projectId){
		List<EAssessProject> projects = this.assessProjectService.list();
		modelMap.addAttribute("projects", projects);
		if(projectId == null){
			if(projects != null && !projects.isEmpty()){
				projectId = projects.get(0).getId();
			}
			else{
				projectId = 0l;
			}
		}
		modelMap.addAttribute("projectId", projectId);
		return "/estimate/assesstatics/list";
	}
	
	@RequestMapping("/show/{id}")
	public String show(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get show id: " + id);
		if(id != null){
			EAssess model = assessService.load(id);
			modelMap.addAttribute("model", model);
			
			AssessStaticsSetPojo staticsSet = null;
			if(model != null && !model.getStatics().isEmpty()){
				staticsSet = this.assesstaticsService.assessList(model);
			}
			
			modelMap.addAttribute("statics", staticsSet);
		}
		
		return "/estimate/assesstatics/show";
	}
	
	@RequestMapping("/listdata")
	public @ResponseBody Map<String, Object> listData(Long projectId){
		LOGGER.info("Get list data.");
		List<EAssess> listdata = assessService.list(projectId);
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("sEcho", 1);  
		map.put("iTotalRecords", listdata.size());  
        map.put("iTotalDisplayRecords", listdata.size());  
		map.put("aaData", listdata);
		
		return map;
	}
	
	@RequestMapping("/report")
	public String report(ModelMap modelMap, Long projectId){
		List<EAssessProject> projects = this.assessProjectService.list();
		modelMap.addAttribute("projects", projects);
		if(projectId == null){
			if(projects != null && !projects.isEmpty()){
				projectId = projects.get(0).getId();
			}
			else{
				projectId = 0l;
			}
		}
		modelMap.addAttribute("projectId", projectId);
		
		
		if(projectId != null){
			EAssessProject project = this.assessProjectService.load(projectId);
			
			List<EAssess> assessList = this.assessService.list(projectId);
			
			List<EIndicator> tops = this.indicatorService.listTops4Category(project.getCategory().getId());
			List<Long> indicators = new ArrayList<Long>();
			for(EIndicator ind : tops){
				indicators.add(ind.getId());
			}
			
			ReportSetPojo pgfxSet = new ReportSetPojo();
			Map<Long, ReportPojo> pgfxMap = new HashMap<Long, ReportPojo>();
			Map<Long, Integer> pgfxSizeMap = new HashMap<Long, Integer>();
			
			
			Map<Long, ReportPojo> zbdfMap = new HashMap<Long, ReportPojo>();
			Map<Long, Integer> zbdfSizeMap = new HashMap<Long, Integer>();
			
			ReportSetPojo pgdfSet = new ReportSetPojo();
			ReportSetPojo zbdfSet = new ReportSetPojo();
			ReportSetPojo bzfxSet = new ReportSetPojo();
			for(EIndicator ind : tops){
				bzfxSet.addLable(ind.getName());
			}
			
			for(EAssess assess : assessList){
				if(assess.getAssessScore() != null){
					AUserInfo userInfo = this.userinfoService.loadByUser(assess.getUser().getId());

					//评估结果统计分析
					if(userInfo.getJobInfo()!= null && userInfo.getJobInfo().getTechLevel() != null){
						ReportPojo tjfxPojo = pgfxMap.get(userInfo.getJobInfo().getTechLevel().getId());
						if(tjfxPojo == null){
							tjfxPojo = new ReportPojo();
							tjfxPojo.setData(assess.getAssessScore());
							tjfxPojo.setLabel(userInfo.getJobInfo().getTechLevel().getName());
							pgfxMap.put(userInfo.getJobInfo().getTechLevel().getId(), tjfxPojo);
							pgfxSizeMap.put(userInfo.getJobInfo().getTechLevel().getId(), 1);
						}
						else{
							tjfxPojo.setData(tjfxPojo.getData() + assess.getAssessScore());
							pgfxSizeMap.put(userInfo.getJobInfo().getTechLevel().getId(), pgfxSizeMap.get(userInfo.getJobInfo().getTechLevel().getId()) + 1);
						}
					}
					
					
					//评估得分
					ReportPojo pgdfPojo = new ReportPojo();
					pgdfPojo.setData(assess.getAssessScore());
					StringBuffer sb = new StringBuffer(userInfo.getUser().getName());
					if(userInfo.getJobInfo()!= null && userInfo.getJobInfo().getTechLevel() != null){
						sb.append(" (").append(userInfo.getJobInfo().getTechLevel().getName()).append(")");
					}
					pgdfPojo.setLabel(sb.toString());
					pgdfSet.addReport(pgdfPojo);
					
					
					//指标得分
					List<EAssessStatics> staticsList = this.assesstaticsService.list(assess.getId(), indicators);
					for(EIndicator ind : tops){
						ReportPojo zbdfPojo = zbdfMap.get(ind.getId());
						if(zbdfPojo == null){
							zbdfPojo = new ReportPojo();
							zbdfPojo.setLabel(ind.getName());
							zbdfPojo.setOrder(ind.getSequence());
							zbdfMap.put(ind.getId(), zbdfPojo);
							zbdfSizeMap.put(ind.getId(), 1);
						}
						for(EAssessStatics statics : staticsList){
							if(ind.getId().equals(statics.getIndicator().getId())){
								if(zbdfPojo.getData() == null){
									zbdfPojo.setData(statics.getResult());
								}
								else{
									zbdfPojo.setData(zbdfPojo.getData() + statics.getResult());
									zbdfSizeMap.put(ind.getId(), zbdfSizeMap.get(ind.getId()) + 1);
								}
								
							}
						}
					}
					
					//班组分析
					
					ReportPojo bzfxPojo = new ReportPojo();
					bzfxPojo.setName(userInfo.getUser().getName());
					bzfxPojo.setColor(StringUtil.getRandColorCode());
					for(EIndicator ind : tops){
						for(EAssessStatics statics : staticsList){
							if(ind.getId().equals(statics.getIndicator().getId())){
								bzfxPojo.addData(statics.getResult());
							}
						}
						
					}
					bzfxSet.addReport(bzfxPojo);
				}
			}
			
				for(Long key : pgfxMap.keySet()){
					ReportPojo po = pgfxMap.get(key);
					po.setData(StringUtil.getDouble(po.getData()/pgfxSizeMap.get(key), 3));
				}
				pgfxSet.addReports(pgfxMap.values());
				modelMap.addAttribute("pgfxSet", pgfxSet);
				
				modelMap.addAttribute("pgdfSet", pgdfSet);
			
			
				for(Long key : zbdfMap.keySet()){
					ReportPojo po = zbdfMap.get(key);
					po.setData(StringUtil.getDouble(po.getData()/zbdfSizeMap.get(key), 3));
					po.setColor(StringUtil.getRandColorCode());
				}
				zbdfSet.addReports(zbdfMap.values());
				SortUtil.sort(zbdfSet.getReports(), "order");
				modelMap.addAttribute("zbdfSet", zbdfSet);
				
				
				modelMap.addAttribute("bzfxSet", bzfxSet);
			
		
		}
		//
		return "/estimate/assesstatics/report";
	}
}
