package com.herms.hermscoder.service.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage;

@Service
public class MockEmailService extends AbstractEmailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MockEmailService.class);

    @Override
    public void sendEmailMessage(SimpleMailMessage msg) {
        LOGGER.info("Simulating HTML email sending...");
        LOGGER.info(msg.toString());
        LOGGER.info("Email sent!");
    }

    @Override
    public void sendHtmlEmail(MimeMessage msg) {
        LOGGER.info("Simulating HTML email sending...");
        LOGGER.info(msg.toString());
        LOGGER.info("Email sent!");
    }
}
