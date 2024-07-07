package com.emirhanarici.human_resources_project.controller;

import com.emirhanarici.human_resources_project.payload.CustomResponse;
import com.emirhanarici.human_resources_project.payload.request.CreateApplyJobRequest;
import com.emirhanarici.human_resources_project.payload.response.ApplyJobResponse;
import com.emirhanarici.human_resources_project.service.ApplyJobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/apply-job")
@RequiredArgsConstructor
@Slf4j
public class ApplyJobController {

    private final ApplyJobService applyJobService;

    @PostMapping("/{jobId}/apply/{jobSeekerId}")
    public CustomResponse<ApplyJobResponse> applyJob(@RequestBody CreateApplyJobRequest createApplyJobRequest, @PathVariable String jobId, @PathVariable Long jobSeekerId) {
        log.info("Apply Job Request: {}", createApplyJobRequest);
        return CustomResponse.ok(applyJobService.applyJob(createApplyJobRequest, jobId, jobSeekerId));
    }


}
