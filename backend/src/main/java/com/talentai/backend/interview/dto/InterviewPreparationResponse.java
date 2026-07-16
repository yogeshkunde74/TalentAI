package com.talentai.backend.interview.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterviewPreparationResponse {

    private List<InterviewQuestion> technicalQuestions;

    private List<InterviewQuestion> hrQuestions;

    private List<InterviewQuestion> codingQuestions;

    private String overallTips;

}