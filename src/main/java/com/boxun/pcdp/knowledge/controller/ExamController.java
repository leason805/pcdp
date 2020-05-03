package com.boxun.pcdp.knowledge.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

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
import com.boxun.pcdp.admin.entity.TUser;
import com.boxun.pcdp.admin.service.IUserService;
import com.boxun.pcdp.basic.controller.BaseController;
import com.boxun.pcdp.knowledge.entity.KArrangeUser;
import com.boxun.pcdp.knowledge.entity.KExamAnswer;
import com.boxun.pcdp.knowledge.entity.KExamScore;
import com.boxun.pcdp.knowledge.entity.KOption;
import com.boxun.pcdp.knowledge.entity.KPaper;
import com.boxun.pcdp.knowledge.entity.KPaperItem;
import com.boxun.pcdp.knowledge.entity.KQuestion;
import com.boxun.pcdp.knowledge.entity.KSection;
import com.boxun.pcdp.knowledge.service.IArrangeUserService;
import com.boxun.pcdp.knowledge.service.IExamScoreService;
import com.boxun.pcdp.knowledge.service.IPaperService;
import com.boxun.pcdp.knowledge.service.IProjectService;
import com.boxun.pcdp.knowledge.service.IQuestionService;

@Controller
@RequestMapping("/system/knowledge/exam")
public class ExamController extends BaseController{

	private static final Logger LOGGER = Logger.getLogger(ExamController.class);
	
	@Autowired
	private IArrangeUserService karrangeuserService;
	
	@Autowired
	private IQuestionService questionService;
	
	@Autowired
	private IProjectService projectService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IExamScoreService examScoreService;
	
	@Autowired
	private IPaperService paperService;
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap){
		return "/knowledge/exam/list";
	}
	
	@RequestMapping("/listdata")
	public @ResponseBody Map<String, Object> listData(){
		LOGGER.info("Get list data.");
		UserInfo userInfo = (UserInfo)this.session.getAttribute("userInfo");
		Long userId = 0L;
		if(userInfo != null){
			userId = userInfo.getUserId();
		}
		List<KArrangeUser> listdata = karrangeuserService.list4User(userId, false);
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
			KArrangeUser model = karrangeuserService.load(id);
			modelMap.addAttribute("model", model);
		}
		
		return "/knowledge/exam/show";
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
			KArrangeUser model = karrangeuserService.load(id, vcode);
			if(model != null){
				model.setSignTime(new Date());
				model.setStatus(Const.ArrangeUserStatus.SIGNED);
				this.karrangeuserService.update(model);
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
		List<KQuestion> questions = new ArrayList<KQuestion>();
		
		if(id != null){
			KArrangeUser model = karrangeuserService.load(id);
			if(model != null){
				KPaper paper = model.getArrange().getPaper();
				
				boolean validate = true;
				
				KSection section = null;
				for(KPaperItem item : paper.getItems()){
					section = item.getSection();
					if(section != null){
						List<KQuestion> all = this.questionService.listBySectionId(section.getId());
						if(all.size() < item.getSize()){
							validate = false;
							break;
						}
						questions.addAll(random(item.getSize(), all));
					}
				}
//				
//				for(KSection section : project.getSections()){
//					List<KQuestion> all = this.questionService.listBySectionId(section.getId());
//					if(all.size() < (project.getSize() * (section.getWeight()/100d))){
//						validate = false;
//						break;
//					}
//					questions.addAll(random(project.getSize() * (section.getWeight()/100d), all));
//				}
				modelMap.addAttribute("model", model);
				modelMap.addAttribute("paper", paper);
				modelMap.addAttribute("questions", questions);
			}
		}
		
		return "/knowledge/exam/exam";
	}
	
	
	
	@RequestMapping("/submit/{id}")
	public String submit(ModelMap modelMap, @PathVariable Long id, @RequestParam String qids, @RequestParam Long paperId, @RequestParam String answers){
		
		
		KArrangeUser arrangeUser = null;
		KPaper paper = null;
		TUser user = null;
		UserInfo userInfo = (UserInfo)this.session.getAttribute("userInfo");
		user = this.userService.load(userInfo.getUserId());
		
		if(paperId != null){
			paper = this.paperService.load(paperId);
		}
		
		if(id != null){
			arrangeUser = karrangeuserService.load(id);
		}
		if(arrangeUser != null && Const.ArrangeUserStatus.COMPLETED.equals(arrangeUser.getStatus())){
			return "HAS_COMPLETED";
		}

		KExamScore score = new KExamScore();
		score.setCreateTime(new Date());
		score.setArrangeUser(arrangeUser);
		score.setPaper(paper);
		score.setScoreType(Const.ScoreType.VALID);
		
		List<Long> questionIdList = StringUtil.split4Long(qids, Const.COMMA_DELIMITER);
		List<KQuestion> questions = this.questionService.list(questionIdList);
		
		List<Long> answerList = this.options(answers);
		
		List<KExamAnswer> ansList = new ArrayList<KExamAnswer>();
		int order = 1;
		int scores = 0;
		for(KQuestion question : questions){
			KExamAnswer answer = createAnswer(user, question, answerList, order);
			answer.setScore(score);
			ansList.add(answer);
			if(Const.CorrectType.YES.equals(answer.getCorrectType())){
				scores++;
			}
			order++;
		}
	
		score.setAnswers(ansList);
		
		Double finalScore = StringUtil.getDouble(scores*(paper.getScore()*1d/paper.getSize()), 2);
		score.setScore(finalScore);
		if(paper.getPassscore() <= scores){
			score.setPassType(Const.PassType.YES);
		}
		else{
			score.setPassType(Const.PassType.NO);
		}
		
		this.examScoreService.create(score);
		
		arrangeUser.setStatus(Const.ArrangeUserStatus.COMPLETED);
		arrangeUser.setCompleteTime(new Date());
		this.karrangeuserService.update(arrangeUser);
		
		modelMap.addAttribute("refresh", "page");
		modelMap.addAttribute("forwindow", true);
		modelMap.addAttribute("message", "请进入“我的成绩”查看考试成绩。");
		modelMap.addAttribute("url", "/system/knowledge/exam/list");
		return "/success";
	}
	
	
	@RequestMapping("/record/{id}")
	public String record(ModelMap modelMap, @PathVariable Long id, @RequestParam String qids, @RequestParam Long paperId, @RequestParam String answers){
		return null;
	}
	
