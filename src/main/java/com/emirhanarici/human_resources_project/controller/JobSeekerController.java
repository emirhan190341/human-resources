package com.emirhanarici.human_resources_project.controller;

import com.emirhanarici.human_resources_project.payload.CustomResponse;
import com.emirhanarici.human_resources_project.payload.request.CreateJobSeekerRequest;
import com.emirhanarici.human_resources_project.payload.response.JobSeekerResponse;
import com.emirhanarici.human_resources_project.service.JobSeekerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/job-seeker")
@RequiredArgsConstructor
@Slf4j
public class JobSeekerController {

    //todo: Docker Compose database,relationships,swagger,exception handling,tests,custom exception class

    private final JobSeekerService jobSeekerService;

    @PostMapping()
    public CustomResponse<JobSeekerResponse> createJobSeeker(@RequestBody CreateJobSeekerRequest request) {
        log.info("JobSeekerController.createJobSeeker request: {}", request);
        return CustomResponse.created(jobSeekerService.createJobSeeker(request));
    }

    @GetMapping()
    public CustomResponse<List<JobSeekerResponse>> getAllJobSeekers() {
        log.info("JobSeekerController.getAllJobSeekers");
        return CustomResponse.ok(jobSeekerService.getAllJobSeekers());
    }

    @GetMapping("/{id}")
    public CustomResponse<JobSeekerResponse> getJobSeekerById(@PathVariable Long id) {
        log.info("JobSeekerController.getJobSeekerById id: {}", id);
        return CustomResponse.ok(jobSeekerService.getJobSeekerById(id));
    }






}
