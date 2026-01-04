package com.example.zev.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class AIService {
  private final WebClient webClient;
  private final ObjectMapper mapper = new ObjectMapper();

  public Flux<String> chatStream(String prompt) {
    String defaultPrompt = "Bạn là một kĩ sử nông nghiệp giàu kinh nghiệm hãy trả lời tôi các câu hỏi sau. ";
    Map<String, Object> body = Map.of(
        "model", "qwen2.5:7b-instruct",
        "prompt", defaultPrompt + prompt,
        "stream", true,
        "num_ctx", 2048,
        "num_predict", 1028,
        "temperature", 0.2
    );

    return webClient.post()
        .uri("/api/generate")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(body)
        .retrieve()
        .bodyToFlux(String.class)
        .flatMap(this::parseNdjson);
  }

  private Flux<String> parseNdjson(String chunk) {
    return Flux.fromArray(chunk.split("\n"))
        .flatMap(line -> {
          try {
            if (line.isBlank()) {
              return Flux.just(" ");
            }

            JsonNode json = mapper.readTree(line);

            if (json.has("response")) {
              String token = json.get("response").asText();
              return Flux.fromStream(
                  token.codePoints()
                      .mapToObj(cp -> new String(Character.toChars(cp)))
              );
            }

          } catch (Exception e) {
            // ignore malformed json chunk
          }
          return Flux.empty();
        });
  }
}
