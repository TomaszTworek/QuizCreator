package pl.two.jaquiz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.two.jaquiz.model.Question;
import pl.two.jaquiz.model.Quiz;
import pl.two.jaquiz.repository.QuizRepository;
import pl.two.jaquiz.service.QuizService;

import javax.validation.Valid;

@Controller
public class MainController {

    private QuizService quizService;
    private QuizRepository quizRepository;

    public MainController(QuizService quizService, QuizRepository quizRepository) {
        this.quizService = quizService;
        this.quizRepository = quizRepository;
    }

    @GetMapping("/")
    public String getMainPage(Model model) {
        model.addAttribute("allQuizzes", quizService.getAllQuizzes());
        return "index";
    }

    @GetMapping("quizzes/play/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid quiz Id:" + id));
        model.addAttribute("quiz", quiz);
        System.out.println(quiz.getId());
        return "play-quiz";
    }
 /*
    @PostMapping("quizzes/edit/{id}")
    public String updateStudent(@PathVariable("id") long id, @Valid Student student, BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            student.setId(id);
            return "update-student";
        }

        studentRepository.save(student);
        model.addAttribute("students", studentRepository.findAll());
        return "index";
    }*/

    @GetMapping("quizzes/delete/{id}")
    public String deleteStudent(@PathVariable("id") long id, Model model) {
        System.out.println("ID: " + id);
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid quiz Id:" + id));
        quizRepository.delete(quiz);
        model.addAttribute("allQuizzes", quizRepository.findAll());
        model.addAttribute("isDeleted", true);
        return "redirect:/";
    }
}
