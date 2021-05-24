package pl.wenusix.familiada.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import pl.wenusix.familiada.model.AnswerModel;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameSnapshot {

    private final String id;
    private final String login;
    private final int level;
    private final int points;
    private final String question;
    private final List<AnswerModel> answers;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long rankId;

    public GameSnapshot(Game game) {
        this.id = game.getId();
        this.login = game.getLogin();
        this.level = game.getLevel();
        this.points = game.getPoints();
        this.question = game.getActualQuestion().getQuestion();
        this.answers = game.getActualQuestion()
                .getAnswersToGuess()
                .stream()
                .map(AnswerModel::new)
                .collect(Collectors.toList());


    }

    public void setRankId(Long rankId) {
        this.rankId = rankId;
    }

    public String getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public int getLevel() {
        return level;
    }

    public int getPoints() {
        return points;
    }

    public String getQuestion() {
        return question;
    }

    public List<AnswerModel> getAnswers() {
        return answers;
    }

    public Long getRankId() {
        return rankId;
    }
}
