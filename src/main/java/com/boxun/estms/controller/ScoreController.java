package com.boxun.estms.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boxun.estms.entity.EIndex;
import com.boxun.estms.entity.EScore;
import com.boxun.estms.service.IIndexService;
import com.boxun.estms.service.IScoreService;

@Controller
@RequestMapping("/admin/score")
public class ScoreController {

	private static final Logger LOGGER = Logger.getLogger(ScoreController.class);
	
	@Autowired
	private IScoreService scoreService;
	
	@Autowired
	private IIndexService indexService;
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap){
		List<EIndex> list = indexService.listTops();
		modelMap.addAttribute("list", list);
		return "/admin/score/list";
	}
	
	@RequestMapping("/show/{id}")
	public String show(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get id: " + id);
		if(id != null){
			EScore score = scoreService.load(id);
			modelMap.addAttribute("score", score);
		}
		return "/admin/score/show";
	}
}
