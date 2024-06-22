package com.emirhanarici.human_resources_project.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    @Builder.Default
    private Boolean active = true;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate activationTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate offTime;

}
