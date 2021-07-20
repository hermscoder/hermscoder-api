package com.herms.hermscoder.model.dto;

import com.herms.hermscoder.model.entity.Media;
import com.herms.hermscoder.model.entity.Profile;
import com.herms.hermscoder.model.entity.Project;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

public class ProjectDTO {

    @NotNull
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private MediaDTO thumbnail;

    @NotNull
    private String description;

    private String urlToProject;

    @NotNull
    private Long profileId;

    public ProjectDTO() {
    }

    public ProjectDTO(Project project) {
        this.id = project.getId();
        this.title = project.getTitle();
        this.thumbnail = project.getThumbnail() != null ? new MediaDTO(project.getThumbnail()) : null;
        this.description = project.getDescription();
        this.urlToProject = project.getUrlToProject();
        this.profileId = project.getProfile() != null ? project.getProfile().getId() : null;
    }

    public Project toProject(){
        var project = new Project();
        project.setId(this.id);
        project.setTitle(this.title);
        project.setThumbnail(this.thumbnail.toMedia());
        project.setDescription(this.description);
        project.setUrlToProject(this.urlToProject);
        project.setProfile(new Profile(this.profileId));
        return project;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MediaDTO getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(MediaDTO thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlToProject() {
        return urlToProject;
    }

    public void setUrlToProject(String urlToProject) {
        this.urlToProject = urlToProject;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }
}
