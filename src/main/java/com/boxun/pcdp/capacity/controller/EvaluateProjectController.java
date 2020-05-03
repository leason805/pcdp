package com.boxun.pcdp.capacity.controller;

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

import com.boxun.pcdp.basic.controller.BaseController;
import com.boxun.pcdp.capacity.entity.CEvaluateProject;
import com.boxun.pcdp.capacity.entity.CEvaluateQuestion;
import com.boxun.pcdp.capacity.service.IEvaluateProjectService;
import com.boxun.pcdp.capacity.service.IEvaluateQuestionService;
import com.boxun.pcdp.estimate.entity.EIndicatorCategory;
import com.boxun.pcdp.estimate.service.IIndicatorCategoryService;

@Controller
@RequestMapping("/system/capacity/evaluateproject")
public class EvaluateProjectController extends BaseController{

private static final Logger LOGGER = Logger.getLogger(EvaluateProjectController.class);
	
	@Autowired
	private IEvaluateProjectService evaluateprojectService;
	
	@Autowired
	private IEvaluateQuestionService evaluatequestionService;
	
	@Autowired
	private IIndicatorCategoryService indicatorcategoryService;
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap){
		return "/capacity/evaluateproject/list";
	}
	
	@RequestMapping("/show/{id}")
	public String show(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get show id: " + id);
		if(id != null){
			CEvaluateProject model = evaluateprojectService.load(id);
			modelMap.addAttribute("model", model);
		}
		
		List<EIndicatorCategory> categories = indicatorcategoryService.listActive();
		modelMap.addAttribute("categories", categories);
		
		return "/capacity/evaluateproject/show";
	}
	
	@RequestMapping("/listdata")
	public @ResponseBody Map<String, Object> listData(){
		LOGGER.info("Get list data.");
		List<CEvaluateProject> listdata = evaluateprojectService.list();
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("sEcho", 1);  
		map.put("iTotalRecords", listdata.size());  
        map.put("iTotalDisplayRecords", listdata.size());  
		map.put("aaData", listdata);
		
		return map;
	}
	
	@RequestMapping("/create")
	public String create(ModelMap modelMap, CEvaluateProject model, Long categoryId){

		if(categoryId != null){
			EIndicatorCategory category = this.indicatorcategoryService.load(categoryId);
			model.setCategory(category);
		}
		evaluateprojectService.create(model);
		return "/success";
	}
	
	@RequestMapping("/update/{id}")
	public String update(ModelMap modelMap, @PathVariable Long id, CEvaluateProject model, Long categoryId){
		LOGGER.info("Get update id: " + id);
		LOGGER.info("Update id: " + id);
		if(id != null){
			CEvaluateProject dbRecord = evaluateprojectService.load(id);
			if(categoryId != null){
				EIndicatorCategory category = this.indicatorcategoryService.load(categoryId);
				dbRecord.setCategory(category);
			}
			dbRecord.setName(model.getName());
			dbRecord.setDescription(model.getDescription());
			evaluateprojectService.update(dbRecord);
		}
		return "/success";
	}
	
	
	@RequestMapping("/questions/{id}")
	public String questions(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get questions id: " + id);
		if(id != null){
			CEvaluateProject model = evaluateprojectService.load(id);
			modelMap.addAttribute("model", model);
			
			List<CEvaluateQuestion> questions = this.evaluatequestionService.list(id);
			modelMap.addAttribute("questions", questions);
		}
		return "/capacity/evaluateproject/questions";
	}
	
	@RequestMapping("/editquestion/{id}")
	public String editquestion(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("update id: " + id);
		if(id != null){
			CEvaluateQuestion model = this.evaluatequestionService.load(id);
			modelMap.addAttribute("model", model);
		}
		return "/capacity/evaluateproject/editquestion";
	}
	
	@RequestMapping("/questionupdate/{id}")
	public String questionupdate(ModelMap modelMap, @PathVariable Long id, String title, String description, String question, Long questid){
		LOGGER.info("update id: " + id);
		if(id != null){
			CEvaluateProject project = evaluateprojectService.load(id);
			CEvaluateQuestion ques = null;
			
			if(questid != null){
				ques = this.evaluatequestionService.load(questid);
			}
			else{
				ques = new CEvaluateQuestion();
				ques.setProject(project);
			}
			ques.setTitle(title);
			ques.setQuestion(question);
			ques.setDescription(description);
			this.evaluatequestionService.update(ques);
		}
		return "redirect:/system/capacity/evaluateproject/questions/"+id+".htm";
	}
	
	@RequestMapping("/delquestion/{id}")
	public String delquestion(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("update id: " + id);
		Long projectId = 0l;
		if(id != null){
			CEvaluateQuestion model = this.evaluatequestionService.load(id);
			if(model != null){
				projectId = model.getProject().getId();
			}
			this.evaluatequestionService.delete(model);
		}
		return "redirect:/system/capacity/evaluateproject/questions/"+projectId+".htm";
	}
}
