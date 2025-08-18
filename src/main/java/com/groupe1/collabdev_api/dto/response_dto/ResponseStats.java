package com.groupe1.collabdev_api.dto.response_dto;

import com.groupe1.collabdev_api.dto.ProjectsCount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class ResponseStats {
    private long adminsCount;
    private UsersCount usersCount;

    public long getAdminsCount() {
        return adminsCount;
    }

    public ResponseStats() {
    }

    public void setAdminsCount(long adminsCount) {
        this.adminsCount = adminsCount;
    }

    public UsersCount getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(UsersCount usersCount) {
        this.usersCount = usersCount;
    }

    public long getIdeasCount() {
        return ideasCount;
    }

    public void setIdeasCount(long ideasCount) {
        this.ideasCount = ideasCount;
    }

    public long getBadgesCount() {
        return badgesCount;
    }

    public void setBadgesCount(long badgesCount) {
        this.badgesCount = badgesCount;
    }

    public ProjectsCount getProjectsCount() {
        return projectsCount;
    }

    public void setProjectsCount(ProjectsCount projectsCount) {
        this.projectsCount = projectsCount;
    }

    private long ideasCount;

    public ResponseStats(long adminsCount, UsersCount usersCount, long ideasCount, long badgesCount, ProjectsCount projectsCount) {
        this.adminsCount = adminsCount;
        this.usersCount = usersCount;
        this.ideasCount = ideasCount;
        this.badgesCount = badgesCount;
        this.projectsCount = projectsCount;
    }

    private long badgesCount;
    private ProjectsCount projectsCount;
}
