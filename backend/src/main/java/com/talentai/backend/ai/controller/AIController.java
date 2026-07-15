package com.talentai.backend.ai.controller;

import com.talentai.backend.ai.dto.ResumeAnalysisResponse;
import com.talentai.backend.ai.entity.ResumeAnalysis;
import com.talentai.backend.ai.service.AIService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    private final AIService aiService;

    public AIController(AIService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/analyze")
    public ResumeAnalysisResponse analyzeResume(@RequestBody String resumeText) {

        return aiService.analyzeResume(resumeText);

    }

    @GetMapping("/history")
    public ResponseEntity<List<ResumeAnalysis>> getHistory() {

        return ResponseEntity.ok(
                aiService.getAllResumeAnalysis());

    }
}