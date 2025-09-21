package com.zaid.patel.AI_Email_Assistant.DTo;

import lombok.Data;

@Data
public class EmailReqDto {
    private String emailContent;
    private String tone;
    public EmailReqDto(String emailContent, String tone) {
        this.emailContent = emailContent;
        this.tone = tone;
    }
    public String getTone() {
        return tone;
    }

    public void setTone(String tone) {
        this.tone = tone;
    }
    public String getEmailContent() {
        return emailContent;
    }
    public void setEmailContent(String emailContent) {
        this.emailContent = emailContent;
    }
}
