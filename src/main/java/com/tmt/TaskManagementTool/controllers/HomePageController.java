package com.tmt.TaskManagementTool.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomePageController {
    
    @GetMapping
    public String home(){
        return "homepage";
    }
    
}
