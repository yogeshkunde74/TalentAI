package com.talentai.backend.interview.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "interview_preparation")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InterviewPreparation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long resumeId;

    @Column(columnDefinition = "TEXT")
    private String technicalQuestions;

    @Column(columnDefinition = "TEXT")
    private String hrQuestions;

    @Column(columnDefinition = "TEXT")
    private String codingQuestions;

    @Column(columnDefinition = "TEXT")
    private String overallTips;

}