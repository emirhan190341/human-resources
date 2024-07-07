package com.emirhanarici.human_resources_project.dto;

import com.emirhanarici.human_resources_project.model.ApplyJob;
import com.emirhanarici.human_resources_project.model.Job;
import com.emirhanarici.human_resources_project.model.JobSeeker;
import com.emirhanarici.human_resources_project.payload.request.CreateApplyJobRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplyJobMessage {

    private Job job;
    private JobSeeker jobSeeker;
    private ApplyJob applyJob;
}
