package pl.wenusix.familiada.domain;

import pl.wenusix.familiada.entity.QuestionEntity;
import pl.wenusix.familiada.repository.QuestionRepository;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Game {
    private final String id;
    private final String login;
    private int level = 1;
    private int points = 0;
    private Question actualQuestion;
    private final QuestionRepository questionRepository;
    private LocalDateTime recentlyActivity;


    public Game(String login, QuestionRepository questionRepository) {
        this.id= UUID.randomUUID().toString();
        this.login=login;
        this.questionRepository = questionRepository;
        generateNewQuestion();
        this.recentlyActivity = LocalDateTime.now();
    }

    public void generateNewQuestion(){
        List<QuestionEntity> questionEntities = questionRepository.findAll().stream().filter(v -> v.getLevel() == this.level).collect(Collectors.toList());
        if(questionEntities.size() == 0) throw new RuntimeException("Any questions for actual level");
        SecureRandom secureRandom = new SecureRandom();
        QuestionEntity entity = questionEntities.get(secureRandom.nextInt(questionEntities.size()));
        this.actualQuestion = new Question(entity);
        this.recentlyActivity = LocalDateTime.now();
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

    public Question getActualQuestion() {
        return actualQuestion;
    }

    public GameSnapshot getSnapshot(){
        return new GameSnapshot(this);
    }

    public void levelUp(){
        this.level++;
        generateNewQuestion();
    }

    public void addPoints(int points){
        this.points += points;
    }

    public LocalDateTime getRecentlyActivity() {
        return recentlyActivity;
    }
}
