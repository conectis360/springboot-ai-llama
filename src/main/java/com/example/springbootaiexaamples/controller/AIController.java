package com.example.springbootaiexaamples.controller;

import com.example.springbootaiexaamples.model.ChatRequest;
import com.example.springbootaiexaamples.model.ChatResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class AIController {

    private final RestTemplate restTemplate;

    // External AI API URLs (These will be injected from application properties)
    @Value("${external.ai.api.models}")
    private String modelsUrl;

    @Value("${external.ai.api.completions}")
    private String chatCompletionsUrl;

    public AIController(RestTemplateBuilder restTemplateBuilder ) {
        this.restTemplate  = restTemplateBuilder.build();
    }

    // GET /v1/models
    @GetMapping("/models")
    public ResponseEntity<String> getModels() {
        // Call the external /v1/models endpoint
        ResponseEntity<String> response = restTemplate.getForEntity(modelsUrl, String.class);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    // POST /v1/chat/completions
    @PostMapping("/chat")
    public ResponseEntity<String> sendChatRequest(@RequestBody ChatRequest chatRequest) throws JsonProcessingException {
        // Set up HTTP headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        // Create the request entity
        HttpEntity<ChatRequest> requestEntity = new HttpEntity<>(chatRequest, headers);

        // Send the POST request to the external API and get the response
        ResponseEntity<String> response = restTemplate.exchange(
                chatCompletionsUrl, // The external API URL
                HttpMethod.POST, // HTTP method
                requestEntity, // The request with headers and body
                String.class // The response type
        );

        System.out.println(response.getBody());

      ObjectMapper objectMapper = new ObjectMapper();
        ChatResponse chatResponse = objectMapper.readValue(response.getBody(), ChatResponse.class);

        System.out.println();

        String responseString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(chatResponse.getChoices().getFirst().getMessage());

        // Return the response to the client
        return ResponseEntity.status(response.getStatusCode()).body(responseString
        );
    }
}
