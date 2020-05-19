package pl.two.jaquiz.service;

import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.two.jaquiz.model.Question;
import pl.two.jaquiz.repository.QuestionRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QuestionServiceTest {

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private QuestionService questionService;


    @Test
    void savedQuestionHasContent() {
        Question question = new Question();
        question.setContent("Test content in service");

        when(questionRepository.save(any(Question.class))).then(returnsFirstArg());
        Question savedQuestion = questionService.addNewQuestion(question);

        assertThat(savedQuestion.getContent()).isNotNull();
        assertThat(savedQuestion.getContent().equals("Test content in service"));
    }
}
