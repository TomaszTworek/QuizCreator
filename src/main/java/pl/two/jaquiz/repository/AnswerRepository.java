package pl.two.jaquiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.two.jaquiz.model.Answer;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

}
