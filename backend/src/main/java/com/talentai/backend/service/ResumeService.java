package com.talentai.backend.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.talentai.backend.entity.Resume;

public interface ResumeService {

    Resume uploadResume(Long userId, MultipartFile file) throws IOException;

    List<Resume> getUserResumes(Long userId);

}