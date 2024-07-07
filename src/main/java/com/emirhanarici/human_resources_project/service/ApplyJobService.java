package com.emirhanarici.human_resources_project.service;

import com.emirhanarici.human_resources_project.dto.ApplyJobMessage;
import com.emirhanarici.human_resources_project.mapper.ApplyJobMapper;
import com.emirhanarici.human_resources_project.model.ApplyJob;
import com.emirhanarici.human_resources_project.model.Job;
import com.emirhanarici.human_resources_project.model.JobSeeker;
import com.emirhanarici.human_resources_project.payload.request.CreateApplyJobRequest;
import com.emirhanarici.human_resources_project.payload.response.ApplyJobResponse;
import com.emirhanarici.human_resources_project.repository.ApplyJobRepository;
import com.emirhanarici.human_resources_project.repository.JobRepository;
import com.emirhanarici.human_resources_project.repository.JobSeekerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplyJobService {

    private final ApplyJobRepository applyJobRepository;
    private final ApplyJobMapper applyJobMapper;
    private final JobRepository jobRepository;
    private final JobSeekerRepository jobSeekerRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${spring.kafka.topic.apply-job}")
    private String applyJobTopic;

    public ApplyJobResponse applyJob(CreateApplyJobRequest createApplyJobRequest, String jobId, Long jobSeekerId) {

        Job job = jobRepository.findById(jobId).orElseThrow(() -> new RuntimeException("Job not found"));
        JobSeeker jobSeeker = jobSeekerRepository.findById(jobSeekerId).orElseThrow(() -> new RuntimeException("Job Seeker not found"));

        log.info("Before Apply Job Request: {}", createApplyJobRequest);

        ApplyJob applyJob = applyJobMapper.mapToApplyJob(createApplyJobRequest);

        log.info("After Apply Job Request: {}", applyJob);

        applyJob.setJob(job);
        applyJob.setJobSeeker(jobSeeker);

        applyJob = applyJobRepository.save(applyJob);

        ApplyJobMessage applyJobMessage = ApplyJobMessage.builder()
                .job(job)
                .jobSeeker(jobSeeker)
                .applyJob(applyJob)
                .build();

//        kafkaTemplate.send(applyJobTopic, applyJobMessage);


        return applyJobMapper.mapToApplyJobResponse(applyJob);

    }
}
