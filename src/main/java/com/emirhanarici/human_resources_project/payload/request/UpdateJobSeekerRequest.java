package com.emirhanarici.human_resources_project.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateJobSeekerRequest {

    private String firstName;
    private String lastName;
    private String address;
    private String mobilPhone;
    private String nationalityId;
    private LocalDate birthYear;
    private String profilePicture;
}
