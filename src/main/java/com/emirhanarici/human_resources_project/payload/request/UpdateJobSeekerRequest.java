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
public class UpdateJobSeekerRequest {

    private String firstName;
    private String lastName;
    private String address;
    private String country;
    private String city;
    private String mobilPhone;
    private String nationalityId;
    private LocalDate birthYear;
    private String profilePicture;
    private String position;
    private String github;
    private String linkedin;
    private String biography;
    private String website;
    private List<String> languages;
    private List<String> skills;
}
