package com.groupe1.collabdev_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectsCount {
    private long inProgress;
    private long stoped;
    private long finished;
}
