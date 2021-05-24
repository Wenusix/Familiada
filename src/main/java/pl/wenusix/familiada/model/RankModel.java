package pl.wenusix.familiada.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pl.wenusix.familiada.domain.Cup;
import pl.wenusix.familiada.domain.Rank;

public class RankModel {

    @JsonIgnore
    private final long rankId;
    private final String login;
    private final int points;
    private final Cup cup;
    private final int place;

    public RankModel(Rank rank, int place){
        this.rankId = rank.getId();
        this.login = rank.getLogin();
        this.points = rank.getPoints();
        this.cup = rank.getCup();
        this.place = place;
    }

    public String getLogin() {
        return login;
    }

    public int getPoints() {
        return points;
    }

    public Cup getCup() {
        return cup;
    }

    public int getPlace() {
        return place;
    }

    public long getRankId() {
        return rankId;
    }
}
