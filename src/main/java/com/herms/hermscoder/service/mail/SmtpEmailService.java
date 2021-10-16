package com.herms.hermscoder.service.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class SmtpEmailService extends AbstractEmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmtpEmailService.class);

    @Autowired
    private MailSender mailSender;
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmailMessage(SimpleMailMessage msg) {
        LOGGER.info("Sending email...");
        mailSender.send(msg);
        LOGGER.info("Email sent!");
    }

    @Override
    public void sendHtmlEmail(MimeMessage msg) {
        javaMailSender.send(msg);
    }
}
