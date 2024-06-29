package com.emirhanarici.human_resources_project.mapper;

import com.emirhanarici.human_resources_project.model.Education;
import com.emirhanarici.human_resources_project.payload.request.CreateEducationRequest;
import com.emirhanarici.human_resources_project.payload.response.EducationResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EducationMapper {


    Education mapToEducation(CreateEducationRequest request);
    @Mappings({
            @Mapping(source = "jobSeeker.id", target = "jobSeekerId"),
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "schoolName", target = "schoolName"),
            @Mapping(source = "department", target = "department"),
            @Mapping(source = "startDate", target = "startDate"),
            @Mapping(source = "endDate", target = "endDate"),
            @Mapping(source = "country", target = "country"),
            @Mapping(source = "city", target = "city"),
            @Mapping(source = "description", target = "description")
    })
    EducationResponse mapToEducationResponse(Education education);

}
