package pl.wenusix.familiada.model;

import pl.wenusix.familiada.domain.Answer;

public class AnswerModel {

    private final String text;
    private final int points;

    public AnswerModel(Answer answer) {
        this.text = answer.getText();
        this.points = answer.getPoints();
    }

    public String getText() {
        return text;
    }

    public int getPoints() {
        return points;
    }
}
