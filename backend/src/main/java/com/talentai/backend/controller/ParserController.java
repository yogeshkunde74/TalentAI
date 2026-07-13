package com.talentai.backend.controller;

import com.talentai.backend.parser.ResumeParser;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/parser")
public class ParserController {

    private final ResumeParser resumeParser;

    public ParserController(ResumeParser resumeParser) {
        this.resumeParser = resumeParser;
    }

    @PostMapping("/extract")
    public String extractResume(@RequestParam("file") MultipartFile file) {

        String text = resumeParser.extractText(file);

        return text;
    }
}