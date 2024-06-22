package com.emirhanarici.human_resources_project.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobSeekerResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private String mobilPhone;
    private String nationalityId;
    private LocalDate birthYear;
    private String profilePicture;


}
