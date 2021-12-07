package com.quizonline.group8.controller;

import com.quizonline.group8.dto.LoginDTO;
import com.quizonline.group8.dto.MultiQuerySearchDTO;
import com.quizonline.group8.dto.RegisterDTO;
import com.quizonline.group8.dto.QuerySearchDTO;
import com.quizonline.group8.mapper.dto.*;
import com.quizonline.group8.mapper.impl.*;
import com.quizonline.group8.model.Question;
import com.quizonline.group8.model.QuizCategory;
import com.quizonline.group8.model.Subject;
import com.quizonline.group8.service.QuestionService;
import com.quizonline.group8.service.QuizCategoryService;
import com.quizonline.group8.service.SubjectService;
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
import java.util.Objects;
import java.util.Optional;

@Controller()
public class BaseController {
    @Autowired
    private QuestionResponseModelMapper questionResponseModelMapper;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private ResponseQuestionDTOMapper responseQuestionDTOMapper;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private ResponseChoiceDTOMapper responseChoiceDTOMapper;
    @Autowired
    private SubjectDTOMapper subjectDTOMapper;
    @Autowired
    private QuizCategoryService quizCategoryService;
    @Autowired
    private QuizCategoryMapper quizCategoryMapper;
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
        List<Subject> subjects = subjectService.findAll();
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
        List<Subject> subjects = subjectService.findAll();
        List<ResponeChoiceDTO> choices = new ArrayList<>();
        for (int i=0;i<4;i++){
            choices.add(new ResponeChoiceDTO());
        }
        question.setChoice(choices);
        model.addAttribute("question",question);
        model.addAttribute("subjects",subjects);
        return new ModelAndView("admin/createquestion");
    }

    @GetMapping("/admin/createquiz")
    public ModelAndView getCreateQuizPage(Model model){
        QuizCategoryDTO quizDTO = new QuizCategoryDTO();
        List<SubjectDTO> subjects = subjectService.findAllSubject();
        model.addAttribute("subjects",subjects);
        model.addAttribute("quiz",quizDTO);
        return new ModelAndView("admin/createquiz");
    }

    @GetMapping("/admin/createsubject")
    public ModelAndView getCreateSubjectPage(Model model){
        SubjectDTO subject = new SubjectDTO();
        model.addAttribute("subject",subject);
        return new ModelAndView("admin/createsubject");
    }

    @GetMapping("/admin/subject")
    public ModelAndView getSubjectPage(Model model){
        QuerySearchDTO search = new QuerySearchDTO();
        model.addAttribute("search",search);
        return new ModelAndView("admin/subject");
    }

    @GetMapping("/admin/")
    public ModelAndView loadQuestionPageFirst(Model model){
        return getQuestionPage(model);
    }

    @GetMapping("/admin/question")
    public ModelAndView getQuestionPage(Model model){
        MultiQuerySearchDTO multiQuerySearchDTO = new MultiQuerySearchDTO();
        List<Subject> subjects = subjectService.findAll();
        model.addAttribute("subjects",subjects);
        model.addAttribute("search", multiQuerySearchDTO);
        return new ModelAndView("admin/question");
    }
    @GetMapping("/admin/quiz")
    public ModelAndView getQuizPage(Model model){
        MultiQuerySearchDTO multiQuerySearchDTO = new MultiQuerySearchDTO();
        List<Subject> subjects = subjectService.findAll();
        model.addAttribute("subjects",subjects);
        model.addAttribute("search", multiQuerySearchDTO);
        return new ModelAndView("admin/quiz");
    }

    @GetMapping("/admin/questiondetail")
    public ModelAndView getUpdateQuestionPage(Model model,@RequestParam(name="id") Optional<Long> questId){
        Question question = questionService.findQuestionById(questId.get());
        if (Objects.nonNull(question)) {
            ResponseQuestionDTO dto = this.responseQuestionDTOMapper.toDTO(question);
            dto.setIsDelete(dto.getStatus() == 1 ? true : false);
            List<Subject> subjects = subjectService.findAll();
            model.addAttribute("question", dto);
            model.addAttribute("subjects", subjects);
            model.addAttribute("lastselected", dto.getSubject().getSubject_Id());
            return new ModelAndView("admin/questiondetail");
        }
        else{
            return getQuestionPage(model);
        }
    }

    @GetMapping("/admin/subjectdetail")
    public ModelAndView getUpdateSubjectPage(Model model,@RequestParam(name="id") Optional<Long> questId){
        Subject subject = subjectService.getSubById(questId.get());
        if (Objects.nonNull(subject)) {
            SubjectDTO dto = this.subjectDTOMapper.toDTO(subject);
            model.addAttribute("subject",dto);
            return new ModelAndView("admin/subjectdetail");
        }
        else{
            return getSubjectPage(model);
        }
    }

    @GetMapping("/admin/quizdetail")
    public ModelAndView getQuizDetailsPage(Model model,@RequestParam(name="id") Optional<Long> quizId){
        QuizCategory quizCategory = quizCategoryService.findQuizById(quizId.get());
        QuizCategoryDTO quizCategoryDTO = quizCategoryMapper.toDTO(quizCategory);
        List<SubjectDTO> subjects = subjectService.findAllSubject();
        model.addAttribute("subjects",subjects);
        model.addAttribute("quiz",quizCategoryDTO);
        return new ModelAndView("admin/quizdetail");
    }



}
