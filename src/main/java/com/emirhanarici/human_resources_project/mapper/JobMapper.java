package com.emirhanarici.human_resources_project.mapper;

import com.emirhanarici.human_resources_project.model.Job;
import com.emirhanarici.human_resources_project.payload.request.CreateJobRequest;
import com.emirhanarici.human_resources_project.payload.response.JobResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JobMapper {

    public Job mapToJob(CreateJobRequest createJobRequest) {
        return Job.builder()
                .position(createJobRequest.getPosition())
                .workType(createJobRequest.getWorkType())
                .location(createJobRequest.getLocation())
                .jobDescription(createJobRequest.getJobDescription())
                .todo(createJobRequest.getTodo())
                .requirements(createJobRequest.getRequirements())
                .activationTime(createJobRequest.getActivationTime())
                .offTime(createJobRequest.getOffTime())
                .build();
    }

    public JobResponse mapToJobResponse(Job job) {
        return JobResponse.builder()
                .code(job.getCode())
                .position(job.getPosition())
                .workType(job.getWorkType())
                .location(job.getLocation())
                .jobDescription(job.getJobDescription())
                .todo(job.getTodo())
                .requirements(job.getRequirements())
                .active(job.getActive())
                .activationTime(job.getActivationTime())
                .offTime(job.getOffTime())
                .build();
    }

}