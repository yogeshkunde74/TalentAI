package com.talentai.backend.ai.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "resume_analysis")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumeAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int overallScore;

    private int technicalScore;

    private int communicationScore;

    private int experienceScore;

    @Column(columnDefinition = "TEXT")
    private String skills;

    @Column(columnDefinition = "TEXT")
    private String missingSkills;

    @Column(columnDefinition = "TEXT")
    private String strengths;

    @Column(columnDefinition = "TEXT")
    private String weaknesses;

    @Column(columnDefinition = "TEXT")
    private String interviewQuestions;

}