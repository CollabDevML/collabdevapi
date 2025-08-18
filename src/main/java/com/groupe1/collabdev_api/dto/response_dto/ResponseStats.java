package com.groupe1.collabdev_api.dto.response_dto;

import com.groupe1.collabdev_api.dto.ProjectsCount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseStats {
    private long adminsCount;
    private UsersCount usersCount;
    private long ideasCount;
    private long badgesCount;
    private ProjectsCount projectsCount;
}
