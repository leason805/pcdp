package com.boxun.pcdp.archive.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.boxun.estms.entity.Const;
import com.boxun.estms.pojo.UserInfo;
import com.boxun.estms.util.DateUtil;
import com.boxun.pcdp.archive.entity.ACertCategory;
import com.boxun.pcdp.archive.entity.ACertification;
import com.boxun.pcdp.archive.pojo.CertificationPojo;
import com.boxun.pcdp.archive.pojo.CertificationSetPojo;
import com.boxun.pcdp.archive.service.ICertCategoryService;
import com.boxun.pcdp.archive.service.ICertificationService;
import com.boxun.pcdp.basic.controller.BaseController;

@Controller
@RequestMapping("/system/archive/certification")
public class CertificationController extends BaseController{

private static final Logger LOGGER = Logger.getLogger(CertificationController.class);
	
	@Autowired
	private ICertificationService certificationService;
	
	@Autowired
	private ICertCategoryService certcategoryService;
	
	@RequestMapping("/index")
	public String index(ModelMap modelMap){
		List<ACertCategory> cateList = certcategoryService.listTops();
		UserInfo userInfo = (UserInfo)this.session.getAttribute("userInfo");
		List<ACertification> certList =  this.certificationService.list(userInfo.getUserId());
		
		List<CertificationSetPojo> list = new ArrayList<CertificationSetPojo>();
		for(ACertCategory category : cateList){
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
			list.add(set);
		}
		
		modelMap.addAttribute("list", list);
		return "/archive/certification/index";
	}
	
	@RequestMapping("/show/{id}")
	public String show(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get show id: " + id);
		if(id != null){
			ACertification model = certificationService.load(id);
			modelMap.addAttribute("model", model);
		}
		
		return "/archive/certification/show";
	}
	
	@RequestMapping("/listdata")
	public @ResponseBody Map<String, Object> listData(){
		LOGGER.info("Get list data.");
		List<ACertification> listdata = certificationService.list();
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("sEcho", 1);  
		map.put("iTotalRecords", listdata.size());  
        map.put("iTotalDisplayRecords", listdata.size());  
		map.put("aaData", listdata);
		
		return map;
	}
	
	@RequestMapping("/create")
	public String create(ModelMap modelMap, ACertification model){

		certificationService.create(model);
		return "/success";
	}
	
	@RequestMapping("/update/{id}")
	public String update(ModelMap modelMap, @PathVariable Long id, ACertification model){
		LOGGER.info("Get update id: " + id);
		LOGGER.info("Update id: " + id);
		if(id != null){
			ACertification dbRecord = certificationService.load(id);
			//dbRecord.setDescription(model.getDescription());
			certificationService.update(dbRecord);
		}
		return "/success";
	}
	
	@RequestMapping("/upload")
	public @ResponseBody Map<String, Object> upload(@RequestParam Long userId, @RequestParam Long typeId, HttpServletRequest request,HttpServletResponse response){
	//public @ResponseBody Map<String, Object> upload(HttpServletRequest request,HttpServletResponse response){
		LOGGER.info("upload file.");
		System.out.println("userId: " + userId);
		System.out.println("typeId: " + typeId);
		Map<String, Object> map  = new HashMap<String, Object>();
		Long resultId = null;
		Boolean result = false;
		try{
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
	        //判断 request 是否有文件上传,即多部分请求  
	        if(multipartResolver.isMultipart(request)){  
	            //转换成多部分request    
	            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
	            //取得request中的所有文件名  
	            Iterator<String> iter = multiRequest.getFileNames();  
	            while(iter.hasNext()){  
	                MultipartFile file = multiRequest.getFile(iter.next());  
	                if(file != null){  
	                	resultId = this.certificationService.createFile(file, userId, typeId);  
	                } 
	            } 
	        }
		}
		catch(Exception e){
			e.printStackTrace();
		}
		if(resultId != null && resultId > 0){
			result = true;
		}
		map.put("result", result);  
		map.put("typeId", typeId); 
		map.put("id", resultId);
		
		return map;
	}
	
	@RequestMapping("/delete/{id}")
	public @ResponseBody Map<String, Object> delete(@PathVariable Long id){
		Map<String, Object> map  = new HashMap<String, Object>();
		
		if(id != null){
			ACertification record = certificationService.load(id);
			certificationService.delete(record);
			map.put("url", "/system/archive/certification/index.htm");  
			map.put("status", "success");
		}
		return map;
	}
	
	@RequestMapping("/updatedate")
	public @ResponseBody Map<String, Object> updatedate(Long id, String startdate, String enddate){
		Map<String, Object> map  = new HashMap<String, Object>();
		
		if(id != null){
			ACertification record = certificationService.load(id);
			Date start = DateUtil.formatDate(startdate, "yyyyMMdd");
			Date end = DateUtil.formatDate(enddate, "yyyyMMdd");

			record.setStartDate(start);
			record.setEndDate(end);
			record.setStatus(Const.CertificationStatus.PENDING);
			this.certificationService.update(record);
			
			map.put("status", "success");
		}
		else{
			map.put("status", "fail");
		}
		return map;
	}
}
