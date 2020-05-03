package com.boxun.pcdp.mi.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boxun.estms.util.Const;
import com.boxun.estms.util.DateUtil;
import com.boxun.estms.util.MD5;
import com.boxun.estms.util.StringUtil;
import com.boxun.pcdp.admin.entity.TUser;
import com.boxun.pcdp.admin.service.IUserService;

@Controller("MiIndexController")
@RequestMapping("/mi")
public class IndexController {

	@Autowired
	private IUserService userService;
	
	@RequestMapping("/login")
	public @ResponseBody Map<String, Object> index(@RequestParam String username, @RequestParam String password){
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("status", "fail");
		if(StringUtil.isNotBlank(username) && StringUtil.isNotBlank(password)){
			TUser user = userService.find(username, password);
			if(user == null){
				map.put("message", "用户名/密码不正确。");
			}
			else{
				String utime = DateUtil.formatDateTime(new Date(), Const.DATE_FORMAT_0);
				System.out.println(utime);
				String umd5 = user.getId() + "_" + utime + "_" + "pcdp";
				umd5 = MD5.compute(umd5);
				map.put("uid", user.getId());
				map.put("utime", utime);
				map.put("umd5", umd5);
				map.put("status", "success");
			}
		}
		else{
			map.put("message", "用户名/密码不能为空。");
		}
		return map;
	}
}
