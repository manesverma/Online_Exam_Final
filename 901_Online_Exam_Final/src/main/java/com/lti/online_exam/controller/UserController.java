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
import com.lti.online_exam.model.User;
import com.lti.online_exam.service.ILoginService;
import com.lti.online_exam.service.IUserService;


@Controller
@RequestMapping(value="/user")
public class UserController {
		@Autowired
		private IUserService userService;
		
		@Autowired
		private ILoginService loginService;
		
		@RequestMapping(value="/addUserForm")
		public ModelAndView showAddUserForm() {
			ModelAndView mv = new ModelAndView();
			User user = new User();
			mv.addObject("title", "Add New Login Page");
			mv.addObject("user", user);
			mv.setViewName( "addUserPage");
			return mv;
		}
		@RequestMapping(value="/addUser",method=RequestMethod.POST)
		public String addUser( @ModelAttribute(value="user") 
									@Validated User user,
									BindingResult bindingResult, 
									Model model) throws ExamException {
			String viewName="home";
			/*Address userAddress = new Address("sector-50", "Noida", "UP", "India", user);
			user.setUserAddress(userAddress);*/
			if(bindingResult.hasErrors()) {//validations
				model.addAttribute("msg", "Adding User Failed!");
				model.addAttribute("user", new User());
				viewName="redirect:addUserForm";
			}
			Login login = new Login();
			login.setLoginRole("user");
			login.setloginUserEmail(user.getUserEmail());
			login.setLoginPassword(user.getUserPassword());
			userService.addUser(user);
			loginService.addLogin(login);
			model.addAttribute("user",user);
			return viewName; 
		}
}
