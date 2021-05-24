package pl.wenusix.familiada.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;
import pl.wenusix.familiada.domain.*;
import pl.wenusix.familiada.repository.QuestionRepository;
import pl.wenusix.familiada.repository.RankRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class GameService {

    private final List<Game> games = new ArrayList<>();
    private final QuestionRepository questionRepository;
    private final RankRepository rankRepository;

    @Autowired
    public GameService(QuestionRepository questionRepository, RankRepository rankRepository) {
        this.questionRepository = questionRepository;
        this.rankRepository = rankRepository;
    }

    public String generateNewGame(String login) {
        Game game= new Game(login, questionRepository);
        games.add(game);
        return game.getId();
    }

    @Scheduled(fixedRate = 60_000)
    public void autoRemove() {
        LocalDateTime now = LocalDateTime.now();
        List<Game> actual = this.games.stream().filter(v -> now.plusHours(2).isAfter(v.getRecentlyActivity())).collect(Collectors.toList());
        this.games.clear();
        this.games.addAll(actual);
    }

    public GameSnapshot getGameDetails(String gameId){
        return games.stream()
                .filter(v -> v.getId().equals(gameId))
                .map(Game::getSnapshot)
                .findAny()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Attempt tryGuess(String gameId, String enteredWord){
        Game game = games.stream()
                .filter(v -> v.getId().equals(gameId)).
                        findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Attempt attempt = game.getActualQuestion().tryGuess(enteredWord);
        if(attempt.getResult() != Result.FAILURE) {
            game.addPoints(attempt.getAnswer().getPoints());
        }
        if(attempt.getResult() == Result.WIN){
            if(game.getLevel() >= 7) {
                Rank rank = rankRepository.save(new Rank(game));
                attempt.finish(rank);
                this.games.remove(game);
                return attempt;
            }

            game.levelUp();
        }
        return attempt;
    }

    public GameSnapshot hintLetter(String gameId, int answerNumber){
        Long rankId = null;
        Game game = games.stream()
                .filter(v -> v.getId().equals(gameId)).
                        findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        int num = 1;
        for(Answer answer : game.getActualQuestion().getAnswersToGuess()){
            if(num == answerNumber){
                if(answer.isGuessed()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
                answer.hintLetter();
                if(answer.isGuessed()) game.addPoints(answer.getPoints());
                if(game.getActualQuestion().getAnswersToGuess().stream().allMatch(Answer::isGuessed)){
                    if(game.getLevel() >= 7) {
                        Rank rank = rankRepository.save(new Rank(game));
                        rankId = rank.getId();
                        this.games.remove(game);
                        break;
                    }

                    game.levelUp();
                }
                break;
            }
            num++;
        }
        GameSnapshot snapshot = game.getSnapshot();
        snapshot.setRankId(rankId);
        return snapshot;
    }

}
