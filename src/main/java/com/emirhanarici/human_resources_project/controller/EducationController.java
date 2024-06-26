package com.emirhanarici.human_resources_project.controller;

import com.emirhanarici.human_resources_project.payload.CustomResponse;
import com.emirhanarici.human_resources_project.payload.request.CreateEducationRequest;
import com.emirhanarici.human_resources_project.payload.response.EducationResponse;
import com.emirhanarici.human_resources_project.service.EducationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/job-seeker-education")
@RequiredArgsConstructor
@Slf4j
public class EducationController {

    private final EducationService educationService;

    @PostMapping("/{jobSeekerId}")
    public CustomResponse<EducationResponse> createJobSeekerEducation(@RequestBody CreateEducationRequest request,
                                                                      @PathVariable Long jobSeekerId) {

        log.info("JobSeekerEducationController.createJobSeekerEducation request: {}, jobSeekerId: {}", request, jobSeekerId);
        return CustomResponse.created(educationService.createJobSeekerEducation(request, jobSeekerId));
    }

    @GetMapping("/{jobSeekerId}")
    public CustomResponse<List<EducationResponse>> getJobSeekerEducationsByJobSeekerId(@PathVariable Long jobSeekerId) {
        log.info("JobSeekerEducationController.getJobSeekerEducation jobSeekerId: {}", jobSeekerId);
        return CustomResponse.ok(educationService.getJobSeekerEducationsByJobSeekerId(jobSeekerId));
    }
}
