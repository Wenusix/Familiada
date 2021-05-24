package pl.wenusix.familiada.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.wenusix.familiada.domain.Attempt;
import pl.wenusix.familiada.domain.GameSnapshot;
import pl.wenusix.familiada.service.GameService;

import java.util.regex.Pattern;

@RestController
@RequestMapping("game")
@CrossOrigin
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public String createNewGame(String login){
        login = login.trim();
        Pattern pattern = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
        if(pattern.matcher(login).find() || login.length() <= 3 || login.length() > 22) throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        return gameService.generateNewGame(login);
    }


    @GetMapping
    public GameSnapshot getGameDetails(String gameId){
        return gameService.getGameDetails(gameId);
    }
    @PostMapping
    @RequestMapping("guess")
    public Attempt tryGuess(String gameId, String enteredWord){
        return gameService.tryGuess(gameId, enteredWord);
    }
    @PostMapping
    @RequestMapping("hint")
    public GameSnapshot hintLetter(String gameId, int answerNumber){
        return gameService.hintLetter(gameId, answerNumber);
    }


}
