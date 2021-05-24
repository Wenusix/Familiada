package pl.wenusix.familiada.domain;

import pl.wenusix.familiada.entity.AnswerEntity;
import pl.wenusix.familiada.entity.SynonymEntity;
import javax.persistence.OneToMany;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Answer {
    private static final SecureRandom random = new SecureRandom();

    private int points;
    private final int originalPoints;
    private final String mainWord;
    private final List <Integer> charIndexes;
    private final List <String> synonyms;
    private boolean guessed = false;

    public Answer (AnswerEntity entity){
        this.points= entity.getPoints();
        this.originalPoints = entity.getPoints();
        this.mainWord= entity.getMainWord();
        this.synonyms= new ArrayList<>(entity.getSynonyms().size());
        for (SynonymEntity synonym: entity.getSynonyms()) {
            synonyms.add(synonym.getValue());
        }
        this.charIndexes=new ArrayList<>(this.mainWord.length());

    }

    public String getText(){
        if(guessed) return mainWord;
        else if(charIndexes.size() == 0) return "................";
        StringBuilder builder = new StringBuilder(mainWord.length());
        for(int i = 0 ; i < mainWord.length() ; i++){
            if(charIndexes.contains(i)) builder.append(mainWord.charAt(i));
            else if(mainWord.charAt(i) == ' ') builder.append("   ");
            else builder.append("_ ");
        }
        return builder.toString();
    }

    public void hintLetter(){
        if(guessed) return;
        points -= 2;
        if(charIndexes.size() == 0){
            charIndexes.add(0);
            return;
        }
        int number;
        do {
            number = random.nextInt(mainWord.length());
        }while (charIndexes.contains(number) || mainWord.charAt(number)== ' ');
        charIndexes.add(number);
        if(charIndexes.size() >= mainWord.replaceAll("\\s","").length())
            guessed = true;

    }

    public Answer tryGuess(String word){
        if(guessed) return null;
        List<String> list = new ArrayList<>(this.synonyms);
        list.add(0, mainWord);
        if(list.stream().anyMatch(v -> tryGuess(word, v)) ){
            guessed = true;
            return this;
        }
        return null;
    }

    private boolean tryGuess(String enteredWord, String actualWord){
        enteredWord = transform(enteredWord);
        actualWord = transform(actualWord);

        return enteredWord.contains(actualWord);
    }

    public int getOriginalPoints() {
        return originalPoints;
    }

    private String transform(String oldString){
        return oldString
                .toLowerCase(Locale.ROOT)
                .replace('ź', 'z')
                .replace('ż', 'z')
                .replace('ó', 'u')
                .replace('ą', 'a')
                .replace('ę', 'e')
                .replace('ś', 's')
                .replace('ł', 'l')
                .replace('v', 'w')
                .replace('ś', 's')
                .replace('ć', 'c');
    }

    public int getPoints() {
        return points;
    }

    public boolean isGuessed() {
        return guessed;
    }
}
