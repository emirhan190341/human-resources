package com.emirhanarici.human_resources_project.service;

import com.emirhanarici.human_resources_project.mapper.JobSeekerExperienceMapper;
import com.emirhanarici.human_resources_project.model.JobSeeker;
import com.emirhanarici.human_resources_project.model.JobSeekerExperience;
import com.emirhanarici.human_resources_project.payload.request.CreateJobSeekerExperienceRequest;
import com.emirhanarici.human_resources_project.payload.response.JobSeekerExperienceResponse;
import com.emirhanarici.human_resources_project.repository.JobSeekerExperienceRepository;
import com.emirhanarici.human_resources_project.repository.JobSeekerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobSeekerExperienceService {

    private final JobSeekerExperienceRepository jobSeekerExperienceRepository;
    private final JobSeekerRepository jobSeekerRepository;


    public JobSeekerExperienceResponse createJobSeekerExperience(CreateJobSeekerExperienceRequest request, Long jobSeekerId) {

        JobSeeker jobSeeker = jobSeekerRepository.findById(jobSeekerId)
                .orElseThrow(() -> new RuntimeException("JobSeeker not found"));

        JobSeekerExperience jobSeekerExperience = JobSeekerExperienceMapper.mapToJobSeekerExperience(request);

        jobSeekerExperience.setJobSeeker(jobSeeker);

        jobSeekerExperience = jobSeekerExperienceRepository.save(jobSeekerExperience);

        return JobSeekerExperienceMapper.mapToJobSeekerExperienceResponse(jobSeekerExperience);
    }




}
