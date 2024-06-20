package com.emirhanarici.human_resources_project.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateJobSeekerRequest {

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String address;
    private String mobilPhone;
    private String profilePicture;
}
