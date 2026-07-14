package com.talentai.backend.ai.client;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GeminiClient {

    private final Client client;

    @Value("${gemini.model}")
    private String model;

    public GeminiClient(Client client) {
        this.client = client;
    }

    public String generateContent(String prompt) {

        GenerateContentResponse response =
                client.models.generateContent(
                        model,
                        prompt,
                        null
                );

        return response.text();
    }
}