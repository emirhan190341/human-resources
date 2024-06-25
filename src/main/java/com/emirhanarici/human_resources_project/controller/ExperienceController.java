package com.emirhanarici.human_resources_project.controller;

import com.emirhanarici.human_resources_project.payload.CustomResponse;
import com.emirhanarici.human_resources_project.payload.request.CreateJobSeekerExperienceRequest;
import com.emirhanarici.human_resources_project.payload.response.JobSeekerExperienceResponse;
import com.emirhanarici.human_resources_project.service.JobSeekerExperienceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/job-seeker-experience")
@RequiredArgsConstructor
@Slf4j
public class JobSeekerExperienceController {

    private final JobSeekerExperienceService jobSeekerExperienceService;

    @PostMapping("/{jobSeekerId}")
    public CustomResponse<JobSeekerExperienceResponse> createJobSeekerExperience(@RequestBody CreateJobSeekerExperienceRequest request,
                                                                                 @PathVariable Long jobSeekerId) {

        log.info("JobSeekerExperienceController.createJobSeekerExperience request: {}, jobSeekerId: {}", request, jobSeekerId);
        return CustomResponse.created(jobSeekerExperienceService.createJobSeekerExperience(request, jobSeekerId));
    }

}
