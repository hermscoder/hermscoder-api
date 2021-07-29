package com.herms.hermscoder.resource;

import com.herms.hermscoder.model.dto.MailMessageDTO;
import com.herms.hermscoder.model.dto.UserRegistration;
import com.herms.hermscoder.service.mail.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/mail")
public class EmailMessageResource {

    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity sendMessageEmail(@RequestBody @Valid MailMessageDTO mailMessageDTO) {
        emailService.sendMessageHtmlEmail(mailMessageDTO);
        return ResponseEntity.ok().build();
    }
}
