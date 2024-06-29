package com.emirhanarici.human_resources_project.mapper;

import com.emirhanarici.human_resources_project.model.Experience;
import com.emirhanarici.human_resources_project.payload.request.CreateExperienceRequest;
import com.emirhanarici.human_resources_project.payload.response.ExperienceResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExperienceMapper {


    Experience mapToExperience(CreateExperienceRequest request);
    @Mappings({
            @Mapping(source = "jobSeeker.id", target = "jobSeekerId"),
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "position", target = "position"),
            @Mapping(source = "companyName", target = "companyName"),
            @Mapping(source = "startDate", target = "startDate"),
            @Mapping(source = "endDate", target = "endDate"),
            @Mapping(source = "country", target = "country"),
            @Mapping(source = "city", target = "city"),
            @Mapping(source = "description", target = "description")
    })
    ExperienceResponse mapToExperienceResponse(Experience experience);

}
