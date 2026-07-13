package com.talentai.backend.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.talentai.backend.entity.Resume;
import com.talentai.backend.service.ResumeService;

@RestController
@RequestMapping("/api/resumes")
public class ResumeController {

    private final ResumeService resumeService;

    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @PostMapping("/upload")
    public ResponseEntity<Resume> uploadResume(

            @RequestParam Long userId,
            @RequestParam("file") MultipartFile file

    ) throws IOException {

        Resume resume = resumeService.uploadResume(userId, file);

        return ResponseEntity.ok(resume);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Resume>> getUserResumes(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                resumeService.getUserResumes(userId)
        );
    }

}