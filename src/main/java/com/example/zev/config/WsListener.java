package com.example.zev.config;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

@Component
public class WsListener {
  @EventListener
  public void onConnect(SessionConnectedEvent event) {
    StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
    System.out.println("🟢 WS CONNECT user = " + accessor.getUser());
  }

  @EventListener
  public void onSubscribe(SessionSubscribeEvent event) {
    StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
    System.out.println(
        "📩 SUBSCRIBE dest=" + accessor.getDestination()
            + " user=" + accessor.getUser()
    );
  }
}
