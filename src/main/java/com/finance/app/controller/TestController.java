package com.finance.app.controller;

import com.finance.app.entity.User;
import com.finance.app.util.LoggedInUserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestController {

    private final LoggedInUserUtil userUtil;

    @GetMapping("/auth-check")
    public ResponseEntity<?> checkAuth() {
        Map<String, Object> response = new HashMap<>();

        try {
            var auth = SecurityContextHolder.getContext().getAuthentication();
            response.put("authentication_null", auth == null);

            if (auth != null) {
                response.put("authenticated", auth.isAuthenticated());
                response.put("principal", auth.getPrincipal().toString());
                response.put("authorities", auth.getAuthorities().toString());
            }

            User user = userUtil.getLoggedInUser();
            response.put("user_found", true);
            response.put("email", user.getEmail());
            response.put("role", user.getRole().name());
            response.put("status", "success");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("error", e.getMessage());
            return ResponseEntity.status(401).body(response);
        }
    }
}