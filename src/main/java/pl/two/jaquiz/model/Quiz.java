package pl.two.jaquiz.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Question> questionList;

    public Quiz() {

    }

    public Quiz(Long id, String name, List<Question> questionList) {
        this.id = id;
        this.name = name;
        this.questionList = questionList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quiz quiz = (Quiz) o;
        return Objects.equals(id, quiz.id) &&
                Objects.equals(name, quiz.name) &&
                Objects.equals(questionList, quiz.questionList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, questionList);
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", questionList=" + questionList +
                '}';
    }
}
