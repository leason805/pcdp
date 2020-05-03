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
import com.boxun.estms.util.StringUtil;
import com.boxun.pcdp.admin.entity.TUser;
import com.boxun.pcdp.admin.service.IUserService;
import com.boxun.pcdp.basic.controller.BaseController;
import com.boxun.pcdp.estimate.entity.EAssess;
import com.boxun.pcdp.estimate.entity.EAssessItem;
import com.boxun.pcdp.estimate.entity.EAssessProject;
import com.boxun.pcdp.estimate.pojo.AssessItemPojo;
import com.boxun.pcdp.estimate.service.IAssessDetailService;
import com.boxun.pcdp.estimate.service.IAssessItemService;
import com.boxun.pcdp.estimate.service.IAssessProjectService;
import com.boxun.pcdp.estimate.service.IAssessService;
import com.boxun.pcdp.estimate.service.IIndicatorService;

@Controller
@RequestMapping("/system/estimate/assess")
public class AssessController extends BaseController{

private static final Logger LOGGER = Logger.getLogger(AssessController.class);
	
	@Autowired
	private IUserService userService;

	@Autowired
	private IAssessService assessService;
	
	@Autowired
	private IAssessProjectService assessProjectService;
	
	@Autowired
	private IAssessItemService assessitemService;
	
