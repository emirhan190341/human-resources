package com.emirhanarici.human_resources_project.repository;

import com.emirhanarici.human_resources_project.model.EmailConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailConfirmationTokenRepository extends JpaRepository<EmailConfirmationToken,Integer> {

    Optional<EmailConfirmationToken> findByToken(String token);
}
