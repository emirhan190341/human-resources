package com.emirhanarici.human_resources_project.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateExperienceRequest {

    private String position;
    private String companyName;
    private String startDate;
    private String endDate;
    private String country;
    private String city;
    private String description;
    private Long jobSeekerId;
}
