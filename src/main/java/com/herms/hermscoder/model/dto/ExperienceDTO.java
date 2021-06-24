package com.herms.hermscoder.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.herms.hermscoder.model.entity.Experience;
import com.herms.hermscoder.utils.ConvertUtils;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

public class ExperienceDTO {


    private Long id;
    @NotNull
    private String jobTitle;
    @NotNull
    private String companyName;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private String startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private String endDate;
    @NotNull
    private String local;
    @NotNull
    @Lob
    private String description;

    public ExperienceDTO() {
    }

    public ExperienceDTO(Experience experience){
        this.id = experience.getId();
        this.jobTitle = experience.getJobTitle();
        this.companyName = experience.getCompanyName();
        this.startDate = ConvertUtils.localDateToString(experience.getStartDate());
        this.endDate = ConvertUtils.localDateToString(experience.getEndDate());
        this.local = experience.getLocal();
        this.description = experience.getDescription();
    }

    public Experience toExperience(){
        var experience = new Experience();
        experience.setId(this.id);
        experience.setJobTitle(this.jobTitle);
        experience.setCompanyName(this.companyName);
        experience.setStartDate(ConvertUtils.stringToLocalDate(this.startDate));
        experience.setEndDate(ConvertUtils.stringToLocalDate(this.endDate));
        experience.setLocal(this.local);
        experience.setDescription(this.description);

        return experience;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
