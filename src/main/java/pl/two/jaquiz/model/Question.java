package pl.two.jaquiz.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private QuestionType questionType;

    private String content;

    private LocalDate createdAt;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,orphanRemoval = true)
    private List<Answer> answers = new ArrayList<>(4);

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", questionType=" + questionType +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                ", answers=" + answers +
                '}';
    }

    public Question() {
        for (int i = 0; i < 4; i++) {
            answers.add(new Answer());
        }
    }


    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(id, question.id) &&
                questionType == question.questionType &&
                Objects.equals(content, question.content) &&
                Objects.equals(createdAt, question.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, questionType, content, createdAt);
    }


}
