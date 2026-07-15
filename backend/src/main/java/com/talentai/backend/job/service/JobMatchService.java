package com.talentai.backend.job.service;

import com.talentai.backend.job.dto.JobMatchResponse;

public interface JobMatchService {

    JobMatchResponse matchResume(
            Long resumeId,
            String jobDescription);

}