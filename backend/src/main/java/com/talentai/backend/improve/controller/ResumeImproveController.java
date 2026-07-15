package com.talentai.backend.improve.controller;

import com.talentai.backend.improve.dto.ResumeImproveResponse;
import com.talentai.backend.improve.service.ResumeImproveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/improve")
public class ResumeImproveController {

    private final ResumeImproveService resumeImproveService;

    public ResumeImproveController(
            ResumeImproveService resumeImproveService) {

        this.resumeImproveService = resumeImproveService;
    }

    @PostMapping("/{resumeId}")
    public ResponseEntity<ResumeImproveResponse> improveResume(
            @PathVariable Long resumeId) {

        ResumeImproveResponse response =
                resumeImproveService.improveResume(resumeId);

        return ResponseEntity.ok(response);
    }
}