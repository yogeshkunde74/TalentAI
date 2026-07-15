package com.talentai.backend.job.repository;

import com.talentai.backend.job.entity.JobMatch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobMatchRepository
        extends JpaRepository<JobMatch, Long> {

}