package com.talentai.backend.resume.controller;

import com.talentai.backend.ai.dto.ResumeAnalysisResponse;
import com.talentai.backend.ai.service.AIService;
import com.talentai.backend.file.service.PdfService;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/resume")
public class ResumeController {

    private final PdfService pdfService;
    private final AIService aiService;

    public ResumeController(
            PdfService pdfService,
            AIService aiService) {

        this.pdfService = pdfService;
        this.aiService = aiService;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResumeAnalysisResponse> uploadResume(
            @RequestParam("file") MultipartFile file) {

        String resumeText = pdfService.extractText(file);

        ResumeAnalysisResponse response = aiService.analyzeResume(resumeText);

        return ResponseEntity.ok(response);
    }
}