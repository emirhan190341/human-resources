package com.emirhanarici.human_resources_project.model;

import com.emirhanarici.human_resources_project.common.BaseEntity;
import com.emirhanarici.human_resources_project.model.role.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString(exclude = {"experiences", "educations"})
public class JobSeeker extends BaseEntity {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private String country;
    private String city;
    private String mobilPhone;
    private String nationalityId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthYear;
    private String profilePicture;
    private String position;
    private String github;
    private String linkedin;
    private String biography;
    private String website;
    @ElementCollection
    private List<String> languages = new ArrayList<>();
    @ElementCollection
    private List<String> skills = new ArrayList<>();
    private boolean active;
    private boolean accountVerified;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Experience> experiences;

    @OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Education> educations;




}
