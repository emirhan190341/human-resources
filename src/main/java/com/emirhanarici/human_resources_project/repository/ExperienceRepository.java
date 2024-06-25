package com.emirhanarici.human_resources_project.repository;

import com.emirhanarici.human_resources_project.model.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExperienceRepository extends JpaRepository<Experience,Long> {

    Optional<List<Experience>> findAllByJobSeekerId(Long jobSeekerId);
}
