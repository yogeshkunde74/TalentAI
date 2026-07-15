package com.talentai.backend.job.controller;

import com.talentai.backend.job.dto.JobMatchRequest;
import com.talentai.backend.job.dto.JobMatchResponse;
import com.talentai.backend.job.service.JobMatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/job")
public class JobMatchController {

    private final JobMatchService jobMatchService;

    public JobMatchController(JobMatchService jobMatchService) {
        this.jobMatchService = jobMatchService;
    }

    @PostMapping("/match")
    public ResponseEntity<JobMatchResponse> matchResume(
            @RequestBody JobMatchRequest request) {

        JobMatchResponse response = jobMatchService.matchResume(
                request.getResumeId(),
                request.getJobDescription());

        return ResponseEntity.ok(response);
    }
}