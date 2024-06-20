package com.emirhanarici.human_resources_project.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateJobRequest {

    private String position;
    private String workType;
    private String location;
    private String jobDescription;
    private List<String> todo;
    private List<String> requirements;
    private LocalDate activationTime;
    private LocalDate offTime;

}
