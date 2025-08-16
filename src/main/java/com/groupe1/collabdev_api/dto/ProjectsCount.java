package com.groupe1.collabdev_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ProjectsCount {
    private long inProgress;

    public ProjectsCount() {
    }

    public long getInProgress() {
        return inProgress;
    }

    public void setInProgress(long inProgress) {
        this.inProgress = inProgress;
    }

    public long getStoped() {
        return stoped;
    }

    public void setStoped(long stoped) {
        this.stoped = stoped;
    }

    public long getFinished() {
        return finished;
    }

    public void setFinished(long finished) {
        this.finished = finished;
    }

    public ProjectsCount(long inProgress, long stoped, long finished) {
        this.inProgress = inProgress;
        this.stoped = stoped;
        this.finished = finished;
    }

    private long stoped;
    private long finished;
}
