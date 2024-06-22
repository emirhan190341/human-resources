package com.emirhanarici.human_resources_project.repository;

import com.emirhanarici.human_resources_project.model.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobSeekerRepository extends JpaRepository<JobSeeker, Long> {
    Optional<JobSeeker> findByEmail(String email);
    boolean existsByEmail(String email);
}
