package com.herms.hermscoder.service.mail;


import com.herms.hermscoder.exception.HermsCoderException;
import com.herms.hermscoder.model.dto.MailMessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public abstract class AbstractEmailService implements EmailService {
    @Value("${default.email.sender}")
    private String sender;
    @Value("${main.profile.user.email}")
    private String mainProfileEmail;
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMessageHtmlEmail(MailMessageDTO mailMsg) {
        SimpleMailMessage sm = prepareMessageEmail(mailMsg);
        MimeMessage mm = null;
        try {
            mm = prepareMimeMessage(mailMsg);
            sendHtmlEmail(mm);
        } catch (MessagingException e) {
            throw new HermsCoderException("Error occurred when sending message email.");
        }
    }

    protected SimpleMailMessage prepareMessageEmail(MailMessageDTO mailMsg) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(mainProfileEmail);
        sm.setFrom(sender);
        sm.setSubject(SUBJECT_EMAIL_MESSAGE);
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(htmlFromTemplateMessageEmail(mailMsg));
        return sm;
    }

    protected String htmlFromTemplateMessageEmail(MailMessageDTO mailMsg) {
        Context context = new Context();
        context.setVariable("mailMsg", mailMsg);
        return templateEngine.process(PATH_TEMPLATE_EMAIL_MESSAGE, context);
    }

    protected MimeMessage prepareMimeMessage(MailMessageDTO mailMsg) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo(mainProfileEmail);
        helper.setFrom(sender);
        helper.setSubject(SUBJECT_EMAIL_MESSAGE);
        helper.setSentDate(new Date(System.currentTimeMillis()));

        mimeMessage.setContent(htmlFromTemplateMessageEmail(mailMsg), "text/html");

        return mimeMessage;
    }


}
