package com.talentai.backend.improve.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "resume_improvement")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResumeImprovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long resumeId;

    @Column(columnDefinition = "TEXT")
    private String professionalSummary;

    @Column(columnDefinition = "TEXT")
    private String improvedSkills;

    @Column(columnDefinition = "TEXT")
    private String improvedProjects;

    @Column(columnDefinition = "TEXT")
    private String atsKeywords;

    @Column(columnDefinition = "TEXT")
    private String finalSuggestions;

    private int improvedScore;
}