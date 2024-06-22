package com.emirhanarici.human_resources_project.controller;

import com.emirhanarici.human_resources_project.payload.CustomResponse;
import com.emirhanarici.human_resources_project.payload.request.CreateJobRequest;
import com.emirhanarici.human_resources_project.payload.response.JobResponse;
import com.emirhanarici.human_resources_project.service.JobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/job")
@RequiredArgsConstructor
@Slf4j
public class JobController {

    private final JobService jobService;

    @PostMapping
    public CustomResponse<JobResponse> createJob(@RequestBody CreateJobRequest request) {
        log.info("JobController.createJob request: {}", request);
        return CustomResponse.created(jobService.createJob(request));
    }

    @GetMapping
    public CustomResponse<List<JobResponse>> getAllJobs() {
        log.info("JobController.getAllJobs");
        return CustomResponse.ok(jobService.getAllJobs());
    }

    @GetMapping("/{id}")
    public CustomResponse<JobResponse> getJobById(@PathVariable String id) {
        log.info("JobController.getJobById id: {}", id);
        return CustomResponse.ok(jobService.getJobById(id));
    }

    @PutMapping("/{id}")
    public CustomResponse<JobResponse> updateJob(@PathVariable String id, @RequestBody CreateJobRequest request) {
        log.info("JobController.updateJob id: {}, request: {}", id, request);
        return CustomResponse.ok(jobService.updateJob(id, request));
    }

    @DeleteMapping("/{id}")
    public CustomResponse<String> deleteJob(@PathVariable String id) {
        log.info("JobController.deleteJob id: {}", id);
        return CustomResponse.ok(jobService.deleteJob(id));
    }


}
