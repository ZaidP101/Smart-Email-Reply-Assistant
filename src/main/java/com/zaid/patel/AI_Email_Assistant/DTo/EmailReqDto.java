package com.zaid.patel.AI_Email_Assistant.DTo;

import lombok.Data;

@Data
public class EmailReqDto {
    private String emailContent;
    private String tone;
    private String rawReply;

    public EmailReqDto(String emailContent, String tone, String rawReply) {
        this.emailContent = emailContent;
        this.tone = tone;
        this.rawReply = rawReply;
    }
    public String getRawReply() {
        return rawReply;
    }
    public void setRawReply(String rawReply) {
        this.rawReply = rawReply;
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
