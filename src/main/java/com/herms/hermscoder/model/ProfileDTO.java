package com.herms.hermscoder.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.herms.hermscoder.utils.ConvertUtils;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProfileDTO {

    private Long id;
    @NotBlank
    private String givenName;
    @NotBlank
    private String familyName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private String birthDate;
    private String jobTitle;
    @Lob
    private String description;

    private boolean active;

    private List<ExperienceDTO> experiencesList;

    public ProfileDTO(){
        experiencesList = new ArrayList<>();
    }

    public ProfileDTO(Profile profile){
        this.id = profile.getId();
        this.givenName = profile.getGivenName();
        this.familyName = profile.getFamilyName();
        this.birthDate = ConvertUtils.localDateToString(profile.getBirthDate());
        this.jobTitle = profile.getJobTitle();
        this.description = profile.getDescription();
        this.active = "Y".equals(profile.getActive());
        this.experiencesList = profile.getExperiencesList().stream().map(ExperienceDTO::new).collect(Collectors.toList());
    }

    public Profile toProfile(){
        var profile = new Profile();
        profile.setId(this.id);
        profile.setGivenName(this.givenName);
        profile.setFamilyName(this.familyName);
        profile.setBirthDate(ConvertUtils.stringToLocalDate(this.birthDate));
        profile.setJobTitle(this.jobTitle);
        profile.setDescription(this.description);
        profile.setActive(this.active ? "Y" : "N");
        profile.setExperiencesList(this.experiencesList.stream().map(ExperienceDTO::toExperience).collect(Collectors.toList()));
        return profile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<ExperienceDTO> getExperiencesList() {
        return experiencesList;
    }

    public void setExperiencesList(List<ExperienceDTO> experiencesList) {
        this.experiencesList = experiencesList;
    }
}
