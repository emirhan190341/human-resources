package com.emirhanarici.human_resources_project.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JobResponse {

    private String code;
    private String position;
    private String workType;
    private String location;
    private String jobDescription;
    private List<String> todo;
    private List<String> requirements;
    private Boolean active;
    private LocalDate activationTime;
    private LocalDate offTime;
}
