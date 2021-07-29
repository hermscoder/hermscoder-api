package com.herms.hermscoder.model.dto;

import javax.validation.constraints.NotNull;

public class MailMessageDTO {
    @NotNull
    private String senderEmail;
    @NotNull
    private String senderName;
    @NotNull
    private String mailContent;

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getMailContent() {
        return mailContent;
    }

    public void setMailContent(String mailContent) {
        this.mailContent = mailContent;
    }
}
