package com.emirhanarici.human_resources_project.auth;

import com.emirhanarici.human_resources_project.exception.AuthenticationFailedException;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid AuthenticationRequest request, HttpServletResponse response) throws MessagingException {
        return ResponseEntity.ok(authenticationService.register(request, response));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationRequest request) {
        try {
            AuthenticationResponse response = authenticationService.login(request);
            return ResponseEntity.ok(response);
        } catch (AuthenticationFailedException e) {

            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }

    @GetMapping("/activate-account")
    public void confirmAccount(@RequestParam String token) throws MessagingException {
        authenticationService.activateAccount(token);
    }


}
