//package pl.wenusix.familiada;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import pl.wenusix.familiada.entity.AnswerEntity;
//import pl.wenusix.familiada.entity.QuestionEntity;
//import pl.wenusix.familiada.repository.QuestionRepository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//public class DatabaseInputing {
//
//    private final QuestionRepository questionRepository;
//
//    @Autowired
//    public DatabaseInputing(QuestionRepository questionRepository) {
//        this.questionRepository = questionRepository;
//
//        List<AnswerEntity> answers = List.of(
//                new AnswerEntity(30, "Pies", List.of("piesek", "kundel", "piesio")),
//                new AnswerEntity(20, "Kot", List.of("kotek", "kociak", "martyna")),
//                new AnswerEntity(1, "Chomik", List.of("chomiczek"))
//
//        );
//        QuestionEntity questionEntity = new QuestionEntity("Zwierzęta domowe", 1, answers);
//
//
//        questionRepository.save(questionEntity);
//
//        answers = List.of(
//                new AnswerEntity(30, "Swinia", List.of()),
//                new AnswerEntity(20, "Zebra", List.of()),
//                new AnswerEntity(2, "Zyrafa", List.of())
//
//        );
//        questionEntity = new QuestionEntity("Zwierzęta hodowlane", 2, answers);
//
//
//        questionRepository.save(questionEntity);
//
//        answers = List.of(
//                new AnswerEntity(30, "xdd", List.of()),
//                new AnswerEntity(20, "ergregerg", List.of()),
//                new AnswerEntity(3, "Z43t43354a", List.of())
//
//        );
//        questionEntity = new QuestionEntity("Zwierzęta ciekawe", 2, answers);
//        questionRepository.save(questionEntity);
//
//        answers = List.of(
//                new AnswerEntity(30, "Marsonix", List.of()),
//                new AnswerEntity(20, "Dupa", List.of()),
//                new AnswerEntity(15, "214f4gerg", List.of())
//
//        );
//        questionEntity = new QuestionEntity("Raperzy", 3, answers);
//        questionRepository.save(questionEntity);
//
//        for(int i = 0 ; i < 100 ; i++){
//
//            answers = List.of(
//                    new AnswerEntity(30, "Marsonix", List.of()),
//                    new AnswerEntity(20, "Dupa", List.of()),
//                    new AnswerEntity(15, "214f4gerg", List.of())
//
//            );
//            questionEntity = new QuestionEntity("Raperzy", 3+i, answers);
//            questionRepository.save(questionEntity);
//
//        }
//
//        System.out.println();
//    }
//
//
//}
