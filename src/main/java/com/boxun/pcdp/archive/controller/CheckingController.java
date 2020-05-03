package com.boxun.pcdp.archive.controller;

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

import com.boxun.estms.entity.Const;
import com.boxun.estms.util.DateUtil;
import com.boxun.pcdp.archive.entity.ACertCategory;
import com.boxun.pcdp.archive.entity.ACertification;
import com.boxun.pcdp.archive.entity.AEducationInfo;
import com.boxun.pcdp.archive.entity.AExpCategory;
import com.boxun.pcdp.archive.entity.AExperience;
import com.boxun.pcdp.archive.entity.AJobInfo;
import com.boxun.pcdp.archive.entity.AUserInfo;
import com.boxun.pcdp.archive.pojo.CertificationPojo;
import com.boxun.pcdp.archive.pojo.CertificationSetPojo;
import com.boxun.pcdp.archive.pojo.ExperienceCollectionPojo;
import com.boxun.pcdp.archive.pojo.ExperiencePojo;
import com.boxun.pcdp.archive.pojo.ExperienceSetPojo;
import com.boxun.pcdp.archive.pojo.UserInfoPojo;
import com.boxun.pcdp.archive.service.ICertCategoryService;
import com.boxun.pcdp.archive.service.ICertificationService;
import com.boxun.pcdp.archive.service.IEducationInfoService;
import com.boxun.pcdp.archive.service.IExpCategoryService;
import com.boxun.pcdp.archive.service.IExperienceService;
import com.boxun.pcdp.archive.service.IJobInfoService;
import com.boxun.pcdp.archive.service.IUserInfoService;
import com.boxun.pcdp.archive.transformer.UserInfoTransformer;
import com.boxun.pcdp.basic.controller.BaseController;

@Controller
@RequestMapping("/system/archive/checking")
public class CheckingController extends BaseController{

	private static final Logger LOGGER = Logger.getLogger(CheckingController.class);
	
	@Autowired
	private IUserInfoService userinfoService;
	
	@Autowired
	private ICertificationService certificationService;
	
	@Autowired
	private ICertCategoryService certcategoryService;
	
	@Autowired
	private IExperienceService experienceService;
	
	@Autowired
	private IExpCategoryService expcategoryService;
	
	@Autowired
	private IEducationInfoService educationinfoService;
	
	@Autowired
	private IJobInfoService jobinfoService;
	
