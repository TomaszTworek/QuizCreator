package pl.two.jaquiz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.two.jaquiz.model.Question;
import pl.two.jaquiz.service.QuestionService;

import java.time.LocalDate;

@Controller
public class QuestionController {

    private QuestionService questionService;


    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/questions")
    public String getAllQuestions(Model model) {
        model.addAttribute("questions", questionService.getAllQuestions());
        model.addAttribute("newQuestion", new Question());
        return "questions";
    }


    @PostMapping("/addQuestion")
    public String addQuestionWhenQuizIsCreated(@ModelAttribute(value = "question") Question question) {
        Question newQuestion = new Question();
        newQuestion.setContent(question.getContent());
        newQuestion.setCreatedAt(LocalDate.now());

        questionService.addNewQuestion(newQuestion);

        return "redirect:/questions";
    }

    @RequestMapping(method = RequestMethod.POST ,value = "/modifyQuestion",params = "action=edit")
    public String editQuestion(@ModelAttribute Question question){
        System.out.println("EDIT CONTROLLER");
        return "redirect:/new-quiz";
    }

    @RequestMapping(method = RequestMethod.POST ,value = "/modifyQuestion",params = "action=delete")
    public String deleteQuestion(@ModelAttribute Question question){
        System.out.println("DELETE  CONTROLLER");

        return "redirect:/new-quiz";
    }

}
