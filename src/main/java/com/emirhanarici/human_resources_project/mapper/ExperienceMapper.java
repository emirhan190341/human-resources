package com.emirhanarici.human_resources_project.mapper;

import com.emirhanarici.human_resources_project.model.JobSeekerExperience;
import com.emirhanarici.human_resources_project.payload.request.CreateJobSeekerExperienceRequest;
import com.emirhanarici.human_resources_project.payload.response.JobSeekerExperienceResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JobSeekerExperienceMapper {

    public JobSeekerExperience mapToJobSeekerExperience(CreateJobSeekerExperienceRequest request) {
        return JobSeekerExperience.builder()
                .position(request.getPosition())
                .companyName(request.getCompanyName())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .country(request.getCountry())
                .city(request.getCity())
                .description(request.getDescription())
                .build();
    }

    public JobSeekerExperienceResponse mapToJobSeekerExperienceResponse(JobSeekerExperience jobSeekerExperience) {
        return JobSeekerExperienceResponse.builder()
                .id(jobSeekerExperience.getId())
                .position(jobSeekerExperience.getPosition())
                .companyName(jobSeekerExperience.getCompanyName())
                .startDate(jobSeekerExperience.getStartDate())
                .endDate(jobSeekerExperience.getEndDate())
                .country(jobSeekerExperience.getCountry())
                .city(jobSeekerExperience.getCity())
                .description(jobSeekerExperience.getDescription())
                .jobSeekerId(jobSeekerExperience.getJobSeeker().getId())
                .build();
    }
}
