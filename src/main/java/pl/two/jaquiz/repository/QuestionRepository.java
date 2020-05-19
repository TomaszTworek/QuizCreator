package pl.two.jaquiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.two.jaquiz.model.Answer;
import pl.two.jaquiz.model.Question;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {


/*    @Query("select ans from Answer ans join ans.question q where q.id= ( :id)")
    List<Answer> findCorrectAnswersForCurrentQuestion(@Param("id") Long questionId);*/
}
