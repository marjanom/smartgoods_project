package com.example.smartgoods_project.rest.model;

import com.example.smartgoods_project.entity.models.Requirement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class ProjectProjectionDto {
    @NotBlank
    @NotNull
    Long id;
    @NotBlank
    @NotNull
    String projectName;
    @NotBlank
    @NotNull
    List<RequirementsProjectionDto> requirements;
}
