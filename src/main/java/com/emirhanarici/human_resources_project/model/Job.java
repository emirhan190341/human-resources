package com.emirhanarici.human_resources_project.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Job {

    @Id
    @UuidGenerator
    private String code;
    private String position;
    private String workType;
    private String location;
    @Column(length = 6555)
    private String jobDescription;
    @ElementCollection
    private List<String> todo;
    @ElementCollection
    private List<String> requirements;
    private boolean isActive;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate activationTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate offTime;

}
