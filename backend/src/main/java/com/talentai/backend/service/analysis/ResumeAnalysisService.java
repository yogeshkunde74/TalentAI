package com.talentai.backend.service.analysis;

import com.talentai.backend.dto.ResumeAnalysisResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResumeAnalysisService {

    private static final List<String> SKILLS = List.of(
            "Java",
            "Spring Boot",
            "React",
            "Angular",
            "Node.js",
            "Python",
            "JavaScript",
            "HTML",
            "CSS",
            "SQL",
            "PostgreSQL",
            "MySQL",
            "MongoDB",
            "AWS",
            "Docker",
            "Kubernetes",
            "Git",
            "REST API",
            "Hibernate"
    );

    public ResumeAnalysisResponse analyze(String resumeText) {

        List<String> foundSkills = new ArrayList<>();

        for (String skill : SKILLS) {

            if (resumeText.toLowerCase().contains(skill.toLowerCase())) {
                foundSkills.add(skill);
            }
        }

        int score = (foundSkills.size() * 100) / SKILLS.size();

        List<String> suggestions = new ArrayList<>();

        if (!foundSkills.contains("Docker"))
            suggestions.add("Learn Docker");

        if (!foundSkills.contains("AWS"))
            suggestions.add("Learn AWS");

        if (!foundSkills.contains("Kubernetes"))
            suggestions.add("Learn Kubernetes");

        return ResumeAnalysisResponse.builder()
                .score(score)
                .skills(foundSkills)
                .missingSkills(suggestions)
                .strengths(foundSkills)
                .suggestions(suggestions)
                .build();
    }
}