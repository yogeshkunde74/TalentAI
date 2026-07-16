package com.talentai.backend.interview.controller;

import com.talentai.backend.interview.dto.InterviewPreparationResponse;
import com.talentai.backend.interview.service.InterviewService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/interview")
public class InterviewPreparationController {

    private final InterviewService interviewService;

    public InterviewPreparationController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    @PostMapping("/prepare")
    public InterviewPreparationResponse prepareInterview(
            @RequestParam Long resumeId) {

        return interviewService.prepareInterview(resumeId);
    }
}