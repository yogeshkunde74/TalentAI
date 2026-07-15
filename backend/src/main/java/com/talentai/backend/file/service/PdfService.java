package com.talentai.backend.file.service;

import org.springframework.web.multipart.MultipartFile;

public interface PdfService {

    String extractText(MultipartFile file);

}