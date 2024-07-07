package com.emirhanarici.human_resources_project.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplyJobResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String resume;
    private String coverLetter;
    private String applyDate;
    private String status;
}
