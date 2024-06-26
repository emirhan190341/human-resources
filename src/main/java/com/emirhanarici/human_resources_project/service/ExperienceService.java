package com.emirhanarici.human_resources_project.service;

import com.emirhanarici.human_resources_project.exception.ExperienceNotFoundException;
import com.emirhanarici.human_resources_project.mapper.ExperienceMapper;
import com.emirhanarici.human_resources_project.model.JobSeeker;
import com.emirhanarici.human_resources_project.model.Experience;
import com.emirhanarici.human_resources_project.payload.request.CreateExperienceRequest;
import com.emirhanarici.human_resources_project.payload.response.ExperienceResponse;
import com.emirhanarici.human_resources_project.repository.ExperienceRepository;
import com.emirhanarici.human_resources_project.repository.JobSeekerRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExperienceService {

    private final ExperienceRepository experienceRepository;
    private final JobSeekerRepository jobSeekerRepository;


    public ExperienceResponse createJobSeekerExperience(CreateExperienceRequest request, Long jobSeekerId) {

        JobSeeker jobSeeker = jobSeekerRepository.findById(jobSeekerId)
                .orElseThrow(() -> new RuntimeException("JobSeeker not found"));

        Experience experience = ExperienceMapper.mapToJobSeekerExperience(request);

        experience.setJobSeeker(jobSeeker);
        experience = experienceRepository.save(experience);

        return ExperienceMapper.mapToJobSeekerExperienceResponse(experience);
    }


    public List<ExperienceResponse> getJobSeekerExperiencesByJobSeekerId(Long jobSeekerId, HttpServletRequest request) {

        log.info("ExperienceService.getJobSeekerExperiencesByJobSeekerId jobSeekerId: {}", jobSeekerId  );

        List<Experience> experience = experienceRepository.findAllByJobSeekerId(jobSeekerId)
                .orElseThrow(() -> new ExperienceNotFoundException("Experience not found"));

            return experience.stream()
                    .map(ExperienceMapper::mapToJobSeekerExperienceResponse)
                    .collect(Collectors.toList());
    }
}
