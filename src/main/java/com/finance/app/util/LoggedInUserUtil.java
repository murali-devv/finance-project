package com.finance.app.util;

import com.finance.app.entity.User;
import com.finance.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoggedInUserUtil {

    private final UserRepository userRepository;

    public User getLoggedInUser() {
        try {
            var authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null) {
                log.error("Authentication is null in SecurityContext");
                throw new RuntimeException("No authentication found");
            }

            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails) {
                String email = ((UserDetails) principal).getUsername();
                log.info("Getting user from UserDetails: {}", email);
                return userRepository.findByEmail(email)
                        .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
            } else if (principal instanceof String) {
                String email = (String) principal;
                log.info("Getting user from String principal: {}", email);
                return userRepository.findByEmail(email)
                        .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
            } else {
                log.error("Unknown principal type: {}", principal.getClass());
                throw new RuntimeException("Unknown principal type");
            }
        } catch (Exception e) {
            log.error("Error getting logged in user: {}", e.getMessage());
            throw new RuntimeException("Failed to get authenticated user: " + e.getMessage(), e);
        }
    }
}