package com.zaid.patel.AI_Email_Assistant.Service;

import com.zaid.patel.AI_Email_Assistant.DTo.EmailReqDto;
import org.springframework.http.ResponseEntity;

public interface EmailService {

    ResponseEntity<String> EmailGenerator(EmailReqDto emailReqDto);
}
