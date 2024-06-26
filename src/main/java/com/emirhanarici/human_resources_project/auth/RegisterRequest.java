package com.emirhanarici.human_resources_project.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "Firstname is mandatory")
    private String firstname;
    @NotBlank(message = "Lastname is mandatory")
    private String lastname;
    @Email(message = "Email should be valid")
    private String email;
    @Size(min = 5, max = 50, message
            = "Password must be between 5 and 200 characters")
    private String password;
    @Size(min = 10, max = 50, message
            = "Password must be between 10 and 50 characters")
    private String address;
    @Size(min = 10, max = 15, message
            = "Phone must be between 10 and 50 characters")
    private String mobilPhone;
    @Size(min = 11, max = 11, message
            = "National Id must be 11 characters")
    private String nationalityId;
    @Past(message = "Birth year should be in the past")
    private LocalDate birthYear;
    private String profilePicture;
}
