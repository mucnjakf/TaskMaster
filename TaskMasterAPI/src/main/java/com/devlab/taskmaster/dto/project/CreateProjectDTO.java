package com.devlab.taskmaster.dto.project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateProjectDTO {

    private String name;

    private String description;

    private Integer assigneeId;
}
