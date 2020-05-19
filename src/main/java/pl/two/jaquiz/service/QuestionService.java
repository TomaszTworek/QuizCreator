package pl.two.jaquiz.service;

import org.springframework.stereotype.Service;
import pl.two.jaquiz.model.Answer;
import pl.two.jaquiz.model.Question;
import pl.two.jaquiz.repository.AnswerRepository;
import pl.two.jaquiz.repository.QuestionRepository;

import java.util.*;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private QuizValidator quizValidator = new QuizValidator();


    public QuestionService(QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question addNewQuestion(Question newQuestion) {
        return questionRepository.save(newQuestion);
    }


    private List<Question> temporaryQuestionHolder = new ArrayList<>();


    public void addNewQuestionToCurrentQuiz(Question newQuestion) {
        temporaryQuestionHolder.add(newQuestion);
    }

    public List<Question> getAllQuestionsForCurrentQuiz() {
        return temporaryQuestionHolder;
    }

    public void clearTemporaryQuestionDB() {
        temporaryQuestionHolder.clear();
    }

    public boolean checkCorrectAnswers(Long questionId, List<Long> userAnswers) {
        Question checkingQuestion = questionRepository.findById(questionId).get();
        System.out.println(checkingQuestion);
        List<Long> correctAnswers = new ArrayList<>();
        for (Answer answer : checkingQuestion.getAnswers()) {
            if (answer.isCorrect()) {
                correctAnswers.add(answer.getId());
            }
        }
        System.out.println("Correct answers before sort: " + correctAnswers);
        System.out.println("User answers before sort: " + userAnswers);
        Collections.sort(correctAnswers);
        System.out.println("Correct answers after sort: " + correctAnswers);
        Collections.sort(userAnswers);
        System.out.println("user answers after sort: " + userAnswers);

        return correctAnswers.equals(userAnswers);

    }

    public int getNumberOfPointsGettingByUser(List<Long> allQuestionsForCurrentQuiz, List<Long> userAnswers) {
        int numberOfPoints = 0;
        System.out.println("Questions for current quiz: " + allQuestionsForCurrentQuiz);
        for (int i = 0; i < allQuestionsForCurrentQuiz.size(); i++) {
            List<Long> userAnswersForQuestion = getAnswersForQuestion(allQuestionsForCurrentQuiz.get(i), userAnswers);
            if (checkIfQuestionsIsCorrect(allQuestionsForCurrentQuiz.get(i), userAnswersForQuestion)) {
                numberOfPoints++;
            }
        }
        return numberOfPoints;
    }

    private List<Long> getAnswersForQuestion(Long questionId, List<Long> userAnswers) {
        Question question = questionRepository.findById(questionId).get();

        List<Long> answersForQuestion = new ArrayList<>();

        for (Answer answer : question.getAnswers()) {
            for (Long id : userAnswers) {
                if (answer.getId().equals(id)) {
                    answersForQuestion.add(answer.getId());
                }
            }
        }
        return answersForQuestion;
    }

    private boolean checkIfQuestionsIsCorrect(Long questionId, List<Long> userAnswers) {
        return checkCorrectAnswers(questionId, userAnswers);
    }
}
