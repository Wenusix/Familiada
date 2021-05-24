package pl.wenusix.familiada.domain;

import javax.persistence.*;

@Entity
public class Rank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private int points;
    @Enumerated(EnumType.STRING)
    private Cup cup;

    public Rank() {
    }

    public Rank(Game game) {
        this.login = game.getLogin();
        this.points = game.getPoints();
        if(game.getPoints() < 560)
            this.cup = Cup.SILVER;
        else if(game.getPoints() < 640)
            this.cup = Cup.GOLD;
        else
            this.cup = Cup.DIAMOND;

    }

    public Long getId() {
        return id;
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
}
