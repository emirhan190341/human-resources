package com.emirhanarici.human_resources_project.mapper;

import com.emirhanarici.human_resources_project.model.Experience;
import com.emirhanarici.human_resources_project.payload.request.CreateExperienceRequest;
import com.emirhanarici.human_resources_project.payload.response.ExperienceResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ExperienceMapper {

    public Experience mapToJobSeekerExperience(CreateExperienceRequest request) {
        return Experience.builder()
                .position(request.getPosition())
                .companyName(request.getCompanyName())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .country(request.getCountry())
                .city(request.getCity())
                .description(request.getDescription())
                .build();
    }

    public ExperienceResponse mapToJobSeekerExperienceResponse(Experience experience) {
        return ExperienceResponse.builder()
                .id(experience.getId())
                .position(experience.getPosition())
                .companyName(experience.getCompanyName())
                .startDate(experience.getStartDate())
                .endDate(experience.getEndDate())
                .country(experience.getCountry())
                .city(experience.getCity())
                .description(experience.getDescription())
                .jobSeekerId(experience.getJobSeeker().getId())
                .build();
    }
}
