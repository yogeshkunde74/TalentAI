package com.talentai.backend.controller;

import com.talentai.backend.dto.ResumeAnalysisResponse;
import com.talentai.backend.parser.ResumeParser;
import com.talentai.backend.service.analysis.ResumeAnalysisService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/analysis")
public class ResumeAnalysisController {

    private final ResumeParser resumeParser;
    private final ResumeAnalysisService analysisService;

    public ResumeAnalysisController(
            ResumeParser resumeParser,
            ResumeAnalysisService analysisService) {

        this.resumeParser = resumeParser;
        this.analysisService = analysisService;
    }

    @PostMapping("/resume")
    public ResumeAnalysisResponse analyzeResume(
            @RequestParam("file") MultipartFile file) {

        String text = resumeParser.extractText(file);

        return analysisService.analyze(text);
    }
}