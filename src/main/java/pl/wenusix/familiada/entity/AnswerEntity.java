package pl.wenusix.familiada.entity;
import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class AnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int points;
    private String mainWord;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    private List<SynonymEntity> synonyms;

    public AnswerEntity(int points, String mainWord, List<String> synonyms) {
        this.points = points;
        this.mainWord = mainWord;
        this.synonyms = synonyms.stream().map(SynonymEntity::new).collect(Collectors.toList());
    }

    public AnswerEntity() {
    }

    public Long getId() {
        return id;
    }

    public int getPoints() {
        return points;
    }

    public String getMainWord() {
        return mainWord;
    }

    public List<SynonymEntity> getSynonyms() {
        return synonyms;
    }
}