	@Autowired
	private IAssessDetailService assessdetailService;
	
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
		return "/estimate/assess/list";
	}
	
	@RequestMapping("/show/{id}")
	public String show(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get show id: " + id);
		if(id != null){
			EAssess model = assessService.load(id);
			modelMap.addAttribute("model", model);
			
//			List<AssessPojo> list = null;
//			List<EAssessDetail> details = this.assessdetailService.list(model.getId());
//			if(details != null && !details.isEmpty()){
//				list = this.assessService.assessList(details);
//			}
//			else{
//				list = this.assessService.assessList(model.getUser().getId());
//			}
//			
//			modelMap.addAttribute("list", list);
		}
		
		return "/estimate/assess/show";
	}
	
	@RequestMapping("/listdata")
	public @ResponseBody Map<String, Object> listData(Long projectId){
		LOGGER.info("Get list data.");
		List<EAssess> list = assessService.list(projectId);
		
		List<AssessItemPojo> listdata = new ArrayList<AssessItemPojo>();
		for(EAssess assess : list){
			AssessItemPojo pojo = new AssessItemPojo();
			pojo.setId(assess.getId());
			pojo.setName(assess.getUser().getName());
			pojo.setStatus(assess.getStatus().toString());
			StringBuffer sb = new StringBuffer();
			for(EAssessItem item : assess.getItems()){
				sb.append(item.getOwner().getName()).append("/");
			}
			pojo.setUsers(sb.toString());
			listdata.add(pojo);
		}
		
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("sEcho", 1);  
		map.put("iTotalRecords", listdata.size());  
        map.put("iTotalDisplayRecords", listdata.size());  
		map.put("aaData", listdata);
		
		return map;
	}
	
	
	@RequestMapping("/loaduser")
	public @ResponseBody Map<String, Object> loaduser(Long id, String type){
		
		Map<String, Object> map  = new HashMap<String, Object>();
		List<Map<String, Object>> rowsData = new ArrayList<Map<String, Object>>();
		Boolean assigned = "assigned".equals(type);
		
		Long total = assessitemService.getSize4Assess(id, assigned);
		if(total > 0){
			List<TUser> list = assessitemService.listUsers4Assess(id, assigned);
			if(list != null){
				for (TUser user : list) {
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("id", user.getId());
					map1.put("account", user.getLoginID());
					map1.put("realName", user.getName());
					rowsData.add(map1);
				}
			}
		}
		map.put("total", total);
		map.put("rows", rowsData);
		return map;
	}
	
	@RequestMapping("/update/{id}")
	public String update(ModelMap modelMap, @PathVariable Long id, String userIds){
		LOGGER.info("Get update id: " + id);
		LOGGER.info("Update id: " + id);
		if(id != null){
			EAssess dbRecord = assessService.load(id);

			List<EAssessItem> list = this.assessitemService.list4Assess(dbRecord.getId());
			List<Long> idList = StringUtil.split4Long(userIds, Const.COMMA_DELIMITER);
			
			//for new add
			if(StringUtil.isNotBlank(userIds)){
				for(Long uid : idList){
					boolean exist = false;
					for(EAssessItem item : list){
						if(item.getOwner() != null && item.getOwner().getId().equals(uid)){
							exist = true;
							break;
						}
					}
					if(!exist){
						EAssessItem item = new EAssessItem();
						item.setAssess(dbRecord);
						TUser user = userService.load(uid);
						item.setOwner(user);
						item.setStatus(Const.AssessStatus.DRAFT);
						assessitemService.create(item);
					}
				}
			}
			
			
			//for remove
			for(EAssessItem item : list){
				boolean remove = true;
				if(idList != null){
					for(Long uid : idList){
						if(item.getOwner() != null && item.getOwner().getId().equals(uid)){
							remove = false;
							break;
						}
					}
				}
				
				if(remove){
					item.setOwner(null);
					this.assessitemService.update(item);
				}
			}
			
			if(idList != null && !idList.isEmpty()){
				dbRecord.setStatus(Const.AssessStatus.ASSIGNED);
			}
			else{
				dbRecord.setStatus(Const.AssessStatus.UNASSIGN);
			}
			this.assessService.update(dbRecord);
		}
		return "/success";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	
	
	
	@RequestMapping("/create")
	public String create(ModelMap modelMap, EAssess model){

		assessService.create(model);
		return "/success";
	}
	
	@RequestMapping("/update1/{id}")
	public String update(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get update id: " + id);
		LOGGER.info("Update id: " + id);
		if(id != null){
			Map<String, Object> map  = new HashMap<String, Object>();
			try{
				String paramName = null;
				String paramValue = null;
				String indicatorId = null;
				EIndicator indicator = null;
				EAssessDetail detail;
				
				UserInfo userInfo = (UserInfo)this.session.getAttribute("userInfo");
				TUser user = userService.load(userInfo.getUserId());
				
				EAssess dbRecord = assessService.load(id);
				List<EIndicator> indicators = indicatorService.list();
				List<EAssessDetail> details = this.assessdetailService.list(dbRecord.getId());
				
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
						detail.setAssess(dbRecord);
						this.assessdetailService.create(detail);
					}
				}
				details.addAll(addDetailList);
				
				
				//新加相应的DetailItem
				List<EAssessDetailItem> detailItemList = new ArrayList<EAssessDetailItem>();
				
				Map<String, EAssessDetail> detailMap = new HashMap<String, EAssessDetail>();
				if(details != null && !details.isEmpty()){
					for(EAssessDetail dtl : details){
						detailMap.put(dtl.getIndicator().getId().toString(), dtl);
					}
				}
//				
//				else{
//					for(EIndicator ind : list){
//						detail = new EAssessDetail();
//						detail.setIndicator(ind);
//						detail.setAssess(dbRecord);
//						//detail.setScore(0);
//						detailMap.put(detail.getIndicator().getId().toString(), detail);
//					}
//				}
				
				EAssessDetailItem item = null;
				Enumeration<String> enu = this.request.getParameterNames();  
				while(enu.hasMoreElements()){  
					paramName = enu.nextElement();  
					paramValue = this.request.getParameter(paramName);
					if(StringUtil.isNotBlank(paramValue)){
						indicatorId = paramName.split("_")[1];
						
						item = new EAssessDetailItem();
						if(detailMap.get(indicatorId) != null){
							detail = detailMap.get(indicatorId);
							item.setUser(user);
							item.setDetail(detail);
							item.setScore(Integer.parseInt(paramValue));
							detailItemList.add(item);
						}
					}
				}
				
				for(EAssessDetail dtl : detailMap.values()){
					this.assessdetailService.update(dtl);
				}
				
				//dbRecord.setStatus(Const.AssessStatus.COMPLETED);
				this.assessService.update(dbRecord);
				map.put("status", "success");  
			}
			catch(Exception e){
				map.put("status", "fail");  
			}
		}
		return "/success";
	}
	
	private List<AssessPojo> assessList(List<EIndicator> indicators){
		List<AssessPojo> list = new ArrayList<AssessPojo>();
		AssessPojo pojo = null;
		for(EIndicator in : indicators){
			pojo = new AssessPojo();
			pojo.setId(in.getId());
			pojo.setMandatory(in.getMandatory());
			pojo.setName(in.getName());
			pojo.setSequence(in.getSequence());
			pojo.setDescription(in.getDescription());
			list.add(pojo);
			children(pojo, in.getChildren());
		}
		return list;
	}
	
	private void children(AssessPojo assess, List<EIndicator> indicators){
		AssessPojo pojo = null;
		if(indicators != null && !indicators.isEmpty()){
			for(EIndicator in : indicators){
				pojo = new AssessPojo();
				pojo.setId(in.getId());
				pojo.setMandatory(in.getMandatory());
				pojo.setName(in.getName());
				pojo.setSequence(in.getSequence());
				pojo.setDescription(in.getDescription());
				pojo.setParent(assess);
				assess.addChild(pojo);
				children(pojo, in.getChildren());
			}
		}
	}
	
	*/
}
