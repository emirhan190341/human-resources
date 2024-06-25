package com.emirhanarici.human_resources_project.service;

import com.emirhanarici.human_resources_project.exception.EducationNotFoundException;
import com.emirhanarici.human_resources_project.exception.ExperienceNotFoundException;
import com.emirhanarici.human_resources_project.mapper.EducationMapper;
import com.emirhanarici.human_resources_project.model.Education;
import com.emirhanarici.human_resources_project.model.Experience;
import com.emirhanarici.human_resources_project.model.JobSeeker;
import com.emirhanarici.human_resources_project.payload.request.CreateEducationRequest;
import com.emirhanarici.human_resources_project.payload.response.EducationResponse;
import com.emirhanarici.human_resources_project.repository.EducationRepository;
import com.emirhanarici.human_resources_project.repository.JobSeekerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EducationService {

    private final EducationRepository educationRepository;
    private final JobSeekerRepository jobSeekerRepository;


    public EducationResponse createJobSeekerEducation(CreateEducationRequest request, Long jobSeekerId) {
        JobSeeker jobSeeker = jobSeekerRepository.findById(jobSeekerId)
                .orElseThrow(() -> new RuntimeException("JobSeeker not found"));

        Education education = EducationMapper.mapToJobSeekerEducation(request);

        education.setJobSeeker(jobSeeker);
        education = educationRepository.save(education);

        return EducationMapper.mapToJobSeekerEducationResponse(education);
    }

    public List<EducationResponse> getJobSeekerEducationsByJobSeekerId(Long jobSeekerId) {

        List<Education> experience = educationRepository.findAllByJobSeekerId(jobSeekerId)
                .orElseThrow(() -> new EducationNotFoundException("Education not found"));

        return experience.stream()
                .map(EducationMapper::mapToJobSeekerEducationResponse)
                .collect(Collectors.toList());
    }
}
