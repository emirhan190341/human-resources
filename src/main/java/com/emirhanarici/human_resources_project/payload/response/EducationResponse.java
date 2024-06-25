package com.emirhanarici.human_resources_project.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EducationResponse {

    private Long id;
    private String schoolName;
    private String department;
    private String startDate;
    private String endDate;
    private String country;
    private String city;
    private String description;
    private Long jobSeekerId;
}
