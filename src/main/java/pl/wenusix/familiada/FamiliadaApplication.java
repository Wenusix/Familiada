package pl.wenusix.familiada;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.UUID;

@SpringBootApplication
@EnableScheduling
public class FamiliadaApplication {

    public static void main(String[] args) {
        SpringApplication.run(FamiliadaApplication.class, args);
    }

}
