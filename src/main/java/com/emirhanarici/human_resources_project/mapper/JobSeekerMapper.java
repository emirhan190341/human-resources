package com.emirhanarici.human_resources_project.mapper;

import com.emirhanarici.human_resources_project.model.JobSeeker;
import com.emirhanarici.human_resources_project.payload.request.UpdateJobSeekerRequest;
import com.emirhanarici.human_resources_project.payload.response.JobSeekerResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobSeekerMapper {


    @Mapping(target = "id", ignore = true)
    JobSeekerResponse mapToJobSeekerResponse(JobSeeker entity);
    @Mapping(target = "id", ignore = true)
    void updateJobSeekerFromRequest(UpdateJobSeekerRequest dto, @MappingTarget JobSeeker entity);
}
