package com.talentai.backend.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.talentai.backend.entity.Resume;
import com.talentai.backend.entity.User;
import com.talentai.backend.repository.ResumeRepository;
import com.talentai.backend.repository.UserRepository;
import com.talentai.backend.service.ResumeService;

@Service
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;

    public ResumeServiceImpl(ResumeRepository resumeRepository,
                             UserRepository userRepository) {

        this.resumeRepository = resumeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Resume uploadResume(Long userId, MultipartFile file) throws IOException {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String uploadDir = "uploads/";

        File directory = new File(uploadDir);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        Path path = Paths.get(uploadDir + fileName);

        Files.write(path, file.getBytes());

        Resume resume = Resume.builder()
                .fileName(file.getOriginalFilename())
                .filePath(path.toString())
                .user(user)
                .build();

        return resumeRepository.save(resume);
    }

    @Override
    public List<Resume> getUserResumes(Long userId) {

        return resumeRepository.findByUserId(userId);

    }

}