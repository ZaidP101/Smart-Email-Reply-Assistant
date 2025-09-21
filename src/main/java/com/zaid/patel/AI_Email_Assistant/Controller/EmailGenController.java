package com.zaid.patel.AI_Email_Assistant.Controller;

import com.zaid.patel.AI_Email_Assistant.DTo.EmailReqDto;
import com.zaid.patel.AI_Email_Assistant.Service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailGenController {

    private final EmailService emailService;

    public EmailGenController(EmailService emailService) {
        this.emailService = emailService;
    }
    @PostMapping("/generate")
    public ResponseEntity<String> generateEmail(@RequestBody EmailReqDto emailReqDto){
        String response = String.valueOf(emailService.EmailGenerator(emailReqDto));
        return ResponseEntity.ok(response);
    }
}
