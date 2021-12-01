package com.quizonline.group8.controller;

import com.quizonline.group8.dto.LoginDTO;
import com.quizonline.group8.dto.RegisterDTO;
import com.quizonline.group8.mapper.impl.QuestionResponseModelMapper;
import com.quizonline.group8.mapper.resposemodel.ChoiceResponseModel;
import com.quizonline.group8.mapper.resposemodel.QuestionResponseModel;
import com.quizonline.group8.model.Subject;
import com.quizonline.group8.repository.SubjectRepository;
import com.quizonline.group8.service.QuestionService;
import com.quizonline.group8.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    public String index(Authentication authentication){

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

    @GetMapping("/signin")
    public ModelAndView getSignin(){
        LoginDTO loginDTO = new LoginDTO();
        return new ModelAndView("signin", "login",loginDTO);
    }

    @GetMapping("/signup")
    public ModelAndView getSignup(){
        RegisterDTO registerDTO = new RegisterDTO();
        return new ModelAndView("signup", "member",registerDTO);
    }


    @GetMapping("/signout")
    public ModelAndView getSignout(HttpServletResponse response, HttpSession session){
        session.invalidate();
        CookieUtil.clear(response,"token");
        LoginDTO loginDTO = new LoginDTO();
        return new ModelAndView("redirect:/signin","login",loginDTO);
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
