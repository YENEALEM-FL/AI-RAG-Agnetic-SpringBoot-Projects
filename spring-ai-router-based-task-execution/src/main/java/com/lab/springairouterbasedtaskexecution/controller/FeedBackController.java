package com.lab.springairouterbasedtaskexecution.controller;

import com.lab.springairouterbasedtaskexecution.services.SentimentBasedRouterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedBackController {

    @Autowired
    private SentimentBasedRouterService sentimentBasedRouterService;

    @PostMapping("/feedback")
    public ResponseEntity<String> submitFeedback(@RequestParam String feedBack) {

        System.out.println("Received feedback: " + feedBack);
        sentimentBasedRouterService.routeFeedBack(feedBack);
        return ResponseEntity.ok("feedback received and is being processed");
    }

}
