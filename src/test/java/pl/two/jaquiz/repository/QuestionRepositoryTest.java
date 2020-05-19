package pl.two.jaquiz.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import pl.two.jaquiz.model.Answer;
import pl.two.jaquiz.model.Question;
import pl.two.jaquiz.model.QuestionType;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class QuestionRepositoryTest {

    @Autowired
    private QuestionRepository questionRepository;


    private Question question;

    private List<Answer> answers;

    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(questionRepository).isNotNull();
    }

    @Before
    public void setUp() {
        Answer answer = new Answer("Answ1", true);
        Answer answer2 = new Answer("Answ2", false);
        Answer answer3 = new Answer("Answ3", false);
        Answer answer4 = new Answer("Answ4", true);
        answers = Arrays.asList(answer, answer2, answer3, answer4);

        question = new Question(
                "Question_content_1",
                QuestionType.JAVA_CORE,
                LocalDate.now(),
                answers);
    }

    @Test
    public void shouldSaveQuestionAndThenFindByContent() {
        //when
        questionRepository.save(question);
        Optional<Question> foundQuestion = questionRepository.findById(question.getId());

        //then
        assertThat(foundQuestion).isNotNull();
        assertThat(foundQuestion.get().getContent()).isEqualTo("Question_content_1");
    }

    @Test
    public void shouldRemoveQuestionFromDatabase() {
        //when
        questionRepository.save(question);
        questionRepository.delete(question);

        //then
        assertThat(questionRepository.findById(question.getId())).isEmpty();
    }

    @Test
    public void shouldUpdateHaveFourAnswers() {
        questionRepository.save(question);

        Optional<Question> foundQuestion = questionRepository.findById(question.getId());


        assertThat(foundQuestion.get().getAnswers().size()).isEqualTo(4);
        assertThat(foundQuestion.get().getAnswers().get(0).getContent()).isEqualTo("Answ1");
    }
}
