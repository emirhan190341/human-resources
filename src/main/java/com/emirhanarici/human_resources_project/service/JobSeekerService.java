package com.emirhanarici.human_resources_project.service;

import com.emirhanarici.human_resources_project.exception.JobSeekerNotFoundException;

import com.emirhanarici.human_resources_project.mapper.JobSeekerMapper;
import com.emirhanarici.human_resources_project.model.JobSeeker;
import com.emirhanarici.human_resources_project.payload.request.UpdateJobSeekerRequest;
import com.emirhanarici.human_resources_project.payload.response.JobSeekerResponse;
import com.emirhanarici.human_resources_project.repository.JobSeekerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobSeekerService {

    private final JobSeekerMapper jobSeekerMapper;
    private final JobSeekerRepository jobSeekerRepository;

    public List<JobSeekerResponse> getAllJobSeekers() {
        List<JobSeeker> jobSeekers = jobSeekerRepository.findAll();

        return jobSeekers.stream()
                .map(jobSeekerMapper::mapToJobSeekerResponse)
                .toList();
    }

    public JobSeekerResponse getJobSeekerById(Long id) {
        JobSeeker jobSeeker = jobSeekerRepository.findById(id).orElseThrow(() -> new JobSeekerNotFoundException("JobSeeker not found with id: " + id));
        return jobSeekerMapper.mapToJobSeekerResponse(jobSeeker);
    }

    @Transactional
    public JobSeekerResponse updateJobSeeker(Long id, UpdateJobSeekerRequest request) {
        JobSeeker jobSeeker = jobSeekerRepository.findById(id)
                .orElseThrow(() -> new JobSeekerNotFoundException("JobSeeker not found with id: " + id));

        log.info("Before update: {}", jobSeeker);
        jobSeekerMapper.updateJobSeekerFromRequest(request, jobSeeker);
        log.info("After update: {}", jobSeeker);

        jobSeekerRepository.save(jobSeeker);

        return jobSeekerMapper.mapToJobSeekerResponse(jobSeeker);
    }

    public String deleteJobSeeker(Long id) {
        JobSeeker jobSeeker = jobSeekerRepository.findById(id).orElseThrow(() -> new JobSeekerNotFoundException("JobSeeker not found with id: " + id));
        jobSeekerRepository.delete(jobSeeker);
        return "JobSeeker deleted successfully with id: " + id;
    }

}
