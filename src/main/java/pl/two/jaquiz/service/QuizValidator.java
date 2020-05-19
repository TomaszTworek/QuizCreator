package pl.two.jaquiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.two.jaquiz.model.Question;
import pl.two.jaquiz.repository.QuestionRepository;
import pl.two.jaquiz.service.AnswerService;
import pl.two.jaquiz.service.QuestionService;
import pl.two.jaquiz.service.QuizService;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizValidator {


    private List<Long> userAnswers = new ArrayList<>();


    public List<Long> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(List<Long> userAnswers) {
        this.userAnswers = userAnswers;
    }

}
