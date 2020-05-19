package pl.two.jaquiz.repository;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.two.jaquiz.model.Question;

import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class QuestionRepositoryTest {
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    QuestionRepository questionRepository;

    @Test
    public void testSaveQuestion() {
        Question question = new Question();
        question.setContent("Test content");
        Question savedEntity = entityManager.persist(question);
        System.out.println(savedEntity.getId());

        Optional<Question> byId = questionRepository.findById(savedEntity.getId());
        Assertions.assertThat(byId.get()).isEqualTo(savedEntity);
    }

}
