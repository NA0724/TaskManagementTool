package com.tmt.TaskManagementTool.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tmt.TaskManagementTool.util.GeneratePdfReportUtil;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private  GeneratePdfReportUtil generatePdfReportUtil;
    
    @GetMapping
    public String getDashboardPage(){
        return "dashboard";
    }

    @GetMapping("/pdfreport")
    public void getPDFReport(){
        generatePdfReportUtil.generatePdfReport();
    }
    
}