	@Autowired
	private UserInfoTransformer userinfoTransformer;
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap){
		return "/archive/checking/list";
	}
	
	@RequestMapping("/listdata")
	public @ResponseBody Map<String, Object> listData(){
		LOGGER.info("Get list data.");
		List<AUserInfo> list = userinfoService.list();
		List<UserInfoPojo> listdata = userinfoTransformer.list(list);
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("sEcho", 1);  
		map.put("iTotalRecords", listdata.size());  
        map.put("iTotalDisplayRecords", listdata.size());  
		map.put("aaData", listdata);
		
		return map;
	}
	
	@RequestMapping("/show/{id}")
	public String show(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get show id: " + id);
		if(id != null){
			AUserInfo model = userinfoService.load(id);
			//ExperiencePojo model = experienceTransformer.convert(entity);
			modelMap.addAttribute("model", model);
			
			
			//certification
			List<ACertCategory> certCateList = certcategoryService.listTops();
			List<ACertification> certList =  this.certificationService.list(model.getUser().getId());
			
			List<CertificationSetPojo> certlist = new ArrayList<CertificationSetPojo>();
			for(ACertCategory category : certCateList){
				CertificationSetPojo set = new CertificationSetPojo();
				set.setCategoryName(category.getName());
				set.setCategoryId(category.getId());
				
				if(category.getChildren() != null && !category.getChildren().isEmpty()){
					for(ACertCategory cate : category.getChildren()){
						CertificationPojo pojo = new CertificationPojo();
						pojo.setCategoryId(cate.getId());
						pojo.setCategoryName(cate.getName());
						for(ACertification cert : certList){
							if(cate.getId().equals(cert.getCategory().getId())){
								pojo.setId(cert.getId());
								pojo.setFileName(cert.getFileName());
								pojo.setStatus(cert.getStatus().toString());
								pojo.setUserId(cert.getUser().getId());
								if(cert.getStartDate() != null){
									pojo.setStartDate(DateUtil.formatDateTime(cert.getStartDate(), "yyyyMMdd"));
								}
								if(cert.getEndDate() != null){
									pojo.setEndDate(DateUtil.formatDateTime(cert.getEndDate(), "yyyyMMdd"));
								}
								pojo.setStatus(cert.getStatus().toString());
								if(Const.CertificationStatus.EXPIRED.equals(cert.getStatus())){
									pojo.setExpired(true);
								}
								else{
									pojo.setExpired(DateUtil.timeBeforeToday(cert.getEndDate()));
								}
								break;
							}
						}
						set.add(pojo);
					}
				}
				certlist.add(set);
			}
			
			modelMap.addAttribute("certlist", certlist);
			
			//experience
			List<AExpCategory> expCateList = expcategoryService.listTops();
			List<AExperience> expList =  this.experienceService.list(model.getUser().getId());
			
			List<ExperienceSetPojo> explist = new ArrayList<ExperienceSetPojo>();
			for(AExpCategory category : expCateList){
				ExperienceSetPojo set = new ExperienceSetPojo();
				set.setCategoryId(category.getId());
				set.setCategoryName(category.getName());
				
				if(category.getChildren() != null && !category.getChildren().isEmpty()){
					for(AExpCategory cate : category.getChildren()){
						ExperienceCollectionPojo collection = new ExperienceCollectionPojo();
						collection.setCategoryId(cate.getId());
						collection.setCategoryName(cate.getName());
						
						for(AExperience exp : expList){
							if(cate.getId().equals(exp.getCategory().getId())){
								ExperiencePojo pojo = new ExperiencePojo();
								pojo.setId(exp.getId());
								pojo.setStatus(exp.getStatus().toString());
								pojo.setCompany(exp.getCompany());
								pojo.setCategoryId(exp.getCategory().getId());
								pojo.setDepartment(exp.getDepartment());
								if(exp.getStartDate() != null){
									pojo.setStartDate(DateUtil.formatDateTime(exp.getStartDate(), "yyyy-MM-dd"));
								}
								if(exp.getEndDate() != null){
									pojo.setEndDate(DateUtil.formatDateTime(exp.getEndDate(), "yyyy-MM-dd"));
								}
								collection.add(pojo);
								//break;
							}
							
						}
						set.add(collection);
					}
				}
				explist.add(set);
			}
			
			modelMap.addAttribute("explist", explist);
		}
		
		return "/archive/checking/show";
	}
	
	@RequestMapping("/certcheck/{id}")
	public @ResponseBody Map<String, Object> certcheck(ModelMap modelMap, @PathVariable Long id, @RequestParam String type){
		Map<String, Object> map  = new HashMap<String, Object>();
		if(id != null){
			ACertification record = certificationService.load(id);
			if(Const.CertificationStatus.PASS.toString().equals(type)){
				record.setStatus(Const.CertificationStatus.PASS);
			}
			if(Const.CertificationStatus.REJECT.toString().equals(type)){
				record.setStatus(Const.CertificationStatus.REJECT);
			}
			this.certificationService.update(record);
			map.put("status", "success");
		}
		
		return map;
	}
	
	@RequestMapping("/expcheck/{id}")
	public @ResponseBody Map<String, Object> expcheck(ModelMap modelMap, @PathVariable Long id, @RequestParam String type){
		Map<String, Object> map  = new HashMap<String, Object>();
		if(id != null){
			AExperience record = this.experienceService.load(id);
			if(Const.CertificationStatus.PASS.toString().equals(type)){
				record.setStatus(Const.CertificationStatus.PASS);
			}
			if(Const.CertificationStatus.REJECT.toString().equals(type)){
				record.setStatus(Const.CertificationStatus.REJECT);
			}
			this.experienceService.update(record);
			map.put("status", "success");
		}
		
		return map;
	}
	
	@RequestMapping("/educheck/{id}")
	public @ResponseBody Map<String, Object> educheck(ModelMap modelMap, @PathVariable Long id, @RequestParam String type){
		Map<String, Object> map  = new HashMap<String, Object>();
		if(id != null){
			AEducationInfo record = this.educationinfoService.load(id);
			if(Const.CertificationStatus.PASS.toString().equals(type)){
				record.setStatus(Const.CertificationStatus.PASS);
			}
			if(Const.CertificationStatus.REJECT.toString().equals(type)){
				record.setStatus(Const.CertificationStatus.REJECT);
			}
			this.educationinfoService.update(record);
			map.put("status", "success");
		}
		
		return map;
	}
	
	@RequestMapping("/basiccheck/{id}")
	public @ResponseBody Map<String, Object> basiccheck(ModelMap modelMap, @PathVariable Long id, @RequestParam String type){
		Map<String, Object> map  = new HashMap<String, Object>();
		if(id != null){
			AUserInfo record = this.userinfoService.load(id);
			if(Const.CertificationStatus.PASS.toString().equals(type)){
				record.setStatus(Const.CertificationStatus.PASS);
			}
			if(Const.CertificationStatus.REJECT.toString().equals(type)){
				record.setStatus(Const.CertificationStatus.REJECT);
			}
			this.userinfoService.update(record);
			map.put("status", "success");
		}
		
		return map;
	}
	
	@RequestMapping("/jobcheck/{id}")
	public @ResponseBody Map<String, Object> jobcheck(ModelMap modelMap, @PathVariable Long id, @RequestParam String type){
		Map<String, Object> map  = new HashMap<String, Object>();
		if(id != null){
			AJobInfo record = this.jobinfoService.load(id);
			if(Const.CertificationStatus.PASS.toString().equals(type)){
				record.setStatus(Const.CertificationStatus.PASS);
			}
			if(Const.CertificationStatus.REJECT.toString().equals(type)){
				record.setStatus(Const.CertificationStatus.REJECT);
			}
			this.jobinfoService.update(record);
			map.put("status", "success");
		}
		
		return map;
	}
	
	@RequestMapping("/print/{id}")
	public String print(ModelMap modelMap, @PathVariable Long id, String type){
		if(id != null){
			AUserInfo model = userinfoService.load(id);
			modelMap.addAttribute("model", model);
			modelMap.addAttribute("type", type);
			
			//experience
			List<AExpCategory> expCateList = expcategoryService.listTops();
			List<AExperience> expList =  this.experienceService.list(model.getUser().getId());
			
			List<ExperienceSetPojo> explist = new ArrayList<ExperienceSetPojo>();
			for(AExpCategory category : expCateList){
				ExperienceSetPojo set = new ExperienceSetPojo();
				set.setCategoryId(category.getId());
				set.setCategoryName(category.getName());
				
				if(category.getChildren() != null && !category.getChildren().isEmpty()){
					for(AExpCategory cate : category.getChildren()){
						ExperienceCollectionPojo collection = new ExperienceCollectionPojo();
						collection.setCategoryId(cate.getId());
						collection.setCategoryName(cate.getName());
						
						for(AExperience exp : expList){
							if(cate.getId().equals(exp.getCategory().getId())){
								ExperiencePojo pojo = new ExperiencePojo();
								pojo.setId(exp.getId());
								pojo.setStatus(exp.getStatus().toString());
								pojo.setCompany(exp.getCompany());
								pojo.setCategoryId(exp.getCategory().getId());
								pojo.setDepartment(exp.getDepartment());
								if(exp.getStartDate() != null){
									pojo.setStartDate(DateUtil.formatDateTime(exp.getStartDate(), "yyyy-MM-dd"));
								}
								if(exp.getEndDate() != null){
									pojo.setEndDate(DateUtil.formatDateTime(exp.getEndDate(), "yyyy-MM-dd"));
								}
								collection.add(pojo);
								//break;
							}
							
						}
						set.add(collection);
					}
				}
				explist.add(set);
			}
			
			modelMap.addAttribute("explist", explist);
		}
		return "/archive/checking/print";
	}
}
