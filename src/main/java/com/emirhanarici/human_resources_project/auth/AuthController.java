package com.emirhanarici.human_resources_project.auth;

import com.emirhanarici.human_resources_project.exception.AuthenticationFailedException;
import com.emirhanarici.human_resources_project.payload.CustomResponse;
import com.emirhanarici.human_resources_project.payload.response.ActivationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Authentication", description = "API for authentication operations such as register, login and logout.")
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Registers a new user and returns a success message.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "User registered successfully",
                            content = @Content(schema = @Schema(implementation = CustomResponse.class)))
            })
    public ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid AuthenticationRequest request, HttpServletResponse response) throws MessagingException {
        return ResponseEntity.ok(authenticationService.register(request, response));
    }

    @PostMapping("/login")
    @Operation(summary = "Login", description = "Logs in a user and returns a JWT token.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User logged in successfully",
                            content = @Content(schema = @Schema(implementation = AuthenticationResponse.class))),
                    @ApiResponse(responseCode = "401", description = "Invalid credentials",
                            content = @Content(schema = @Schema(implementation = Map.class)))
            })
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationRequest request, HttpServletResponse response) {
        try {
            AuthenticationResponse rs = authenticationService.login(request, response);
            return ResponseEntity.ok(rs);
        } catch (AuthenticationFailedException e) {

            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }


    @PostMapping("/logout")
    @Operation(summary = "Logout", description = "Logs out a user and deletes the JWT token.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User logged out successfully",
                            content = @Content(schema = @Schema(implementation = AuthenticationResponse.class)))
            })
    public ResponseEntity<AuthenticationResponse> logout(HttpServletResponse response) {



        return ResponseEntity.ok(authenticationService.logout(response));
    }

    @GetMapping("/activate-account")
    @Operation(summary = "Activate account", description = "Activates a user account.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Account activated successfully",
                            content = @Content(schema = @Schema(implementation = ActivationResponse.class)))
            })
    public ResponseEntity<ActivationResponse> confirmAccount(@RequestParam String token) throws MessagingException {
        authenticationService.activateAccount(token);
        return ResponseEntity.ok(authenticationService.activateAccount(token));
    }


}
