package pl.wenusix.familiada.controller;

import org.springframework.web.bind.annotation.*;
import pl.wenusix.familiada.entity.AnswerEntity;
import pl.wenusix.familiada.entity.QuestionEntity;
import pl.wenusix.familiada.model.QuestionModel;
import pl.wenusix.familiada.repository.QuestionRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("database")


public class DatabaseController {

    private final QuestionRepository questionRepository;

    public DatabaseController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }


    @PostMapping
    public void fillDatabaseQuestions(@RequestBody Questions questions, @RequestParam String password){
        if(!password.equals("fghmktrklrnhiteng5io5en5uiogr"))return;
        questions.getQuestions().forEach(model ->{
            List<AnswerEntity> list = model.getAnswers().stream().map(v -> new AnswerEntity(v.getPoints(), v.getText(), v.getSynonyms())).collect(Collectors.toList());
            QuestionEntity questionEntity = new QuestionEntity(model.getQuestion(), model.getLevel(), list);
            questionRepository.save(questionEntity);
        });
    }

}

class Questions{
    private List<QuestionModel> questions;

    public List<QuestionModel> getQuestions() {
        return questions;
    }
}


