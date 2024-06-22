package com.emirhanarici.human_resources_project.auth;


import com.emirhanarici.human_resources_project.security.JwtService;
import com.emirhanarici.human_resources_project.exception.AuthenticationFailedException;
import com.emirhanarici.human_resources_project.exception.EmailAlreadyExistsException;
import com.emirhanarici.human_resources_project.model.JobSeeker;
import com.emirhanarici.human_resources_project.model.role.Role;
import com.emirhanarici.human_resources_project.repository.JobSeekerRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JobSeekerRepository jobSeekerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Value("${jwt.cookieExpiry}")
    private int cookieExpiry;

    public AuthenticationResponse register(AuthenticationRequest request, HttpServletResponse response) {

        var jobSeeker = JobSeeker.builder()
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role(Role.JOB_SEEKER)
                .build();


        if (jobSeekerRepository.existsByEmail(jobSeeker.getEmail())) {
            throw new EmailAlreadyExistsException("User with email already exists");
        }


        var savedUser = jobSeekerRepository.save(jobSeeker);
        var jwtToken = jwtService.generateToken(savedUser);

        ResponseCookie cookie = ResponseCookie.from("accessToken", jwtToken)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(cookieExpiry)
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .message("User registered successfully")
                .build();
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
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


            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .message("User authenticated successfully")
                    .build();

        } catch (AuthenticationException e) {
            throw new AuthenticationFailedException("Authentication failed");
        }
    }


}
