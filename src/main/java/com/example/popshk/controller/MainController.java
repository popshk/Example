package com.example.popshk.controller;

import com.example.popshk.domain.Message;
import com.example.popshk.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
        private MessageRepository messageRepository;

    @GetMapping("/")
    public String greeting (Map<String,Object> model){
            return "greeting";
    }

    @GetMapping("/main")
        public String main(Map<String,Object> model){
        Iterable<Message> messages = messageRepository.findAll();

        model.put("messages",messages);
            return "main";
    }

    @PostMapping("/main")
        public String add(@RequestParam String text, @RequestParam String tag, Map<String,Object> model){
        Message message = new Message(text, tag);

        messageRepository.save(message);

        Iterable<Message> messages = messageRepository.findAll();

        model.put("messages",messages);

        return "main";
    }

    @PostMapping("filter")
        public String filter(@RequestParam String filter,Map<String,Object> model){
            Iterable<Message> messages;

            if (!filter.isEmpty() && filter != null) {
                messages = messageRepository.findByTag(filter);

            }else {
                //messages = messageRepository.findAll();
                    return "redirect:/main";
            }

        model.put("messages",messages);
        return "main";
    }
}
