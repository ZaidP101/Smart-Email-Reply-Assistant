package com.zaid.patel.AI_Email_Assistant.Service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaid.patel.AI_Email_Assistant.DTo.EmailReqDto;
import com.zaid.patel.AI_Email_Assistant.Service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.lang.runtime.ObjectMethods;
import java.util.List;
import java.util.Map;
@Service
public class EmailServiceImp implements EmailService {

    private final WebClient webClient;

    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    public EmailServiceImp(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }


    @Override
    public ResponseEntity<String> EmailGenerator(EmailReqDto emailReqDto) {
        // Build Prompt
        String prompt = buildPrompt(emailReqDto);
        // Crafting like thr API demands
        Map<String, Object> requestBody = Map.of(
                "contents", List.of(
                        Map.of("parts", List.of(
                                Map.of("text", prompt)
                        ))
                )
        );
        // DO request and get request
        String response = webClient.post()
                .uri(geminiApiUrl + "?key=" + geminiApiKey)
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return extractResponse(response);
    }

    private ResponseEntity<String> extractResponse(String response) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response);
            String text =  rootNode.path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text")
                    .asText();
            return ResponseEntity.ok(text);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing request: " + e.getMessage());
        }
    }

    private String buildPrompt(EmailReqDto emailReqDto) {
        StringBuilder prompt = new StringBuilder();

        prompt.append("Generate a professional email reply using the provided context and raw reply.");

        if (emailReqDto.getTone() != null && !emailReqDto.getTone().isEmpty()) {
            prompt.append(" Use a ").append(emailReqDto.getTone()).append(" tone.");
        }

        prompt.append("\n\nOriginal Email:\n")
                .append(emailReqDto.getEmailContent());

        prompt.append("\n\nRaw Reply:\n")
                .append(emailReqDto.getRawReply());

        prompt.append("\n\nInstructions:\n")
                .append("- Use the Raw Reply to generate a polished and professional response.\n")
                .append("- Do not include any explanations or commentary, only the improved reply.\n");

        return prompt.toString();
    }

}
