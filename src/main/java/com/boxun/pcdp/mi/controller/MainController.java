package com.boxun.pcdp.mi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boxun.estms.entity.Const;
import com.boxun.pcdp.archive.entity.AUserInfo;
import com.boxun.pcdp.archive.service.IUserInfoService;
import com.boxun.pcdp.knowledge.entity.KArrangeUser;
import com.boxun.pcdp.knowledge.entity.KProject;
import com.boxun.pcdp.knowledge.entity.KQuestion;
import com.boxun.pcdp.knowledge.entity.KSection;
import com.boxun.pcdp.knowledge.service.IArrangeUserService;
import com.boxun.pcdp.knowledge.service.IQuestionService;
import com.boxun.pcdp.mi.pojo.MiArrangePojo;
import com.boxun.pcdp.mi.pojo.MiExamArrangePojo;
import com.boxun.pcdp.mi.pojo.MiExamSetPojo;
import com.boxun.pcdp.mi.pojo.MiTrainingArrangePojo;
import com.boxun.pcdp.mi.pojo.MiUserPojo;
import com.boxun.pcdp.mi.transformer.MiExamSetTransformer;
import com.boxun.pcdp.mi.transformer.MiExamTransformer;
import com.boxun.pcdp.mi.transformer.MiKArrangeTransformer;
import com.boxun.pcdp.mi.transformer.MiTArrangeTransformer;
import com.boxun.pcdp.mi.transformer.MiTrainingTransformer;
import com.boxun.pcdp.mi.transformer.MiUserTransformer;
import com.boxun.pcdp.training.entity.TArrangeUser;

@Controller("MiMainController")
@RequestMapping("/mi/main")
public class MainController {

	@Autowired
	private MiUserTransformer miUserTransformer;
	
	@Autowired
	private MiExamTransformer miExamTransformer;
	
	@Autowired
	private MiTrainingTransformer miTrainingTransformer;
	
	@Autowired
	private IUserInfoService userinfoService;
	
	@Autowired
	private IArrangeUserService karrangeuserService;
	
	@Autowired
	private com.boxun.pcdp.training.service.IArrangeUserService tarrangeuserService;
	
	@Autowired
	private IQuestionService questionService;
	
	@Autowired
	private MiKArrangeTransformer miKArrangeTransformer;
	
	@Autowired
	private MiTArrangeTransformer miTArrangeTransformer;
	
	@Autowired
	private MiExamSetTransformer miExamSetTransformer;
	
	@RequestMapping("/userinfo")
	public @ResponseBody Map<String, Object> index(@RequestParam Long uid, @RequestParam String utime, @RequestParam String umd5){
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("status", "fail");
		if(uid != null){
			AUserInfo model = this.userinfoService.loadByUser(uid);
			if(model != null){
				MiUserPojo user = this.miUserTransformer.convert(model);
				map.put("user", user);
				map.put("status", "success");
			}
			else{
				map.put("message", "用户不能识别，请重新登录。");
			}
		}
		else{
			map.put("message", "用户不能识别，请重新登录。");
		}
		return map;
	}
	
	/*
	@RequestMapping("/startexam")
	public @ResponseBody Map<String, Object> startexam(@RequestParam Long param1){
		List<KQuestion> questions = new ArrayList<KQuestion>();
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("status", "fail");
		if(param1 != null){
			KArrangeUser model = karrangeuserService.load(param1);
			if(model != null){
				KProject project = model.getArrange().getProject();
				
				boolean validate = true;
				
				for(KSection section : project.getSections()){
					List<KQuestion> all = this.questionService.listBySectionId(section.getId());
					if(all.size() < (project.getSize() * (section.getWeight()/100d))){
						validate = false;
						break;
					}
					questions.addAll(random(project.getSize() * (section.getWeight()/100d), all));
				}
			}
			MiExamSetPojo examset = miExamSetTransformer.convert(questions);
			map.put("examset", examset);
			map.put("status", "success");
		}
		
		return map;
	}
	*/
	private static List<KQuestion> random(Double weight, List<KQuestion> list){
		List<KQuestion> result =  new ArrayList<KQuestion>();
		Random r = new Random();
		Set<KQuestion> set =  new HashSet<KQuestion>();
		while(set.size() < weight){
			set.add(list.get(r.nextInt(list.size())));
		}
		result.addAll(set);
		return result;
	}
	
	@RequestMapping("/examlist")
	public @ResponseBody Map<String, Object> examlist(@RequestParam Long uid, @RequestParam String utime, @RequestParam String umd5){
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("status", "fail");
		if(uid != null){
			List<KArrangeUser> listArr = karrangeuserService.list4User(uid);
			List<MiExamArrangePojo> list = new ArrayList<MiExamArrangePojo>();
			for(KArrangeUser aru : listArr){
				list.add(this.miExamTransformer.convert(aru));
			}
			map.put("list", list);
			map.put("status", "success");
		}
		else{
			System.out.println("no uid");
			map.put("message", "用户不能识别，请重新登录。");
		}
		return map;
	}
	
	@RequestMapping("/traininglist")
	public @ResponseBody Map<String, Object> traininglist(@RequestParam Long uid, @RequestParam String utime, @RequestParam String umd5){
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("status", "fail");
		if(uid != null){
			List<TArrangeUser> listArr = tarrangeuserService.list4User(uid);
			List<MiTrainingArrangePojo> list = new ArrayList<MiTrainingArrangePojo>();
			for(TArrangeUser aru : listArr){
				list.add(this.miTrainingTransformer.convert(aru));
			}
			map.put("list", list);
			map.put("status", "success");
		}
		else{
			System.out.println("no uid");
			map.put("message", "用户不能识别，请重新登录。");
		}
		return map;
	}
	
	/**
	 * 
	 * @param type  type of sign request
	 * @param i id of arrange
	 * @param u user id
	 * @param v vcode 
	 * @return
	 */
	@RequestMapping("/sign/{type}/{uuid}")
	public @ResponseBody Map<String, Object> sign(@PathVariable String type, @PathVariable String uuid){
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("status", "fail");
		if(type != null && uuid != null){
			MiArrangePojo pojo = null;
			
			if("EX".equals(type)){
				KArrangeUser arr = this.karrangeuserService.loadByUUID(uuid);
				if(arr != null){
					pojo = this.miKArrangeTransformer.convert(arr);
					arr.setStatus(Const.ArrangeUserStatus.SIGNED);
					this.karrangeuserService.update(arr);
				}
			}
			if("TR".equals(type)){
				TArrangeUser arr = this.tarrangeuserService.loadByUUID(uuid);
				if(arr != null){
					pojo = this.miTArrangeTransformer.convert(arr);
					arr.setStatus(Const.ArrangeUserStatus.SIGNED);
					this.tarrangeuserService.update(arr);
				}
			}

			if(pojo != null){
				map.put("arrange", pojo);
				map.put("status", "success");
			}
		}
		return map;
	}
}
