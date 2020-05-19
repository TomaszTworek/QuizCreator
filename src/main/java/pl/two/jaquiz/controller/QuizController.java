package pl.two.jaquiz.controller;

import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.two.jaquiz.model.Question;
import pl.two.jaquiz.model.Quiz;
import pl.two.jaquiz.repository.QuizRepository;
import pl.two.jaquiz.service.QuizValidator;
import pl.two.jaquiz.service.QuestionService;
import pl.two.jaquiz.service.QuizService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class QuizController {

    private QuestionService questionService;
    private QuizService quizService;
    private QuizRepository quizRepository;
    private List<Question> currentQuestions = new ArrayList<>();

    public QuizController(QuestionService questionService, QuizService quizService, QuizRepository quizRepository) {
        this.questionService = questionService;
        this.quizService = quizService;
        this.quizRepository = quizRepository;
    }

    @GetMapping("/new-quiz")
    public String getQuizCreator(Model model) {
        model.addAttribute("currentQuizQuestions", questionService.getAllQuestionsForCurrentQuiz());
        Question question = new Question();
        model.addAttribute("newQuestionForCurrentQuiz", question);
        model.addAttribute("newQuiz", new Quiz());

        return "new-quiz";
    }

    @PostMapping("/addQuiz")
    public String createQuiz(@ModelAttribute(value = "quiz") Quiz quiz) {
        Quiz newQuiz = new Quiz();
        newQuiz.setName(quiz.getName());

        List<Question> questionList = questionService.getAllQuestionsForCurrentQuiz();

        for (Question q : questionList) {
            questionService.addNewQuestion(q);
        }
        newQuiz.setQuestionList(questionList);
        System.out.println(newQuiz);
        quizService.addNewQuiz(newQuiz);

        questionService.clearTemporaryQuestionDB();
        newQuiz.setQuestionList(new ArrayList<>());
        return "redirect:/new-quiz";
    }


    @PostMapping("/addQuestionViaQuiz")
    public String addQuestionWhenQuizIsCreating(@ModelAttribute(value = "question") Question question) {
        Question newQuestion = new Question();
        newQuestion.setContent(question.getContent());
        newQuestion.setCreatedAt(LocalDate.now());
        newQuestion.setAnswers(question.getAnswers());

        questionService.addNewQuestionToCurrentQuiz(newQuestion);
        System.out.println(questionService.getAllQuestionsForCurrentQuiz());
        return "redirect:/new-quiz";
    }


    @PostMapping("/playQuiz/{id}")
    public String generateQuiz(@PathVariable("id") long id, @RequestParam(value = "numberOfQuestions") String numberOfQuestions) {
        System.out.println("generateQuiz: " + id);
        Quiz byId = quizRepository.findById(id).get();
        quizService.generateRandomQuestionsForCurrentQuiz(byId, numberOfQuestions);


        return "redirect:/play-questions/{id}";
    }


    @GetMapping("/play-questions/{id}")
    public String getPlayQuiz(Model model, @PathVariable("id") long id) {
        List<Question> randomQuestions = quizService.getRandomQuestions();
        model.addAttribute("questions", randomQuestions);

        model.addAttribute("userAnswers", new QuizValidator());
        return "play-questions";
    }

    @PostMapping("/submitQuiz")
    public String submitQuiz(Model model, @ModelAttribute QuizValidator quizValidator) {
        List<Long> userAnswers = quizValidator.getUserAnswers();
        List<Long> collect = quizService.getRandomQuestions().stream().map(Question::getId).collect(Collectors.toList());
        int numberOfPointsGettingByUser = questionService.getNumberOfPointsGettingByUser(collect, userAnswers);
        model.addAttribute("points", numberOfPointsGettingByUser);
        model.addAttribute("totalPoints", quizService.getRandomQuestions().size());
        System.out.println(numberOfPointsGettingByUser);
        return "summary-quiz";
    }

}
