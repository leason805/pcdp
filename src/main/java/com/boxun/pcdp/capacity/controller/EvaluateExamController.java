package com.boxun.pcdp.capacity.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boxun.estms.entity.Const;
import com.boxun.estms.pojo.UserInfo;
import com.boxun.estms.util.StringUtil;
import com.boxun.pcdp.admin.service.IUserService;
import com.boxun.pcdp.basic.controller.BaseController;
import com.boxun.pcdp.capacity.entity.CEvaluateAnswer;
import com.boxun.pcdp.capacity.entity.CEvaluateArrangeUser;
import com.boxun.pcdp.capacity.entity.CEvaluateQuestion;
import com.boxun.pcdp.capacity.service.IEvaluateAnswerService;
import com.boxun.pcdp.capacity.service.IEvaluateArrangeService;
import com.boxun.pcdp.capacity.service.IEvaluateArrangeUserService;
import com.boxun.pcdp.capacity.service.IEvaluateProjectService;
import com.boxun.pcdp.capacity.service.IEvaluateQuestionService;
import com.boxun.pcdp.capacity.transformer.ArrangeTransformer;

@Controller
@RequestMapping("/system/capacity/exam")
public class EvaluateExamController extends BaseController{

	private static final Logger LOGGER = Logger.getLogger(EvaluateExamController.class);

	@Autowired
	private IEvaluateArrangeService evaluatearrangeService;
	
	@Autowired
	private IEvaluateProjectService evaluateprojectService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ArrangeTransformer carrangeTransformer;
	
	
	@Autowired
	private IEvaluateArrangeUserService carrangeuserService;
	
	@Autowired
	private IEvaluateQuestionService evaluatequestionService;
	
	@Autowired
	private IEvaluateAnswerService evaluateanswerService;
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap){
		return "/capacity/exam/list";
	}
	
	@RequestMapping("/listdata")
	public @ResponseBody Map<String, Object> listData(){
		LOGGER.info("Get list data.");
		UserInfo userInfo = (UserInfo)this.session.getAttribute("userInfo");
		Long userId = 0L;
		if(userInfo != null){
			userId = userInfo.getUserId();
		}
		List<CEvaluateArrangeUser> listdata = carrangeuserService.list4User(userId, false);
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
			CEvaluateArrangeUser model = carrangeuserService.load(id);
			modelMap.addAttribute("model", model);
		}
		
		return "/capacity/exam/show";
	}
	
	@RequestMapping("/sign/{id}")
	public String sign(ModelMap modelMap, @PathVariable Long id, @RequestParam String vcode){
		boolean success = false;
		
		if(id != null){
			CEvaluateArrangeUser model = carrangeuserService.load(id, vcode);
			if(model != null){
				model.setSignTime(new Date());
				model.setStatus(Const.ArrangeUserStatus.SIGNED);
				this.carrangeuserService.update(model);
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
	
	@RequestMapping("/exam/{id}")
	public String exam(ModelMap modelMap, @PathVariable Long id){
		if(id != null){
			CEvaluateArrangeUser model = carrangeuserService.load(id);
			modelMap.addAttribute("model", model);
			
			List<CEvaluateQuestion> questions = this.evaluatequestionService.list(model.getArrange().getProject().getId());
			modelMap.addAttribute("questions", questions);
		}
		
		return "/capacity/exam/exam";
	}
	
	@RequestMapping("/submit/{id}")
	public String submit(ModelMap modelMap, @PathVariable Long id){
		CEvaluateArrangeUser model = carrangeuserService.load(id);
		
		String paramName = null;
		String paramValue = null;
		String qid = null;
		CEvaluateQuestion question = null;
		
		Enumeration<String> enu = this.request.getParameterNames();  
		while(enu.hasMoreElements()){  
			qid = null;
			
			paramName = enu.nextElement();  
			paramValue = this.request.getParameter(paramName);
			if(StringUtil.isNotBlank(paramValue)){
				qid = paramName.split("_")[1];
				question = this.evaluatequestionService.load(Long.valueOf(qid));
				CEvaluateAnswer answer = new CEvaluateAnswer();
				answer.setArrangeUser(model);
				answer.setQuestion(question);
				answer.setAnswer(paramValue);
				
				this.evaluateanswerService.create(answer);
			}
		}
		
		model.setStatus(Const.ArrangeUserStatus.COMPLETED);
		this.carrangeuserService.update(model);
		
		modelMap.addAttribute("refresh", "page");
		modelMap.addAttribute("forwindow", true);
		modelMap.addAttribute("message", "考核完成。");
		modelMap.addAttribute("url", "/system/capacity/exam/list");
		return "/success";
	}
	
	@RequestMapping("/detail/{id}")
	public String detail(ModelMap modelMap, @PathVariable Long id){
		if(id != null){
			CEvaluateArrangeUser model = carrangeuserService.load(id);
			modelMap.addAttribute("model", model);
			
			List<CEvaluateAnswer> answers = this.evaluateanswerService.list(model.getId());
			modelMap.addAttribute("answers", answers);
		}
		
		return "/capacity/exam/detail";
	}
}
