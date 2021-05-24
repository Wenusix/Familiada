package pl.wenusix.familiada.domain;

import pl.wenusix.familiada.entity.AnswerEntity;
import pl.wenusix.familiada.entity.QuestionEntity;

import java.util.*;

public class Question {
    private final String question;
    private final Set<Answer> answersToGuess;


    public Question(QuestionEntity entity) {
        this.answersToGuess = new TreeSet<>(Comparator.comparingInt(Answer::getOriginalPoints).reversed());
        this.question= entity.getQuestion();
        for(AnswerEntity answer: entity.getAnswers()) {
            this.answersToGuess.add(new Answer(answer));
        }
    }

    public Attempt tryGuess(String enteredWord){
        Answer answer = answersToGuess.stream()
                .map(v -> v.tryGuess(enteredWord))
                .filter(Objects::nonNull)
                .findAny()
                .orElse(null);

        Attempt attempt;
        if(answer!= null) {
            if(answersToGuess.stream().allMatch(Answer::isGuessed)) attempt = Attempt.of(Result.WIN, enteredWord, answer);
            else attempt = Attempt.of(Result.SUCCESS, enteredWord, answer);
        }
        else attempt = Attempt.of(Result.FAILURE, enteredWord, null);

        return attempt;
    }

    public String getQuestion() {
        return question;
    }

    public Set<Answer> getAnswersToGuess() {
        return answersToGuess;
    }

}
