package com.lti.online_exam.controller;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lti.online_exam.exception.ExamException;
import com.lti.online_exam.model.Question;
import com.lti.online_exam.service.IQuestionService;

@Controller 
@RequestMapping(value="/exam")
public class ExamController {

	@Autowired
	private IQuestionService questionService;
	@RequestMapping(value="/examFront")
	public String showExamHomePage(Model model) {
		//return model and view
		model.addAttribute("msg", "Select any one subject");
		model.addAttribute("today",LocalDate.now());
		//model object associated with name 'msg'
		return "examFrontPage";//view name which will be returned to dispacherServlet
	}
	@RequestMapping(value="/java")
	public ModelAndView showJavaPage(HttpSession httpSession) {
		ModelAndView mv = new ModelAndView();
		httpSession.setAttribute("subjectName", "Java");
		//return model and view
		mv.addObject("title", "Java Exam Page");
		mv.setViewName( "examPage");
		return mv;//view name which will be returned to dispacherServlet
	}
	@RequestMapping(value="/sql")
	public ModelAndView showSqlPage(HttpSession httpSession) {
		ModelAndView mv = new ModelAndView();
		httpSession.setAttribute("subjectName", "SQL");
		//return model and view
		mv.addObject("title", "SQL Exam Page");
		mv.setViewName( "examPage");
		return mv;//view name which will be returned to dispacherServlet
	}
	@RequestMapping(value="/python")
	public ModelAndView showPythonPage(HttpSession httpSession) {
		ModelAndView mv = new ModelAndView();
		httpSession.setAttribute("subjectName", "Python");
		//return model and view
		mv.addObject("title", "Pyhton Exam Page");
		mv.setViewName( "examPage");
		return mv;//view name which will be returned to dispacherServlet
	}
	@RequestMapping(value="/c")
	public ModelAndView showCPage(HttpSession httpSession) {
		ModelAndView mv = new ModelAndView();
		httpSession.setAttribute("subjectName", "C/C++");
		//return model and view
		mv.addObject("title", "C/C++ Exam Page");
		mv.setViewName( "examPage");
		return mv;//view name which will be returned to dispacherServlet
	}
	@RequestMapping(value="/php")
	public ModelAndView showPhpPage(HttpSession httpSession) {
		ModelAndView mv = new ModelAndView();
		httpSession.setAttribute("subjectName", "PHP");
		//return model and view
		mv.addObject("title", "PHP Exam Page");
		mv.setViewName( "examPage");
		return mv;//view name which will be returned to dispacherServlet
	}
	@RequestMapping(value="/cSharp")
	public ModelAndView showCHashPage(HttpSession httpSession) {
		ModelAndView mv = new ModelAndView();
		httpSession.setAttribute("subjectName", "C#/.Net");
		//return model and view
		mv.addObject("title", "C#/.Net Exam Page");
		mv.setViewName( "examPage");
		return mv;//view name which will be returned to dispacherServlet
	}

	@RequestMapping(value="/examStart")
	public String examStart(Model model) throws ExamException {
		List<Question> questionList=questionService.getSubjectQuestionList();
		if(questionList.isEmpty())
			model.addAttribute("msg", "There are no Question in the Question Bank"); 
        model.addAttribute("questionList",questionList);
		return "examStartPage";
	}
}







