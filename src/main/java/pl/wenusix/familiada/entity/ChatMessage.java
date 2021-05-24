package pl.wenusix.familiada.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String message;

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getMessage() {
        return message;
    }
}
