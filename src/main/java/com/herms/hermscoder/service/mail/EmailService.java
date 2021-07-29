package com.herms.hermscoder.service.mail;


import com.herms.hermscoder.model.dto.MailMessageDTO;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public interface EmailService {

    static String PATH_TEMPLATE_EMAIL_MESSAGE = "emailMessage";
    static String SUBJECT_EMAIL_MESSAGE = "New message fom Hermscoder Blogfolio";
    void sendEmailMessage(SimpleMailMessage msg);


    void sendHtmlEmail(MimeMessage msg);

    void sendMessageHtmlEmail(MailMessageDTO mailMsg);
}
