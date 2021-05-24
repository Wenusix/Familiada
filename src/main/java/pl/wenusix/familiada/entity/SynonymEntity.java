package pl.wenusix.familiada.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SynonymEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String value;

    public Long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public SynonymEntity(String value) {
        this.value = value;
    }

    public SynonymEntity() {
    }
}
