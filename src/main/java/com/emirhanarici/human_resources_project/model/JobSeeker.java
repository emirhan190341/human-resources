package com.emirhanarici.human_resources_project.model;

import com.emirhanarici.human_resources_project.common.BaseEntity;
import com.emirhanarici.human_resources_project.model.role.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class JobSeeker extends BaseEntity  {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private String mobilPhone;
    private String nationalityId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthYear;
    private String profilePicture;
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "jobSeeker",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<JobSeekerExperience> jobSeekerExperiences = new ArrayList<>();


}
