package com.emirhanarici.human_resources_project.model;

import com.emirhanarici.human_resources_project.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class JobSeeker extends BaseEntity {




    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String address;
    private String mobilPhone;
    private String nationalityId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthYear;
    private String profilePicture;




}
