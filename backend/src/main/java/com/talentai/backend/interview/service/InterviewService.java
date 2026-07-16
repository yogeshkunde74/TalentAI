package com.talentai.backend.interview.service;

import com.talentai.backend.interview.dto.InterviewPreparationResponse;

public interface InterviewService {

    InterviewPreparationResponse prepareInterview(Long resumeId);

}