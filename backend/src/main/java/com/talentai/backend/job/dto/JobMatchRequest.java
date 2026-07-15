package com.talentai.backend.job.dto;

import lombok.Data;

@Data
public class JobMatchRequest {

    private Long resumeId;

    private String jobDescription;

}