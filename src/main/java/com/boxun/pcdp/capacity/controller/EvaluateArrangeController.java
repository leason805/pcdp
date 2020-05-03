package com.boxun.pcdp.capacity.controller;

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
import com.boxun.pcdp.cache.CacheManager;
import com.boxun.pcdp.capacity.entity.CEvaluateArrange;
import com.boxun.pcdp.capacity.entity.CEvaluateArrangeUser;
import com.boxun.pcdp.capacity.entity.CEvaluateProject;
import com.boxun.pcdp.capacity.pojo.ArrangePojo;
import com.boxun.pcdp.capacity.service.IEvaluateArrangeService;
import com.boxun.pcdp.capacity.service.IEvaluateArrangeUserService;
import com.boxun.pcdp.capacity.service.IEvaluateProjectService;
import com.boxun.pcdp.capacity.transformer.ArrangeTransformer;
import com.boxun.pcdp.knowledge.entity.KArrange;
import com.boxun.pcdp.knowledge.entity.KArrangeUser;
import com.boxun.pcdp.knowledge.service.IArrangeUserService;

@Controller("CArrange")
@RequestMapping("/system/capacity/evaluatearrange")
public class EvaluateArrangeController extends BaseController{

private static final Logger LOGGER = Logger.getLogger(EvaluateArrangeController.class);
	
	@Autowired
	private IEvaluateArrangeService evaluatearrangeService;
	
	@Autowired
	private IEvaluateProjectService evaluateprojectService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ArrangeTransformer carrangeTransformer;
	
	@Autowired
	private CacheManager cacheManager;
	
	@Autowired
	private IEvaluateArrangeUserService carrangeuserService;
	
