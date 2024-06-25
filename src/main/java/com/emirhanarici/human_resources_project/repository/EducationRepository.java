package com.emirhanarici.human_resources_project.repository;

import com.emirhanarici.human_resources_project.model.Education;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EducationRepository extends JpaRepository<Education, Long>{


    Optional<List<Education>> findAllByJobSeekerId(Long jobSeekerId);
}
