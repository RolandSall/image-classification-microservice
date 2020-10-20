package com.rhr.imageclassificationbackend.controllers;
import com.rhr.imageclassificationbackend.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/home")
public class Home {

    private final RestTemplate restTemplate;

    @Autowired
    public Home(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public ResponseEntity<Message> test() {
        ResponseEntity<Message> message = restTemplate.getForEntity("http://d07e56dc65f9.ngrok.io/", Message.class);

        return ResponseEntity.status(HttpStatus.OK).body(message.getBody());
    }


}
