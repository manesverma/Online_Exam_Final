 package com.lti.online_exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lti.online_exam.exception.ExamException;
import com.lti.online_exam.model.Login;
import com.lti.online_exam.service.ILoginService;
import com.lti.online_exam.service.IUserService;


@Controller
@RequestMapping(value="/login")
public class LoginController {
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ILoginService loginService;
	
	@RequestMapping(value="/showLogin",method=RequestMethod.GET)
	public ModelAndView getLoginPage() {
		Login loginObj = new Login();
		String viewName="loginPage";
		String modelName="loginObj";
		return new ModelAndView(viewName, modelName, loginObj);
	}
	
	
	@RequestMapping(value="/checkLogin",method=RequestMethod.POST)
	public ModelAndView checkLogin(
			@ModelAttribute(value="loginObj") @Validated Login loginObj,
			BindingResult bindingResult, ModelAndView model) throws ExamException {
		String viewName="";
		if(bindingResult.hasErrors()) {//validations
			model.addObject("msg", "Login Failed!");
			model.addObject("loginObj", new Login());
			viewName="redirect:showLogin";
			model.setViewName(viewName);
			//prefix redirect is used to redirect from one controller method to the another method
		}
		if(userService.authenticateUser(loginObj)) {
			if (loginObj.getLoginRole().equals("admin")) {
				model.addObject("loginObj", loginObj);
				model.addObject("msg", "Login Successful!");
				viewName = "adminFrontPage";
				model.setViewName(viewName);
			}
			if (loginObj.getLoginRole().equals("user")) {
				model.addObject("loginObj", loginObj);
				model.addObject("msg", "Login Successful!");
				viewName = "examFrontPage";
				model.setViewName(viewName);
			}
			
		}else {
			model.addObject("loginObj", new Login());
			model.addObject("msg", "Login Failed Invalid Credentials!");
			model.addObject("error", "Login Failed Invalid Credentials!"); 
			viewName="redirect:showLogin";
			model.setViewName(viewName);
		}		
		return model;
	}
	@RequestMapping(value="/forgotPassword")
	public String forgotPasswordPage() throws ExamException {
		return "forgotPasswordPage";
	}
	
	@RequestMapping(value="/forgotPasswordSendEmail")
	public String forgotPasswordPage(String forgotPassword,Model model) throws ExamException {
		
		if(loginService.forgotPassword(forgotPassword)) {
			System.out.println("\n\n\n"+forgotPassword+"\n\n\n");
			return "loginPage";
		}		
		else
			return "forgotPasswordPage";
	}
}










