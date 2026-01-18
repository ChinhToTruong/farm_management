package com.example.zev.config;

import com.example.zev.entity.User;
import com.example.zev.repository.UserRepository;
import com.example.zev.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class AuthChannelInterceptor implements ChannelInterceptor {

  @Autowired
  private JwtService jwtService; // service verify token
  @Autowired
  private UserRepository userRepository;

  @Override
  public Message<?> preSend(Message<?> message, MessageChannel channel) {

    StompHeaderAccessor accessor =
        MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

    if (StompCommand.CONNECT.equals(accessor.getCommand())) {

      String authHeader = accessor.getFirstNativeHeader("Authorization");

      if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        throw new IllegalArgumentException("Missing Authorization header");
      }

      String token = authHeader.substring(7);

      // ✅ validate token
      String username = jwtService.extractUsername(token);
      if (username == null) {
        throw new IllegalArgumentException("Invalid token");
      }
      User user = userRepository.findByEmail(username)
          .orElseThrow(() -> new IllegalArgumentException("Invalid user"));

      // gán user vào WebSocket session
      accessor.setUser(
          new UsernamePasswordAuthenticationToken(
              user, null, user.getAuthorities()
          )
      );
    }

    return message;
  }
}
