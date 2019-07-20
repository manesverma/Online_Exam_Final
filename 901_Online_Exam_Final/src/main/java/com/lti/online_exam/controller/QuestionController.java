package com.lti.online_exam.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lti.online_exam.exception.ExamException;
import com.lti.online_exam.model.Question;
import com.lti.online_exam.service.IQuestionService;

@Controller 
@RequestMapping(value="/question")
public class QuestionController {
	@Autowired
	private IQuestionService questionService;
	
	@RequestMapping(value="/questionPage")
	public ModelAndView questionPage() throws ExamException {
		return new ModelAndView("addSingleQuesPage", "question", new Question());
	}
	
	@RequestMapping(value="/addSingleQues")
	public ModelAndView showSingleQuesPage(@ModelAttribute(value="question") 
																				@Validated Question question,
																				BindingResult bindingResult, 
																				ModelAndView model) throws ExamException {
		String viewName="";
		if(bindingResult.hasErrors()) {//validations
			model.addObject("msg", "Adding Question Failed!");
			model.addObject("question", new Question());
			viewName="redirect:addSingleQues";
			model.setViewName(viewName);
		}
		questionService.addQuestion(question);
		model.addObject("msg", "Adding Question Sucessfully!");
		model.addObject("question",new Question());
		viewName="redirect:questionPage";
		model.setViewName(viewName);
		return model;
	}
	
	@RequestMapping(value="/addSingleQuesFile")
	public ModelAndView showFileUploadPage() {
		ModelAndView mv = new ModelAndView();
		//return model and view
		mv.addObject("title", "Upload a Question File");
		mv.setViewName( "addSingleQuesFilePage");
		return mv;//view name which will be returned to dispacherServlet
	}
	
	@RequestMapping(value="/removeQuestion/{questionId}",method = RequestMethod.GET)
    public String removeQuestion(@PathVariable("questionId") Integer questionId,Model model) throws ExamException{
        this.questionService.removeQuestion(questionId);
        model.addAttribute("question", new Question());
        List<Question> questionList=questionService.getQuestionList();
        model.addAttribute("questionList",questionList);
        return "removeQuesPage";
    }
	/*@RequestMapping(value = "/removeQuestion",method = RequestMethod.GET)
	public String removeQues(Model model) throws ExamException {
		model.addAttribute("question", new Question());
		List<Question> questionList=questionService.getQuestionList();
		
		//questionList.forEach((data)->System.out.println(data)); 
		model.addAttribute("questionList",questionList);
		return "removeQuesPage";
	}*/
	
	//@Resource(name = "excelPOIHelper")
	//private ExcelPOIHelper excelPOIHelper;
	
	@RequestMapping(value = "/displayExcelQuestion",method = RequestMethod.POST)
	public String displayExcelQuestion(Model model) throws ExamException {
		//model.addAttribute("question", new Question());
		//List<Question> questionList=questionService.getQuestionList();
		
		//questionList.forEach((data)->System.out.println(data)); 
		//model.addAttribute("questionList",questionList);
		return "displayExcelQuestionPage";
	} 
}
