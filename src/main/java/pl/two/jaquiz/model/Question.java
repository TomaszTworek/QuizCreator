package pl.two.jaquiz.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
public class Question implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private QuestionType questionType;

    private String content;

    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "question")
    private List<Answer> answers = new ArrayList<>(4);

    public Question(String content, QuestionType questionType, LocalDate createdAt, List<Answer> answerList) {
        this.content = content;
        this.questionType = questionType;
        this.createdAt = createdAt;
        this.answers = answerList;
    }
}
