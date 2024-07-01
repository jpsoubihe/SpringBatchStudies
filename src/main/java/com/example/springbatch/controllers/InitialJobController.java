package com.example.springbatch.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/initial")
public class InitialJobController {

    public static final Logger log = LoggerFactory.getLogger(InitialJobController.class);

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job myJob;

    @GetMapping("/hello")
    public ResponseEntity helloWorld() {
        try {
            jobLauncher.run(myJob, new JobParameters());
        } catch (JobExecutionException e) {
            log.error("Failed to execute job", e);
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }
}
