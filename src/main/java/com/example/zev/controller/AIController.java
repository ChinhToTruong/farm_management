package com.example.zev.controller;

import com.example.zev.dto.request.ChatRequest;
import com.example.zev.service.AIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/ai-chat")
@RequiredArgsConstructor
public class AIController {
  private final AIService aiService;

  @GetMapping(
      value = "/stream",
      produces = MediaType.TEXT_EVENT_STREAM_VALUE
  )
  public Flux<String> stream(@RequestParam String message) {
    return aiService.chatStream(message);
  }

}
