package com.boxun.pcdp.estimate.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.boxun.estms.entity.Const;
import com.boxun.estms.pojo.UserInfo;
import com.boxun.estms.util.SortUtil;
import com.boxun.estms.util.StringUtil;
import com.boxun.pcdp.admin.service.IUserService;
import com.boxun.pcdp.basic.controller.BaseController;
import com.boxun.pcdp.estimate.entity.EAssessDetail;
import com.boxun.pcdp.estimate.entity.EAssessItem;
import com.boxun.pcdp.estimate.entity.EAssessProject;
import com.boxun.pcdp.estimate.entity.EIndicator;
import com.boxun.pcdp.estimate.pojo.AssessPojo;
import com.boxun.pcdp.estimate.service.IAssessDetailService;
import com.boxun.pcdp.estimate.service.IAssessItemService;
import com.boxun.pcdp.estimate.service.IAssessProjectService;
import com.boxun.pcdp.estimate.service.IAssessService;
import com.boxun.pcdp.estimate.service.IAssessStaticsService;
import com.boxun.pcdp.estimate.service.IIndicatorService;

@Controller
@RequestMapping("/system/estimate/assessitem")
public class AssessItemController extends BaseController{

private static final Logger LOGGER = Logger.getLogger(AssessItemController.class);
	
	@Autowired
	private IAssessItemService assessitemService;
	
	@Autowired
	private IAssessProjectService assessProjectService;
	
	@Autowired
	private IAssessService assessService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IIndicatorService indicatorService;
	
	@Autowired
	private IAssessDetailService assessdetailService;
	
