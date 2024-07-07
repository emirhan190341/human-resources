package com.emirhanarici.human_resources_project.email;

import com.emirhanarici.human_resources_project.dto.ApplyJobMessage;
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

import static com.emirhanarici.human_resources_project.utils.Utils.generateActivationCode;

@Service
@RequiredArgsConstructor
public class EmailConsumer {

    private final EmailService emailService;
    private final EmailConfirmationTokenRepository tokenRepository;

    @Value("${mailing.frontend.activation-url}")
    private String activationUrl;



    @KafkaListener(topics = "${spring.kafka.topic.email-validation}", groupId = "email-group")
    public void listen(@Payload JobSeeker jobSeeker) throws MessagingException {
        sendValidationEmail(jobSeeker);
    }

    /*

    @KafkaListener(topics = "${spring.kafka.topic.apply-job}", groupId = "apply-group")
    public void listen(@Payload ApplyJobMessage applyJobMessage) throws MessagingException {
        sendApplyJobEmail(applyJobMessage);
    }

    private void sendApplyJobEmail(ApplyJobMessage applyJobMessage) throws MessagingException {
        var jobSeeker = applyJobMessage.getJobSeeker();
        var job = applyJobMessage.getJob();

        Map<String, Object> properties = new HashMap<>();
        properties.put("username", jobSeeker.getFirstName() + " " + jobSeeker.getLastName());
        properties.put("user_email", jobSeeker.getEmail());
        properties.put("job_title", job.getPosition());
        properties.put("application_date", applyJobMessage.getApplyJob().getApplyDate());

        emailService.sendEmail(
                jobSeeker.getEmail(),
                EmailTemplateName.APPLY_JOB_SUCCESS,
                "Job Application",
                properties
        );
    }

     */


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

    public String generateAndSaveActivationToken(JobSeeker user) {

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


}
