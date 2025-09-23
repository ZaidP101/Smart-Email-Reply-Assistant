package com.zaid.patel.AI_Email_Assistant.Controller;

import com.zaid.patel.AI_Email_Assistant.DTo.EmailReqDto;
import com.zaid.patel.AI_Email_Assistant.Service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
@CrossOrigin(origins = "*")
public class EmailGenController {

    private final EmailService emailService;

    public EmailGenController(EmailService emailService) {
        this.emailService = emailService;
    }
    @PostMapping("/generate")
    public ResponseEntity<String> generateEmail(@RequestBody EmailReqDto emailReqDto) {
        ResponseEntity<String> response = emailService.EmailGenerator(emailReqDto);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

}