//	@RequestMapping("/detail/{id}")
//	public String detail(ModelMap modelMap, @PathVariable Long id){
//		KExamScore score = this.examScoreService.loadByArrangeUser(id);
//		modelMap.addAttribute("model", score);
//		return "/knowledge/exam/detail";
//	}
	
	private KExamAnswer createAnswer(TUser user, KQuestion question, List<Long> options, int order){
		
		KExamAnswer answer = new KExamAnswer();
		answer.setUser(user);
		answer.setQuestion(question);
		//answer.setScore(score);
		answer.setOrder(order);

		boolean allCorrect = true;
		List<KOption> optionList =  new ArrayList<KOption>();
		for(KOption option : question.getOptions()){
			if(options.contains(option.getId())){
				optionList.add(option);
			}
			
			if(option.getAnswerType().equals(Const.AnswerType.YES)){
				allCorrect &= options.contains(option.getId());
			}
			else{
				allCorrect &= !options.contains(option.getId());
			}
		}
		if(allCorrect){
			answer.setCorrectType(Const.CorrectType.YES);
		}
		else{
			answer.setCorrectType(Const.CorrectType.NO);
		}
		
		answer.setOptions(optionList);
		//score.addAnswer(answer);
		
		return answer;
	}
	
	private List<Long> options(String answers){
		List<Long> options =  new ArrayList<Long>();
		if(StringUtil.isNotBlank(answers)){
			List<String> list = StringUtil.split(answers, StringUtil.COMMA);
			for(String str : list){
				String[] arr =  str.split(StringUtil.UNDERLINE);
				if(arr.length == 2 && StringUtil.isNotBlank(arr[1])){
					options.add(Long.parseLong(arr[1]));
				}
			}
		}
		return options;
	}
	
	private static List<KQuestion> random(Integer weight, List<KQuestion> list){
		List<KQuestion> result =  new ArrayList<KQuestion>();
		Random r = new Random();
		Set<KQuestion> set =  new HashSet<KQuestion>();
		while(set.size() < weight){
			set.add(list.get(r.nextInt(list.size())));
		}
		result.addAll(set);
		return result;
	}
}
