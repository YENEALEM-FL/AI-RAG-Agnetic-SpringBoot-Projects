package com.lab.springaiworkflowcontrolapproach.controller;

import com.lab.springaiworkflowcontrolapproach.service.WorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AIController {

    @Autowired
    private WorkflowService workflowService;

    @PostMapping("/chat")
    public String chat(@RequestParam String input){

        return workflowService.chatServiceInWorkflow(input);
    }
}
