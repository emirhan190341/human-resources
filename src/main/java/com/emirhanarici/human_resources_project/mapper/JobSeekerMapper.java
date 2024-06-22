package com.emirhanarici.human_resources_project.mapper;

import com.emirhanarici.human_resources_project.model.JobSeeker;
import com.emirhanarici.human_resources_project.payload.request.CreateJobSeekerRequest;
import com.emirhanarici.human_resources_project.payload.response.JobSeekerResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JobSeekerMapper {

    public JobSeeker mapToJobSeeker(CreateJobSeekerRequest request) {
        return JobSeeker.builder()
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .email(request.getEmail())
                .password(request.getPassword())
                .address(request.getAddress())
                .mobilPhone(request.getMobilPhone())
                .nationalityId(request.getNationalityId())
                .birthYear(request.getBirthYear())
                .profilePicture(request.getProfilePicture())
                .build();
    }

    public JobSeekerResponse mapToJobSeekerResponse(JobSeeker jobSeeker) {
        return JobSeekerResponse.builder()
                .id(jobSeeker.getId())
                .firstName(jobSeeker.getFirstName())
                .lastName(jobSeeker.getLastName())
                .email(jobSeeker.getEmail())
                .password(jobSeeker.getPassword())
                .address(jobSeeker.getAddress())
                .mobilPhone(jobSeeker.getMobilPhone())
                .nationalityId(jobSeeker.getNationalityId())
                .birthYear(jobSeeker.getBirthYear())
                .profilePicture(jobSeeker.getProfilePicture())
                .build();
    }




}
