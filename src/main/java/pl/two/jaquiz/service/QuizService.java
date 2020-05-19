package pl.two.jaquiz.service;

import org.springframework.stereotype.Service;
import pl.two.jaquiz.model.Question;
import pl.two.jaquiz.model.Quiz;
import pl.two.jaquiz.repository.QuizRepository;

import java.util.*;

@Service
public class QuizService {

    private QuizRepository quizRepository;

    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public Quiz addNewQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    private List<Question> randomQuestions = new ArrayList<>();

    public List<Question> getRandomQuestions() {
        return randomQuestions;
    }

    public void setRandomQuestions(List<Question> randomQuestions) {
        this.randomQuestions = randomQuestions;
    }

    public void deleteQuiz(Quiz quiz) {
        Optional<Quiz> byId = quizRepository.findById(quiz.getId());
        quizRepository.delete(byId.get());
    }

    public void generateRandomQuestionsForCurrentQuiz(Quiz quiz, String numberOfQuestions) {

        List<Question> randomList = new ArrayList<>();

        int numberOfQuestionsInDB = getNumberOfQuestionsForCurrentQuiz(quiz);


        List<Question> questionsFromDB = quizRepository.findById(quiz.getId()).map(Quiz::getQuestionList).get();
        Collections.shuffle(questionsFromDB);
        if (numberOfQuestionsInDB < Integer.parseInt(numberOfQuestions)) {
            for (int i = 0; i < numberOfQuestionsInDB; i++) {
                randomList.add(questionsFromDB.get(i));
            }
        } else {
            for (int i = 0; i < Integer.parseInt(numberOfQuestions); i++) {
                randomList.add(questionsFromDB.get(i));
            }
        }
        this.randomQuestions = randomList;
    }

    public int getNumberOfQuestionsForCurrentQuiz(Quiz quiz) {
        return quizRepository.findById(quiz.getId())
                .map(Quiz::getQuestionList)
                .orElseThrow(() -> new IllegalArgumentException("Invalid quiz Id:" + quiz.getId()))
                .size();
    }




}
