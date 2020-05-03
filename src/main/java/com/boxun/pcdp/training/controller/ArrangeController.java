package com.boxun.pcdp.training.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
import com.boxun.pcdp.training.entity.TArrange;
import com.boxun.pcdp.training.entity.TArrangeUser;
import com.boxun.pcdp.training.entity.TCourse;
import com.boxun.pcdp.training.pojo.ArrangePojo;
import com.boxun.pcdp.training.service.IArrangeService;
import com.boxun.pcdp.training.service.IArrangeUserService;
import com.boxun.pcdp.training.service.ICourseService;
import com.boxun.pcdp.training.transformer.ArrangeTransformer;

@Controller("TArrange")
@RequestMapping("/system/training/arrange")
public class ArrangeController extends BaseController{

private static final Logger LOGGER = Logger.getLogger(ArrangeController.class);
	
	@Autowired
	private IArrangeService tarrangeService;
	
	@Autowired
	private ICourseService courseService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IArrangeUserService tarrangeuserService;
	
	@Autowired
	private ArrangeTransformer tarrangeTransformer;
	
	@Autowired
	private CacheManager cacheManager;
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap){
		return "/training/arrange/list";
	}
	
	@RequestMapping("/show/{id}")
	public String show(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get show id: " + id);
		List<TCourse> courses = this.courseService.listActive();
		modelMap.addAttribute("courses", courses);
		
		List<TUser> coachers = this.userService.list();
		modelMap.addAttribute("coachers", coachers);
		
		if(id != null){
			TArrange model = tarrangeService.load(id);
			modelMap.addAttribute("model", model);
		}
		return "/training/arrange/show";
	}
	
	@RequestMapping("/loaduser")
	public @ResponseBody Map<String, Object> loaduser(Long id, String type){
		
		Map<String, Object> map  = new HashMap<String, Object>();
		List<Map<String, Object>> rowsData = new ArrayList<Map<String, Object>>();
		Boolean assigned = "assigned".equals(type);
		
		Long total = tarrangeuserService.getSize4Arrange(id, assigned);
		if(total > 0){
			List<TUser> list = tarrangeuserService.listUsers4Arrange(id, assigned);
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
	
	@RequestMapping("/create")
	public String create(ModelMap modelMap, ArrangePojo model){
		TArrange arrange = this.tarrangeTransformer.transform(model);
		if(model.getUserId() != null && model.getUserId() > 0){
			TUser user = userService.load(model.getUserId());
			arrange.setCoacher(user);
		}
		if(model.getCourseId() != null){
			TCourse course =  this.courseService.load(model.getCourseId());
			arrange.setCourse(course);
		}
		arrange.setStatus(Const.ArrangeStatus.PENDING);
		tarrangeService.create(arrange);
		
		if(StringUtil.isNotBlank(model.getUserIds())){
			List<Long> idList = StringUtil.split4Long(model.getUserIds(), Const.COMMA_DELIMITER);
			for(Long uid : idList){
				TUser user = userService.load(uid);
				TArrangeUser arrUser =  new TArrangeUser();
				arrUser.setArrange(arrange);
				arrUser.setStatus(Const.ArrangeUserStatus.DRAFT);
				arrUser.setUser(user);
				arrUser.setVcode(getVCode());
				arrUser.setUcode(StringUtil.uuid());
				arrUser.setBarcodeImage(barcodeText(arrUser));
				String barCode = this.tarrangeuserService.generateBarCode(arrUser.getUser().getId(), barcodeText(arrUser));
				arrUser.setBarcodeImage(barCode);
				tarrangeuserService.create(arrUser);
			}
		}
		return "/success";
	}
	
	@RequestMapping("/assign/{id}")
	public String assign(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get show id: " + id);
		if(id != null){
			TCourse model = courseService.load(id);
			modelMap.addAttribute("model", model);
		}
		
		return "/training/arrange/assign";
	}
	
	@RequestMapping("/listdata")
	public @ResponseBody Map<String, Object> listData(){
		LOGGER.info("Get list data.");
		List<TArrange> listdata = tarrangeService.list();
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("sEcho", 1);  
		map.put("iTotalRecords", listdata.size());  
        map.put("iTotalDisplayRecords", listdata.size());  
		map.put("aaData", listdata);
		
		return map;
	}
	
	@RequestMapping("/update/{id}")
	public String update(ModelMap modelMap, @PathVariable Long id, ArrangePojo model){
		LOGGER.info("Get update id: " + id);
		LOGGER.info("Update id: " + id);
		if(id != null){
			TArrange dbRecord = tarrangeService.load(id);
			this.tarrangeTransformer.update(model, dbRecord);

			if(model.getCourseId() != null && model.getCourseId() > 0){
				TCourse course = this.courseService.load(model.getCourseId());
				dbRecord.setCourse(course);
			}
			
			if(model.getUserId() != null && model.getUserId() > 0){
				TUser user = userService.load(model.getUserId());
				dbRecord.setCoacher(user);
			}
			tarrangeService.update(dbRecord);
			
			List<TArrangeUser> list = this.tarrangeuserService.list(dbRecord.getId());
			List<Long> idList = StringUtil.split4Long(model.getUserIds(), Const.COMMA_DELIMITER);
			
			//for new add
			if(StringUtil.isNotBlank(model.getUserIds())){
				for(Long uid : idList){
					boolean exist = false;
					for(TArrangeUser arrangeUser : list){
						if(arrangeUser.getUser() != null && arrangeUser.getUser().getId().equals(uid)){
							exist = true;
							break;
						}
					}
					if(!exist){
						TArrangeUser arrangeUser = new TArrangeUser();
						arrangeUser.setArrange(dbRecord);
						arrangeUser.setStatus(Const.ArrangeUserStatus.DRAFT);
						TUser user = userService.load(uid);
						arrangeUser.setUser(user);
						arrangeUser.setVcode(getVCode());
						arrangeUser.setUcode(StringUtil.uuid());
						arrangeUser.setBarcodeImage(barcodeText(arrangeUser));
						String barCode = this.tarrangeuserService.generateBarCode(arrangeUser.getUser().getId(), barcodeText(arrangeUser));
						arrangeUser.setBarcodeImage(barCode);
						tarrangeuserService.create(arrangeUser);
					}
				}
			}
			
			
			//for remove
			for(TArrangeUser arrangeUser : list){
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
					this.tarrangeuserService.update(arrangeUser);
					//this.karrangeuserService.delete(arrangeUser);
				}
			}
		}
		return "/success";
	}

	@RequestMapping("/vcode/{id}")
	public String vcode(ModelMap modelMap, @PathVariable Long id){
		if(id != null){
			List<TArrangeUser> list = this.tarrangeuserService.list(id);
			modelMap.addAttribute("list", list);
		}
		return "/knowledge/arrange/vcode";
	}
	
	@RequestMapping("/record/{id}")
	public String record(ModelMap modelMap, @PathVariable Long id){
		if(id != null){
			List<TArrangeUser> list = this.tarrangeuserService.list(id);
			modelMap.addAttribute("list", list);
		}
		return "/knowledge/arrange/record";
	}
	
	private String barcodeText(TArrangeUser arrUser){
		String path = request.getContextPath();  
		String FQDN = cacheManager.get(Const.FQDN);
		String basePath = FQDN+path+"/mi/main/";
		//String text = basePath + "sign.htm?type=EX&i=" + arrUser.getId() + "&u=" + arrUser.getUser().getId() + "&v=" + arrUser.getVcode();
		String text = basePath + "sign/TR/" + arrUser.getUcode() + ".htm";
		return text;
	}
	
	private String getVCode() {
		String vcode = "";
        Random random = new Random();
        for(int i=0; i<6; i++){
        	int numberResult = random.nextInt(10);
            int ret = numberResult + 48;
            vcode += (char) ret;
        }
        return vcode;
    }

}
