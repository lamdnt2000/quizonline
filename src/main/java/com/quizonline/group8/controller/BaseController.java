package com.quizonline.group8.controller;

import com.quizonline.group8.mapper.impl.QuestionResponseModelMapper;
import com.quizonline.group8.mapper.resposemodel.ChoiceResponseModel;
import com.quizonline.group8.mapper.resposemodel.QuestionResponseModel;
import com.quizonline.group8.model.Subject;
import com.quizonline.group8.repository.SubjectRepository;
import com.quizonline.group8.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller()
public class BaseController {
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private QuestionResponseModelMapper questionResponseModelMapper;
    @Autowired
    private QuestionService questionService;

    @RequestMapping("/")
    public String index(){
        return showSubject();
    }

    @GetMapping("/doquiz")
    public String showQuestionInQuiz(){
        return "doquiz";
    }

    @GetMapping("/quizcategory")
    public String showQuizCategory(){
        return "quizcategory";
    }

    @GetMapping("/viewsubject")
    public String showSubject(){
        return "viewsubject";
    }

    @GetMapping("/header")
    public String getHeader(){
        return "header";
    }

    @GetMapping("/history")
    public ModelAndView getHistory(Model model){
        List<Subject> subjects = subjectRepository.findAll();
        model.addAttribute("subjects",subjects);
        return new ModelAndView("history");
    }

    @GetMapping("/admin/header")
    public String getHeaderAdmin(){
        return "admin/header";
    }

    @GetMapping("/admin/menu")
    public String getMenuAdmin(){
        return "admin/menu";
    }
    @GetMapping("/admin/footer")
    public String getFooterAdmin(){
        return "admin/footerAdmin";
    }

    @GetMapping("/admin/createquestion")
    public ModelAndView getCreateQuestionPage(Model model){
        QuestionResponseModel question = new QuestionResponseModel();
        List<Subject> subjects = subjectRepository.findAll();
        List<ChoiceResponseModel> choices = new ArrayList<>();
        for (int i=0;i<4;i++){
            choices.add(new ChoiceResponseModel());
        }
        question.setChoice(choices);
        model.addAttribute("question",question);
        model.addAttribute("subjects",subjects);
        return new ModelAndView("admin/createquestion");
    }

    @GetMapping("/admin/createexam")
    public ModelAndView getCreateExamPage(Model model){
        List<Subject> subjects = subjectRepository.findAll();
        model.addAttribute("subjects",subjects);
        return new ModelAndView("admin/createexam");
    }

    @GetMapping("/admin/createsubject")
    public ModelAndView getCreateSubjectPage(Model model){
        return new ModelAndView("admin/createsubject");
    }

    @GetMapping("/admin/question")
    public ModelAndView getQuestionPage(Model model){
        return new ModelAndView("admin/question");
    }



}
