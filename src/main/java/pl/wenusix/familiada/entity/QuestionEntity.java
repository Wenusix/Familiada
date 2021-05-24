package pl.wenusix.familiada.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;
    private int level;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    private List <AnswerEntity> answers;

    public QuestionEntity(String question, int level, List<AnswerEntity> answers) {
        this.question = question;
        this.level = level;
        this.answers = answers;
    }

    public QuestionEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public int getLevel() {
        return level;
    }

    public List<AnswerEntity> getAnswers() {
        return answers;
    }
}
