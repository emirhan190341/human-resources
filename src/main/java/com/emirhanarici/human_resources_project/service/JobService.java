package com.emirhanarici.human_resources_project.service;

import com.emirhanarici.human_resources_project.exception.JobNotFoundException;
import com.emirhanarici.human_resources_project.mapper.JobMapper;
import com.emirhanarici.human_resources_project.model.Job;
import com.emirhanarici.human_resources_project.payload.request.CreateJobRequest;
import com.emirhanarici.human_resources_project.payload.response.JobResponse;
import com.emirhanarici.human_resources_project.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobService {

    //TODO: How to show recent added job? Redis,pageable,sort, repository method?

    private final JobRepository jobRepository;
    private final JobMapper jobMapper;


    public JobResponse createJob(CreateJobRequest request) {

        Job job = jobRepository.save(jobMapper.mapToJob(request));
        System.out.println(job.getActive());
        return jobMapper.mapToJobResponse(job);

    }

    public List<JobResponse> getAllJobs() {
        List<Job> jobs = jobRepository.findAll();
        return jobs.stream()
                .map(jobMapper::mapToJobResponse)
                .toList();
    }

    public JobResponse getJobById(String id) {
        Job job = jobRepository.findById(id).orElseThrow(() -> new JobNotFoundException("Job not found with id: " + id));
        return jobMapper.mapToJobResponse(job);
    }

    public JobResponse updateJob(String id, CreateJobRequest request) {
        Job job = jobRepository.findById(id).orElseThrow(() -> new JobNotFoundException("Job not found with id: " + id));
        job.setPosition(request.getPosition());
        job.setWorkType(request.getWorkType());
        job.setLocation(request.getLocation());
        job.setJobDescription(request.getJobDescription());
        job.setTodo(request.getTodo());
        job.setRequirements(request.getRequirements());
        job.setActivationTime(request.getActivationTime());
        job.setOffTime(request.getOffTime());
        jobRepository.save(job);
        return jobMapper.mapToJobResponse(job);
    }

    public String deleteJob(String id) {
        Job job = jobRepository.findById(id).orElseThrow(() -> new JobNotFoundException("Job not found with id: " + id));
        jobRepository.delete(job);
        return "Job deleted successfully";
    }
}