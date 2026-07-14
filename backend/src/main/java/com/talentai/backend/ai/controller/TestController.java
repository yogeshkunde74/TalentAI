package com.talentai.backend.ai.controller;

import com.google.genai.Client;
import com.google.genai.types.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final Client client;

    public TestController(Client client) {
        this.client = client;
    }

    @GetMapping("/api/models")
    public String listModels() {

        StringBuilder sb = new StringBuilder();

        for (Model model : client.models.list(null)) {
            sb.append(model.name()).append("\n");
        }

        return sb.toString();
    }
}