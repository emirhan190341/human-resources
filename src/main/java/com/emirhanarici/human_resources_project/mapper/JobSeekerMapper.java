package com.emirhanarici.human_resources_project.mapper;

import com.emirhanarici.human_resources_project.model.JobSeeker;
import com.emirhanarici.human_resources_project.payload.request.CreateJobSeekerRequest;
import com.emirhanarici.human_resources_project.payload.request.UpdateJobSeekerRequest;
import com.emirhanarici.human_resources_project.payload.response.JobSeekerResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JobSeekerMapper {

    public JobSeeker mapToJobSeeker(CreateJobSeekerRequest request) {
        return JobSeeker.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .address(request.getAddress())
                .mobilPhone(request.getMobilPhone())
                .nationalityId(request.getNationalityId())
                .birthYear(request.getBirthYear())
                .profilePicture(request.getProfilePicture())
                .position(request.getPosition())
                .github(request.getGithub())
                .linkedin(request.getLinkedin())
                .biography(request.getBiography())
                .website(request.getWebsite())
                .languages(request.getLanguages())
                .skills(request.getSkills())
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
                .city(jobSeeker.getCity())
                .country(jobSeeker.getCountry())
                .mobilPhone(jobSeeker.getMobilPhone())
                .nationalityId(jobSeeker.getNationalityId())
                .birthYear(jobSeeker.getBirthYear())
                .profilePicture(jobSeeker.getProfilePicture())
                .position(jobSeeker.getPosition())
                .github(jobSeeker.getGithub())
                .linkedin(jobSeeker.getLinkedin())
                .biography(jobSeeker.getBiography())
                .website(jobSeeker.getWebsite())
                .languages(jobSeeker.getLanguages())
                .skills(jobSeeker.getSkills())
                .build();
    }

    public JobSeeker updateJobSeekerFromRequest(JobSeeker jobSeeker, UpdateJobSeekerRequest request) {
        jobSeeker.setFirstName(request.getFirstName());
        jobSeeker.setLastName(request.getLastName());
        jobSeeker.setAddress(request.getAddress());
        jobSeeker.setCountry(request.getCountry());
        jobSeeker.setCity(request.getCity());
        jobSeeker.setMobilPhone(request.getMobilPhone());
        jobSeeker.setNationalityId(request.getNationalityId());
        jobSeeker.setBirthYear(request.getBirthYear());
        jobSeeker.setProfilePicture(request.getProfilePicture());
        jobSeeker.setPosition(request.getPosition());
        jobSeeker.setGithub(request.getGithub());
        jobSeeker.setLinkedin(request.getLinkedin());
        jobSeeker.setBiography(request.getBiography());
        jobSeeker.setWebsite(request.getWebsite());
        jobSeeker.setLanguages(request.getLanguages());
        jobSeeker.setSkills(request.getSkills());
        return jobSeeker;
    }

}
