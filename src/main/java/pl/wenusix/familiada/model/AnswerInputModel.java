package pl.wenusix.familiada.model;

import java.util.List;

public class AnswerInputModel {
    private String text;
    private int points;
    private List<String> synonyms;

    public String getText() {
        return text;
    }

    public int getPoints() {
        return points;
    }

    public List<String> getSynonyms() {
        return synonyms;
    }
}
