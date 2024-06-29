package com.emirhanarici.human_resources_project.model;

import com.emirhanarici.human_resources_project.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "jobSeeker")
public class Experience extends BaseEntity {

    private String position;
    private String companyName;
    private String startDate;
    private String endDate;
    private String country;
    private String city;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "job_seeker_id",nullable = false)
    private JobSeeker jobSeeker;


}
