package com.talentai.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talentai.backend.entity.Resume;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

    List<Resume> findByUserId(Long userId);

}