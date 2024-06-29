package com.emirhanarici.human_resources_project.mapper;

import com.emirhanarici.human_resources_project.model.Job;
import com.emirhanarici.human_resources_project.payload.request.CreateJobRequest;
import com.emirhanarici.human_resources_project.payload.response.JobResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobMapper {


    @Mapping(target = "code", ignore = true)
    Job mapToJob(CreateJobRequest request);
    JobResponse mapToJobResponse(Job job);

}
