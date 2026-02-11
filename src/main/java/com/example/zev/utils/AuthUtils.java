package com.example.zev.utils;

import com.example.zev.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthUtils {

  public static Long getUserId() {
    Authentication auth = SecurityContextHolder
        .getContext()
        .getAuthentication();

    if (auth == null || !auth.isAuthenticated()) {
      return null;
    }

    return ((User) auth.getPrincipal()).getId();
  }
}
