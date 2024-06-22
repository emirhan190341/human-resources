package com.emirhanarici.human_resources_project.service;

import com.emirhanarici.human_resources_project.exception.JobSeekerNotFoundException;
import com.emirhanarici.human_resources_project.mapper.JobSeekerMapper;
import com.emirhanarici.human_resources_project.model.JobSeeker;
import com.emirhanarici.human_resources_project.payload.request.CreateJobSeekerRequest;
import com.emirhanarici.human_resources_project.payload.request.UpdateJobSeekerRequest;
import com.emirhanarici.human_resources_project.payload.response.JobSeekerResponse;
import com.emirhanarici.human_resources_project.repository.JobSeekerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobSeekerService {

    private final JobSeekerRepository jobSeekerRepository;

    public JobSeekerResponse createJobSeeker(CreateJobSeekerRequest request) {

        JobSeeker jobSeeker = JobSeekerMapper.mapToJobSeeker(request);

        jobSeekerRepository.save(jobSeeker);

        return JobSeekerMapper.mapToJobSeekerResponse(jobSeeker);
    }


    public List<JobSeekerResponse> getAllJobSeekers() {
        List<JobSeeker> jobSeekers = jobSeekerRepository.findAll();
        return jobSeekers.stream()
                .map(JobSeekerMapper::mapToJobSeekerResponse)
                .toList();
    }

    public JobSeekerResponse getJobSeekerById(Long id) {
        JobSeeker jobSeeker = jobSeekerRepository.findById(id).orElseThrow(() -> new JobSeekerNotFoundException("JobSeeker not found with id: " + id));
        return JobSeekerMapper.mapToJobSeekerResponse(jobSeeker);
    }

    public JobSeekerResponse updateJobSeeker(Long id, UpdateJobSeekerRequest request) {

        JobSeeker jobSeeker = jobSeekerRepository.findById(id).orElseThrow(() -> new JobSeekerNotFoundException("JobSeeker not found with id: " + id));

        jobSeeker.setFirstName(request.getFirstname());
        jobSeeker.setLastName(request.getLastname());
        jobSeeker.setPassword(request.getPassword());
        jobSeeker.setAddress(request.getAddress());
        jobSeeker.setMobilPhone(request.getMobilPhone());
        jobSeeker.setProfilePicture(request.getProfilePicture());

        jobSeekerRepository.save(jobSeeker);

        return JobSeekerMapper.mapToJobSeekerResponse(jobSeeker);

    }

    public String deleteJobSeeker(Long id) {
        JobSeeker jobSeeker = jobSeekerRepository.findById(id).orElseThrow(() -> new JobSeekerNotFoundException("JobSeeker not found with id: " + id));
        jobSeekerRepository.delete(jobSeeker);
        return "JobSeeker deleted successfully with id: " + id;
    }

}
