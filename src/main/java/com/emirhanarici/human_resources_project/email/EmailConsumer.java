package com.emirhanarici.human_resources_project.email;

import com.emirhanarici.human_resources_project.model.EmailConfirmationToken;
import com.emirhanarici.human_resources_project.model.JobSeeker;
import com.emirhanarici.human_resources_project.repository.EmailConfirmationTokenRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailConsumer {

    private final EmailService emailService;
    private final EmailConfirmationTokenRepository tokenRepository;

    @Value("${mailing.frontend.activation-url}")
    private String activationUrl;

    @KafkaListener(topics = "${spring.kafka.topic.email-validation}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(@Payload JobSeeker jobSeeker) throws MessagingException {
        // Logic to send email validation
        sendValidationEmail(jobSeeker);
    }

    private String generateAndSaveActivationToken(JobSeeker user) {

        String generatedToken = generateActivationCode(6);
        var token = EmailConfirmationToken.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(1))
                .jobSeeker(user)
                .build();

        tokenRepository.save(token);

        return generatedToken;
    }

    private void sendValidationEmail(JobSeeker user) throws MessagingException {
        var newToken = generateAndSaveActivationToken(user);

        Map<String, Object> properties = new HashMap<>();
        properties.put("username", user.getFirstName() + " " + user.getLastName());
        properties.put("confirmationUrl", activationUrl);
        properties.put("activation_code", newToken);

        emailService.sendEmail(
                user.getEmail(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                "Account activation",
                properties
        );

    }

    private String generateActivationCode(int length) {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();

        SecureRandom secureRandom = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }

        return codeBuilder.toString();
    }


}
