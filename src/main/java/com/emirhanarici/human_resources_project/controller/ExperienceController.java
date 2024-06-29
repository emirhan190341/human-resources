package com.emirhanarici.human_resources_project.controller;

import com.emirhanarici.human_resources_project.payload.CustomResponse;
import com.emirhanarici.human_resources_project.payload.request.CreateExperienceRequest;
import com.emirhanarici.human_resources_project.payload.response.ExperienceResponse;
import com.emirhanarici.human_resources_project.service.ExperienceService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/job-seeker-experience")
@RequiredArgsConstructor
@Slf4j
public class ExperienceController {

    private final ExperienceService experienceService;

    @PostMapping("/{jobSeekerId}")
    public CustomResponse<ExperienceResponse> createJobSeekerExperience(@RequestBody CreateExperienceRequest request,
                                                                        @PathVariable Long jobSeekerId) {

        log.info("JobSeekerExperienceController.createJobSeekerExperience request: {}, jobSeekerId: {}", request, jobSeekerId);
        return CustomResponse.created(experienceService.createJobSeekerExperience(request, jobSeekerId));
    }

    @GetMapping("/{jobSeekerId}")
    public CustomResponse<List<ExperienceResponse>> getJobSeekerExperiencesByJobSeekerId(@PathVariable Long jobSeekerId) {
        log.info("JobSeekerExperienceController.getJobSeekerExperience jobSeekerId: {}", jobSeekerId);
        return CustomResponse.ok(experienceService.getJobSeekerExperiencesByJobSeekerId(jobSeekerId));
    }

}
