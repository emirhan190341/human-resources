package com.emirhanarici.human_resources_project.repository;

import com.emirhanarici.human_resources_project.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job,String > {
}
