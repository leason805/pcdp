package com.boxun.pcdp.basic.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;

public class BaseController {

	protected HttpServletRequest request;  
    protected HttpServletResponse response;  
    protected HttpSession session;
     
    @ModelAttribute
    public void preRun() {
        System.out.println("Test Pre-Run");
    }
    
    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){  
    	System.out.println("Test setReqAndRes");
        this.request = request;
        this.response = response;
        this.session = request.getSession();
    }
}
