package com.rizwan.emailWriter_sb.Services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rizwan.emailWriter_sb.DTOS.EmailRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class EmailGeneratedService {
    private final WebClient webClient;
    @Value("${gemini.api.url}")
    private String geminiApiUrl;
    @Value("${gemini.api.key}")
    private String geminiApiKey;

    public EmailGeneratedService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("https://generativelanguage.googleapis.com")
                .build();

    }

    public String generatedEmailReply(EmailRequest request){
        String prompt = buildPrompt(request);
//            crafting a request
//        Map<String,Object> requestBody = Map.of(
//                "contents",new Object[]{
//                        Map.of(
//                                "parts",new Object[]{
//                                        Map.of(
//                                                "text",prompt
//                                        )
//                                }
//                        )
//                }
//        );
        Map<String, Object> requestBody = Map.of(
                "model", "models/gemini-pro",
                "contents", List.of(
                        Map.of(
                                "role", "user",
                                "parts", List.of(
                                        Map.of("text", prompt)
                                )
                        )
                )
        );


        String response = webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1beta/models/gemini-2.0-flash:generateContent")
                        .queryParam("key","AIzaSyBzedTjevkSlZE4gNOPK5CqDx8p7fK6k5s")
                        .build())
                .header("Content-Type","application/json")
                .bodyValue(requestBody)
                .retrieve().bodyToMono(String.class)
                .block();
//return the response
        return extractResponse(response);

    }

    private String buildPrompt(EmailRequest request){
        //    building a prompt
        StringBuilder prompt = new StringBuilder();
        prompt.append("You are a professional email assistant.\n");
        prompt.append("Write a professional email reply to the message below. ");
        prompt.append("Do not include a subject line. ");

        if (request.getTone() != null && !request.getTone().isEmpty()) {
            prompt.append("Use a ").append(request.getTone()).append(" tone. ");
        }

        prompt.append("Only write the reply.\n\n");
        prompt.append("Original Email:\n");
        prompt.append("\"\"\"\n").append(request.getEmailContent()).append("\n\"\"\"");

        return prompt.toString();

    }

//     Do request and get Response


    private String extractResponse(String response){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode node = objectMapper.readTree(response);
            return node.path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text").asText();
        }catch (Exception e){
            return "Error processing request" + e.getMessage();
        }
    }

}
