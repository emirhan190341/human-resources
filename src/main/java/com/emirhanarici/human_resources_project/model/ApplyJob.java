package com.emirhanarici.human_resources_project.model;

import com.emirhanarici.human_resources_project.model.enums.ApplyJobStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"job", "jobSeeker"})
public class ApplyJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String resume;
    private String coverLetter;
    private LocalDate applyDate;

    @Enumerated(EnumType.STRING)
    private ApplyJobStatus status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "job_seeker_id", nullable = false)
    private JobSeeker jobSeeker;

    @PrePersist
    public void prePersist() {
        this.applyDate = LocalDate.now();
        this.status = ApplyJobStatus.PENDING;
    }


}
