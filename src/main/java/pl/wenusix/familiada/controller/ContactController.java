package pl.wenusix.familiada.controller;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import pl.wenusix.familiada.model.MailModel;

@RestController
@CrossOrigin
@RequestMapping("contact")
public class ContactController {

    private final JavaMailSender javaMailSender;

    public ContactController(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @PostMapping
    public String sendMail(@RequestBody MailModel model){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject(model.getTitle());
        simpleMailMessage.setText(model.getDescription()+"\n\nWiadomość od "+model.getEmail());
        simpleMailMessage.setTo("martynabakula09@gmail.com");
        javaMailSender.send(simpleMailMessage);
        return "success";
    }

}
