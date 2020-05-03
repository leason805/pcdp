package com.boxun.pcdp.training.controller;

import java.util.Date;
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
import com.boxun.estms.pojo.UserInfo;
import com.boxun.pcdp.basic.controller.BaseController;
import com.boxun.pcdp.training.entity.TArrangeUser;
import com.boxun.pcdp.training.service.IArrangeUserService;


@Controller
@RequestMapping("/system/training/train")
public class TrainingController extends BaseController{

	@Autowired
	private IArrangeUserService tarrangeuserService;
	
	private static final Logger LOGGER = Logger.getLogger(TrainingController.class);
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap){
		return "/training/train/list";
	}
	
	@RequestMapping("/listdata")
	public @ResponseBody Map<String, Object> listData(){
		LOGGER.info("Get list data.");
		UserInfo userInfo = (UserInfo)this.session.getAttribute("userInfo");
		Long userId = 0L;
		if(userInfo != null){
			userId = userInfo.getUserId();
		}
		List<TArrangeUser> listdata = tarrangeuserService.list4User(userId);
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
			TArrangeUser model = tarrangeuserService.load(id);
			modelMap.addAttribute("model", model);
		}
		
		return "/training/train/show";
	}
	
	
	@RequestMapping("/sign/{id}")
	public String sign(ModelMap modelMap, @PathVariable Long id, @RequestParam String vcode){
		
		UserInfo userInfo = (UserInfo)this.session.getAttribute("userInfo");
		Long userId = 0L;
		if(userInfo != null){
			userId = userInfo.getUserId();
		};
		
		boolean success = false;
		
		if(id != null){
			TArrangeUser model = tarrangeuserService.load(id, vcode);
			if(model != null){
				model.setSignTime(new Date());
				model.setStatus(Const.ArrangeUserStatus.SIGNED);
				this.tarrangeuserService.update(model);
				success = true;
			}
		}
		
		String url = "/success";
		if(!success){
			url = "/result";
			modelMap.addAttribute("message", "请输入正确的签到码！");
		}
		
		
		return url;
	}
}
