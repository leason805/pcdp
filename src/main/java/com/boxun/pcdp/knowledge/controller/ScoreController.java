package com.boxun.pcdp.knowledge.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boxun.estms.pojo.UserInfo;
import com.boxun.pcdp.basic.controller.BaseController;
import com.boxun.pcdp.knowledge.entity.KArrangeUser;
import com.boxun.pcdp.knowledge.entity.KExamScore;
import com.boxun.pcdp.knowledge.service.IArrangeUserService;
import com.boxun.pcdp.knowledge.service.IExamScoreService;

@Controller
@RequestMapping("/system/knowledge/score")
public class ScoreController extends BaseController{

	@Autowired
	private IArrangeUserService karrangeuserService;
	
	@Autowired
	private IExamScoreService examScoreService;
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap){
		return "/knowledge/score/list";
	}
	
	@RequestMapping("/listdata")
	public @ResponseBody Map<String, Object> listData(){
		UserInfo userInfo = (UserInfo)this.session.getAttribute("userInfo");
		Long userId = 0L;
		if(userInfo != null){
			userId = userInfo.getUserId();
		}
		List<KArrangeUser> listdata = karrangeuserService.list4User(userId, true);
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("sEcho", 1);  
		map.put("iTotalRecords", listdata.size());  
        map.put("iTotalDisplayRecords", listdata.size());  
		map.put("aaData", listdata);
		
		return map;
	}
	
	@RequestMapping("/detail/{id}")
	public String detail(ModelMap modelMap, @PathVariable Long id){
		KExamScore score = this.examScoreService.loadByArrangeUser(id);
		modelMap.addAttribute("model", score);
		return "/knowledge/score/detail";
	}
	
	@RequestMapping("/print/{id}")
	public String print(ModelMap modelMap, @PathVariable Long id){
		KExamScore score = this.examScoreService.loadByArrangeUser(id);
		modelMap.addAttribute("model", score);
		return "/knowledge/score/print";
	}
}
