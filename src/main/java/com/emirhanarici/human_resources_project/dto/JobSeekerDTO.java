package com.emirhanarici.human_resources_project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobSeekerDTO {
    private Long id;
    private String lastname;
    private String email;
    private String password;
    private String address;
    private String mobilPhone;
    private String nationalityId;
    private String birthYear;
    private String profilePicture;

}
