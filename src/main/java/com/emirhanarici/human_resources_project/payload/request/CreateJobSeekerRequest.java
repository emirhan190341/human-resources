package com.emirhanarici.human_resources_project.payload.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
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
public class CreateJobSeekerRequest {

    @NotBlank(message = "Firstname is mandatory")
    private String firstName;

    @NotBlank(message = "Lastname is mandatory")
    private String lastName;

    @Email(message = "Email should be valid")
    private String email;

    @Size(min = 5, max = 50, message = "Password must be between 5 and 50 characters")
    private String password;

    @Size(min = 10, max = 50, message = "Address must be between 10 and 50 characters")
    private String address;

    @NotBlank(message = "Country is mandatory")
    private String country;

    @NotBlank(message = "City is mandatory")
    private String city;

    @Size(min = 10, max = 15, message = "Phone must be between 10 and 15 characters")
    private String mobilPhone;

    @Size(min = 11, max = 11, message = "National Id must be 11 characters")
    private String nationalityId;

    @Past(message = "Birth year should be in the past")
    @JsonFormat(pattern = "yyyy-MM-dd")
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

