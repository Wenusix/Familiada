package pl.wenusix.familiada.controller;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import pl.wenusix.familiada.entity.ChatMessage;
import pl.wenusix.familiada.repository.ChatMessageRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("chat")
public class ChatController {

    private final ChatMessageRepository chatMessageRepository;

    public ChatController(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    @GetMapping
    public List<ChatMessage>getAllMessages(){
        return chatMessageRepository.findAll(Sort.by("id"));
    }

    @GetMapping("skip")
    public List<ChatMessage>getAllMessagesAndSkip(@RequestParam long lastId){
        return chatMessageRepository.findAll(Sort.by("id")).stream().skip(lastId).collect(Collectors.toList());
    }

    @PostMapping
    public long saveMessage(@RequestBody ChatMessage message){
        return chatMessageRepository.save(message).getId();
    }


}
