package com.emirhanarici.human_resources_project.auth;

import com.emirhanarici.human_resources_project.model.JobSeeker;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthenticationResponse {
    private JobSeeker jobSeeker;
    private String message;
    private Long id;
}