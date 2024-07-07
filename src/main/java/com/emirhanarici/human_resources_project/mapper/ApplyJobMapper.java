package com.emirhanarici.human_resources_project.mapper;

import com.emirhanarici.human_resources_project.model.ApplyJob;
import com.emirhanarici.human_resources_project.payload.request.CreateApplyJobRequest;
import com.emirhanarici.human_resources_project.payload.response.ApplyJobResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ApplyJobMapper {

    @Mapping(target = "id", ignore = true)
    ApplyJob mapToApplyJob(CreateApplyJobRequest request);

    ApplyJobResponse mapToApplyJobResponse(ApplyJob applyJob);

}
