package com.boxun.pcdp.capacity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boxun.pcdp.basic.controller.BaseController;
import com.boxun.pcdp.capacity.entity.CEvaluateArrange;
import com.boxun.pcdp.capacity.entity.CEvaluateArrangeUser;
import com.boxun.pcdp.capacity.service.IEvaluateArrangeService;
import com.boxun.pcdp.capacity.service.IEvaluateArrangeUserService;

@Controller("cResultController")
@RequestMapping("/system/capacity/result")
public class ResultController extends BaseController{

	@Autowired
	private IEvaluateArrangeService evaluatearrangeService;
	
	@Autowired
	private IEvaluateArrangeUserService carrangeuserService;
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap, Long arrId){
		List<CEvaluateArrange> arranges = evaluatearrangeService.list();
		modelMap.addAttribute("arranges", arranges);
		
		if(arrId == null){
			if(arranges != null && !arranges.isEmpty()){
				arrId = arranges.get(0).getId();
			}
			else{
				arrId = 0l;
			}
			
		}
		modelMap.addAttribute("arrId", arrId);
		
		if(arrId != null){
//			KArrange arrange = this.carrangeuserService.load(arrId);
			List<CEvaluateArrangeUser> list = this.carrangeuserService.list(arrId);
			modelMap.addAttribute("list", list);
		}
		
		return "/capacity/result/list";
	}

}
