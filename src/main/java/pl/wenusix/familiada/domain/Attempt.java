package pl.wenusix.familiada.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Attempt {

    private Result result;
    private final String enteredWord;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Answer answer;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long rankId;

    private Attempt(Result result, String enteredWord, Answer answer){
        this.enteredWord = enteredWord;
        this.result = result;
        this.answer = answer;
    }

    public static Attempt of(Result result, String enteredWord, Answer answer) {
        return new Attempt(result, enteredWord, answer);
    }

    public Result getResult() {
        return result;
    }

    public String getEnteredWord() {
        return enteredWord;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void finish(Rank rank){
        this.result = Result.END;
        this.rankId = rank.getId();
    }

    public Long getRankId() {
        return rankId;
    }
}

