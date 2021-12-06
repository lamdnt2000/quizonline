package com.quizonline.group8.controller;

import com.quizonline.group8.dto.*;
import com.quizonline.group8.mapper.dto.ResponeChoiceDTO;
import com.quizonline.group8.mapper.dto.ResponseQuestionDTO;
import com.quizonline.group8.mapper.impl.QuestionResponseModelMapper;
import com.quizonline.group8.mapper.impl.ResponseChoiceDTOMapper;
import com.quizonline.group8.mapper.impl.ResponseQuestionDTOMapper;
import com.quizonline.group8.model.Question;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller()
public class BaseController {
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private QuestionResponseModelMapper questionResponseModelMapper;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private ResponseQuestionDTOMapper responseQuestionDTOMapper;

    @Autowired
    private ResponseChoiceDTOMapper responseChoiceDTOMapper;


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
        ResponseQuestionDTO question = new ResponseQuestionDTO();
        List<Subject> subjects = subjectRepository.findAll();
        List<ResponeChoiceDTO> choices = new ArrayList<>();
        for (int i=0;i<4;i++){
            choices.add(new ResponeChoiceDTO());
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

    @GetMapping("/admin/")
    public ModelAndView loadQuestionPageFirst(Model model){
        return getQuestionPage(model);
    }

    @GetMapping("/admin/question")
    public ModelAndView getQuestionPage(Model model){
        QuestionQuerySearchDTO questionQuerySearchDTO = new QuestionQuerySearchDTO();
        List<Subject> subjects = subjectRepository.findAll();
        model.addAttribute("subjects",subjects);
        model.addAttribute("search",questionQuerySearchDTO);
        return new ModelAndView("admin/question");
    }

    @GetMapping("/admin/questiondetail")
    public ModelAndView getUpdateQuestionPage(Model model,@RequestParam(name="id") Optional<Long> questId){
        Question question = questionService.findQuestionById(questId.get());
        ResponseQuestionDTO dto = this.responseQuestionDTOMapper.toDTO(question);
        dto.setIsDelete(dto.getStatus()==1?true:false);
        List<Subject> subjects = subjectRepository.findAll();
        model.addAttribute("question",dto);
        model.addAttribute("subjects",subjects);
        model.addAttribute("lastselected",dto.getSubject().getSubject_Id());
        return new ModelAndView("admin/questiondetail");
    }



}
