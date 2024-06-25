package com.emirhanarici.human_resources_project.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateEducationRequest {

    private String schoolName;
    private String department;
    private String startDate;
    private String endDate;
    private String country;
    private String city;
    private String description;
}