	@Autowired
	private IAssessStaticsService assesstaticsService;
	
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
		return "/estimate/assessitem/list";
	}
	
	@RequestMapping("/show/{id}")
	public String show(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get show id: " + id);
		if(id != null){
			EAssessItem model = assessitemService.load(id);
			modelMap.addAttribute("model", model);
			
			Long categoryId = model.getAssess().getProject().getCategory().getId();
			
			List<AssessPojo> list = null;
			List<EAssessDetail> details = model.getDetails();
			if(details != null && !details.isEmpty()){
				list = this.assessService.assessList(details, categoryId);
			}
			else{
				list = this.assessService.assessList(model.getAssess().getUser().getId(), categoryId, model.getAssess().getId());
			}
			
			modelMap.addAttribute("list", list);
		}
		
		return "/estimate/assessitem/show";
	}
	
	@RequestMapping("/listdata")
	public @ResponseBody Map<String, Object> listData(Long projectId){
		LOGGER.info("Get list data.");
		UserInfo userInfo = (UserInfo)this.session.getAttribute("userInfo");
		List<EAssessItem> listdata = assessitemService.list4User(userInfo.getUserId(), projectId);
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("sEcho", 1);  
		map.put("iTotalRecords", listdata.size());  
        map.put("iTotalDisplayRecords", listdata.size());  
		map.put("aaData", listdata);
		
		return map;
	}
	
	@RequestMapping("/update/{id}")
	public String update(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get update id: " + id);
		LOGGER.info("Update id: " + id);
		EAssessDetail detail = null;
		if(id != null){
			Map<String, Object> map  = new HashMap<String, Object>();
			try{
				String paramName = null;
				String paramValue = null;
				String indicatorId = null;
				//EAssessDetail detail;
				
				EAssessItem dbRecord = assessitemService.load(id);
				Long categoryId = dbRecord.getAssess().getProject().getCategory().getId();
				
				List<EIndicator> indicators = indicatorService.listByCategory(categoryId);
				List<EAssessDetail> details = dbRecord.getDetails();
				
				//第一个保存的人初始化EAssessDetail里面的记录，同时每次查是否有新增的Eindicator
				List<EAssessDetail> addDetailList = new ArrayList<EAssessDetail>();
				boolean existing = false;
				for(EIndicator ind : indicators){
					existing = false;
					for(EAssessDetail dtl : details){
						if(dtl.getIndicator().getId().equals(ind.getId())){
							existing = true;
							break;
						}
					}
					if(!existing){
						detail = new EAssessDetail();
						detail.setIndicator(ind);
						detail.setAssessItem(dbRecord);
						this.assessdetailService.create(detail);
						addDetailList.add(detail);
					}
				}
				details.addAll(addDetailList);
				
				

				Map<String, EAssessDetail> detailMap = new HashMap<String, EAssessDetail>();
				if(details != null && !details.isEmpty()){
					for(EAssessDetail dtl : details){
						detailMap.put(dtl.getIndicator().getId().toString(), dtl);
					}
				}
				
				Enumeration<String> enu = this.request.getParameterNames();  
				while(enu.hasMoreElements()){  
					paramName = enu.nextElement();  
					paramValue = this.request.getParameter(paramName);
					if(StringUtil.isNotBlank(paramValue)){
						indicatorId = paramName.split("_")[1];
						
						if(detailMap.get(indicatorId) != null){
							detail = detailMap.get(indicatorId);
							detail.setScore(Double.parseDouble(paramValue));
						}
					}
				}
				
				
				
				
				List<EIndicator> inds = new ArrayList<EIndicator>();
				inds.addAll(indicators);
				SortUtil.sort(inds, "level", "desc");
				
				Double calculation = 0d;
				for(EIndicator ind : inds){
					EAssessDetail item = detailMap.get(ind.getId().toString());
					calculation = 0d;
					
					if(ind.getChildren() != null && !ind.getChildren().isEmpty()){
						for(EIndicator child : ind.getChildren()){
							detail = detailMap.get(child.getId().toString());
							if(child.getWeight() != null){
								calculation += (detail.getCalculation() * (child.getAdjustWeight() != null ? child.getAdjustWeight() : child.getWeight()).doubleValue());
							}
						}
					}
					else{
						if(detailMap.get(ind.getId().toString()) != null){
							detail = detailMap.get(ind.getId().toString());
							calculation = detail.getScore();
						}
					}
					item.setCalculation(calculation);	
				}
				
				for(EAssessDetail dtl : detailMap.values()){
					this.assessdetailService.update(dtl);
				}
				
				int index=0;
				Double finalCalculation = 0d;
				for(EAssessDetail dtl : detailMap.values()){
					EAssessDetail item = detailMap.get(dtl.getIndicator().getId().toString());
					if(Const.Mandatory.YES.equals(dtl.getIndicator().getMandatory())){
						if(item.getCalculation() != null && item.getCalculation() == 0){
							finalCalculation = 0d;
							break;
						}
					}
					else{
						if(item.getIndicator().getParent() == null){
							index++;
							System.out.println("round: " + index + ", for " + item.getIndicator().getId() + " - " + item.getIndicator().getName());
							System.out.println(item.getCalculation() + "*" + (item.getIndicator().getAdjustWeight() != null ? item.getIndicator().getAdjustWeight() : item.getIndicator().getWeight()).doubleValue());
							finalCalculation += (item.getCalculation() * (item.getIndicator().getAdjustWeight() != null ? item.getIndicator().getAdjustWeight() : item.getIndicator().getWeight()).doubleValue());
							System.out.println("finalCalculation = " + finalCalculation);
							
						}
					}
				}

				dbRecord.setAssessScore(finalCalculation);
				dbRecord.setAssessTime(new Date());
				dbRecord.setStatus(Const.AssessStatus.COMPLETED);
				this.assessitemService.update(dbRecord);
				
				this.assesstaticsService.calcResult(dbRecord.getAssess());
				map.put("status", "success");  
			}
			catch(Exception e){
				System.out.println("error detail:" + detail.getIndicator().getName() + "+" + detail.getScore());
				e.printStackTrace();
				map.put("status", "fail");  
			}
		}
		return "/success";
	}

	@RequestMapping("/review/{id}")
	public String review(ModelMap modelMap, @PathVariable Long id){
		
		EAssessItem model = assessitemService.load(id);
		modelMap.addAttribute("model", model);
		
		Long categoryId = model.getAssess().getProject().getCategory().getId();
		
		List<AssessPojo> list = null;
		List<EAssessDetail> details = model.getDetails();
		if(details != null && !details.isEmpty()){
			list = this.assessService.assessList(details, categoryId);
		}
		
		modelMap.addAttribute("list", list);
		
		return "/estimate/assessitem/review";
	}
	
	
	@RequestMapping("/pdf/{id}")
	public String pdf(ModelMap modelMap, @PathVariable Long id){
		EAssessItem model = assessitemService.load(id);
		modelMap.addAttribute("model", model);
		
		Long categoryId = model.getAssess().getProject().getCategory().getId();
		
		List<AssessPojo> list = null;
		List<EAssessDetail> details = model.getDetails();
		if(details != null && !details.isEmpty()){
			list = this.assessService.assessList(details, categoryId);
		}
		
		modelMap.addAttribute("list", list);
		
		String filename = null;
		try {
			filename = //URLEncoder.encode(model.getAssess().getUser().getName() + "-aa评估报告.pdf", "UTF-8");
					new String((model.getAssess().getUser().getName() + "-评估报告.pdf").getBytes("UTF-8"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		modelMap.put("FILE_NAME", filename);
		return "pdf";
	}
}
