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

    private final JobRepository jobRepository;


    public JobResponse createJob(CreateJobRequest request) {

        Job job = jobRepository.save(JobMapper.mapToJob(request));
        System.out.println(job.getActive());
        return JobMapper.mapToJobResponse(job);

    }

    public List<JobResponse> getAllJobs() {
        List<Job> jobs = jobRepository.findAll();
        return jobs.stream()
                .map(JobMapper::mapToJobResponse)
                .toList();
    }

    public JobResponse getJobById(String id) {
        Job job = jobRepository.findById(id).orElseThrow(() -> new JobNotFoundException("Job not found with id: " + id));
        return JobMapper.mapToJobResponse(job);
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
        return JobMapper.mapToJobResponse(job);
    }

    public String deleteJob(String id) {
        Job job = jobRepository.findById(id).orElseThrow(() -> new JobNotFoundException("Job not found with id: " + id));
        jobRepository.delete(job);
        return "Job deleted successfully";
    }
}