	@Autowired
	private IArrangeUserService karrangeuserService;
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap){
		return "/capacity/evaluatearrange/list";
	}
	
	@RequestMapping("/show/{id}")
	public String show(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get show id: " + id);
		List<CEvaluateProject> projects = this.evaluateprojectService.list();
		modelMap.addAttribute("projects", projects);
		
		List<TUser> users = this.userService.list();
		modelMap.addAttribute("users", users);
		
		if(id != null){
			CEvaluateArrange model = evaluatearrangeService.load(id);
			modelMap.addAttribute("model", model);
		}
		
		return "/capacity/evaluatearrange/show";
	}
	
	@RequestMapping("/listdata")
	public @ResponseBody Map<String, Object> listData(){
		LOGGER.info("Get list data.");
		List<CEvaluateArrange> listdata = evaluatearrangeService.list();
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("sEcho", 1);  
		map.put("iTotalRecords", listdata.size());  
        map.put("iTotalDisplayRecords", listdata.size());  
		map.put("aaData", listdata);
		
		return map;
	}
	
	@RequestMapping("/create")
	public String create(ModelMap modelMap, ArrangePojo model){
		CEvaluateArrange arrange = this.carrangeTransformer.transform(model);
		if(model != null && model.getProjectId() != null&& model.getProjectId() > 0){
			CEvaluateProject project = this.evaluateprojectService.load(model.getProjectId());
			arrange.setProject(project);
		}
		
		if(model != null && model.getAdjudicator() != null && model.getAdjudicator() > 0){
			TUser user = userService.load(model.getAdjudicator());
			arrange.setAdjudicator(user);
		}
		
		if(model != null && model.getInvigilator() != null && model.getInvigilator() > 0){
			TUser user = userService.load(model.getInvigilator());
			arrange.setInvigilator(user);
		}
		
		arrange.setStatus(Const.ArrangeStatus.PENDING);
		evaluatearrangeService.create(arrange);
		
		if(StringUtil.isNotBlank(model.getUserIds())){
			List<Long> idList = StringUtil.split4Long(model.getUserIds(), Const.COMMA_DELIMITER);
			for(Long uid : idList){
				TUser user = userService.load(uid);
				CEvaluateArrangeUser arrUser =  new CEvaluateArrangeUser();
				arrUser.setArrange(arrange);
				arrUser.setStatus(Const.ArrangeUserStatus.DRAFT);
				arrUser.setCheckStatus(Const.ArrangeUserStatus.DRAFT);
				arrUser.setUser(user);
				arrUser.setVcode(StringUtil.getVCode());
				arrUser.setUcode(StringUtil.uuid());
				arrUser.setBarcodeImage(barcodeText(arrUser));
				String barCode = this.karrangeuserService.generateBarCode(arrUser.getUser().getId(), barcodeText(arrUser));
				arrUser.setBarcodeImage(barCode);
				carrangeuserService.create(arrUser);
			}
		}
		
		return "/success";
	}
	
	private String barcodeText(CEvaluateArrangeUser arrUser){
		String path = request.getContextPath();  
		String FQDN = cacheManager.get(Const.FQDN);
		String basePath = FQDN+path+"/mi/main/";
		//String text = basePath + "sign.htm?type=EX&i=" + arrUser.getId() + "&u=" + arrUser.getUser().getId() + "&v=" + arrUser.getVcode();
		String text = basePath + "sign/EX/" + arrUser.getUcode() + ".htm";
		return text;
	}
	
	@RequestMapping("/update/{id}")
	public String update(ModelMap modelMap, @PathVariable Long id, ArrangePojo model){
		LOGGER.info("Get update id: " + id);
		LOGGER.info("Update id: " + id);
		if(id != null){
			CEvaluateArrange dbRecord = evaluatearrangeService.load(id);
			this.carrangeTransformer.update(model, dbRecord);
			
			if(model != null && model.getProjectId() != null&& model.getProjectId() > 0){
				CEvaluateProject project = this.evaluateprojectService.load(model.getProjectId());
				dbRecord.setProject(project);
			}
			if(model != null && model.getAdjudicator() != null && model.getAdjudicator() > 0){
				TUser user = userService.load(model.getAdjudicator());
				dbRecord.setAdjudicator(user);
			}
			
			if(model != null && model.getInvigilator() != null && model.getInvigilator() > 0){
				TUser user = userService.load(model.getInvigilator());
				dbRecord.setInvigilator(user);
			}
			evaluatearrangeService.update(dbRecord);
			
			List<CEvaluateArrangeUser> list = this.carrangeuserService.list(dbRecord.getId());
			List<Long> idList = StringUtil.split4Long(model.getUserIds(), Const.COMMA_DELIMITER);
			
			//for new add
			if(StringUtil.isNotBlank(model.getUserIds())){
				for(Long uid : idList){
					boolean exist = false;
					for(CEvaluateArrangeUser arrangeUser : list){
						if(arrangeUser.getUser() != null && arrangeUser.getUser().getId().equals(uid)){
							exist = true;
							break;
						}
					}
					if(!exist){
						CEvaluateArrangeUser arrangeUser = new CEvaluateArrangeUser();
						arrangeUser.setArrange(dbRecord);
						arrangeUser.setStatus(Const.ArrangeUserStatus.DRAFT);
						arrangeUser.setCheckStatus(Const.ArrangeUserStatus.DRAFT);
						TUser user = userService.load(uid);
						arrangeUser.setUser(user);
						arrangeUser.setVcode(StringUtil.getVCode());
						arrangeUser.setUcode(StringUtil.uuid());
						arrangeUser.setBarcodeImage(barcodeText(arrangeUser));
						String barCode = this.karrangeuserService.generateBarCode(arrangeUser.getUser().getId(), barcodeText(arrangeUser));
						arrangeUser.setBarcodeImage(barCode);
						carrangeuserService.create(arrangeUser);
					}
				}
			}
			
			
			//for remove
			for(CEvaluateArrangeUser arrangeUser : list){
				boolean remove = true;
				if(idList != null){
					for(Long uid : idList){
						if(arrangeUser.getUser() != null && arrangeUser.getUser().getId().equals(uid)){
							remove = false;
							break;
						}
					}
				}
				
				if(remove){
					arrangeUser.setUser(null);
					this.carrangeuserService.update(arrangeUser);
					//this.karrangeuserService.delete(arrangeUser);
				}
			}
		}
		return "/success";
	}
	
	@RequestMapping("/loaduser")
	public @ResponseBody Map<String, Object> loaduser(Long id, String type){
		
		Map<String, Object> map  = new HashMap<String, Object>();
		List<Map<String, Object>> rowsData = new ArrayList<Map<String, Object>>();
		Boolean assigned = "assigned".equals(type);
		
		Long total = carrangeuserService.getSize4Arrange(id, assigned);
		if(total > 0){
			List<TUser> list = carrangeuserService.listUsers4Arrange(id, assigned);
			if(list != null){
				for (TUser user : list) {
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("id", user.getId());
					map1.put("company", user.getCompany().getName());
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
	
	@RequestMapping("/delete/{id}")
	public @ResponseBody Map<String, Object> delete(@PathVariable Long id){
		Map<String, Object> map  = new HashMap<String, Object>();
		
		if(id != null){
			Long starttedSize = this.carrangeuserService.getSize4Started(id);
			if(starttedSize > 0){
				map.put("status", "exist");
			}
			else{
				CEvaluateArrange record = evaluatearrangeService.load(id);
				if(record != null){
					this.carrangeuserService.delete(id);
					evaluatearrangeService.delete(record); 
					map.put("status", "success");
				}
				else{
					map.put("status", "fail");
				}
			}
				
			
		}
		return map;
	}
	
	@RequestMapping("/vcode/{id}")
	public String vcode(ModelMap modelMap, @PathVariable Long id){
		if(id != null){
			List<CEvaluateArrangeUser> list = this.carrangeuserService.list(id);
			modelMap.addAttribute("list", list);
		}
		return "/knowledge/arrange/vcode";
	}
	
	@RequestMapping("/record/{id}")
	public String record(ModelMap modelMap, @PathVariable Long id){
		if(id != null){
			List<CEvaluateArrangeUser> list = this.carrangeuserService.list(id);
			modelMap.addAttribute("list", list);
		}
		return "/knowledge/arrange/record";
	}
}
