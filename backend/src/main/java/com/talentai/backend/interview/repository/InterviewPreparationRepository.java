package com.talentai.backend.interview.repository;

import com.talentai.backend.interview.entity.InterviewPreparation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewPreparationRepository
        extends JpaRepository<InterviewPreparation, Long> {

}