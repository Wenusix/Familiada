package pl.wenusix.familiada.model;

import java.util.List;

public class QuestionModel {

    private String question;
    private int level;
    private List<AnswerInputModel> answers;

    public String getQuestion() {
        return question;
    }

    public int getLevel() {
        return level;
    }

    public List<AnswerInputModel> getAnswers() {
        return answers;
    }
}
