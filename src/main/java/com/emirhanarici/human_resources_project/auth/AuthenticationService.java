package com.emirhanarici.human_resources_project.auth;


import com.emirhanarici.human_resources_project.email.EmailService;
import com.emirhanarici.human_resources_project.exception.AuthenticationFailedException;
import com.emirhanarici.human_resources_project.exception.EmailAlreadyExistsException;
import com.emirhanarici.human_resources_project.exception.InvalidTokenException;
import com.emirhanarici.human_resources_project.exception.TokenExpiredException;
import com.emirhanarici.human_resources_project.model.EmailConfirmationToken;
import com.emirhanarici.human_resources_project.model.JobSeeker;
import com.emirhanarici.human_resources_project.model.role.Role;
import com.emirhanarici.human_resources_project.payload.response.ActivationResponse;
import com.emirhanarici.human_resources_project.repository.EmailConfirmationTokenRepository;
import com.emirhanarici.human_resources_project.repository.JobSeekerRepository;
import com.emirhanarici.human_resources_project.security.JwtService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JobSeekerRepository jobSeekerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;
    private final EmailConfirmationTokenRepository tokenRepository;
    private final EmailConfirmationTokenRepository emailConfirmationTokenRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${jwt.cookieExpiry}")
    private int cookieExpiry;

    @Value("${mailing.frontend.activation-url}")
    private String activationUrl;

    @Value("${spring.kafka.topic.email-validation}")
    private String emailValidationTopic;

    public AuthenticationResponse register(AuthenticationRequest request, HttpServletResponse response) throws MessagingException {

        var jobSeeker = JobSeeker.builder()
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .role(Role.JOB_SEEKER)
                .build();

        if (jobSeekerRepository.existsByEmail(jobSeeker.getEmail())) {
            throw new EmailAlreadyExistsException("User with email already exists. Please login instead or use a different email address.");
        }

        var savedUser = jobSeekerRepository.save(jobSeeker);

        kafkaTemplate.send(emailValidationTopic,"key-1", savedUser);

        var jwtToken = jwtService.generateToken(savedUser);

        ResponseCookie cookie = ResponseCookie.from("accessToken", jwtToken)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(cookieExpiry)
                .sameSite("Strict")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return AuthenticationResponse.builder()
                .jobSeeker(savedUser)
                .message("User registered successfully")
                .id(savedUser.getId())
                .build();
    }


    public AuthenticationResponse login(AuthenticationRequest request, HttpServletResponse response) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            var user = jobSeekerRepository.findByEmail(request.getEmail())
                    .orElseThrow();

            var jwtToken = jwtService.generateToken(user);

            ResponseCookie cookie = ResponseCookie.from("accessToken", jwtToken)
                    .httpOnly(true)
                    .secure(false)
                    .path("/")
                    .maxAge(cookieExpiry)
                    .sameSite("Strict")
                    .build();
            response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

            return AuthenticationResponse.builder()
                    .jobSeeker(user)
                    .message("User authenticated successfully")
                    .build();

        } catch (AuthenticationException e) {
            throw new AuthenticationFailedException("Authentication failed");
        }
    }

    @Transactional
    public ActivationResponse activateAccount(String token) throws MessagingException {
        EmailConfirmationToken savedToken = emailConfirmationTokenRepository.findByToken(token)
                .orElseThrow(() -> new InvalidTokenException("Invalid token, please try again."));

        if (LocalDateTime.now().isAfter(savedToken.getExpiresAt())) {
            kafkaTemplate.send(emailValidationTopic, savedToken.getJobSeeker());
            throw new TokenExpiredException("Activation token has expired. A new token has been sent.");
        }

        savedToken.getJobSeeker().setAccountVerified(true);
        savedToken.setValidatedAt(LocalDateTime.now());
        jobSeekerRepository.save(savedToken.getJobSeeker());

        return ActivationResponse.builder()
                .message("SUCCESS")
                .build();

    }


    public AuthenticationResponse logout(HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie.from("accessToken", "")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(0)
                .sameSite("None")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return AuthenticationResponse.builder()
                .message("User logged out successfully")
                .build();
    }


}
