package com.emirhanarici.human_resources_project.mapper;

import com.emirhanarici.human_resources_project.model.Education;
import com.emirhanarici.human_resources_project.payload.request.CreateEducationRequest;
import com.emirhanarici.human_resources_project.payload.response.EducationResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EducationMapper {
    public static Education mapToJobSeekerEducation(CreateEducationRequest request) {
        return Education.builder()
                .schoolName(request.getSchoolName())
                .department(request.getDepartment())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .country(request.getCountry())
                .city(request.getCity())
                .description(request.getDescription())
                .build();
    }

    public static EducationResponse mapToJobSeekerEducationResponse(Education education) {

        return EducationResponse.builder()
                .id(education.getId())
                .schoolName(education.getSchoolName())
                .department(education.getDepartment())
                .startDate(education.getStartDate())
                .endDate(education.getEndDate())
                .country(education.getCountry())
                .city(education.getCity())
                .description(education.getDescription())
                .jobSeekerId(education.getJobSeeker().getId())
                .build();
    }
}
