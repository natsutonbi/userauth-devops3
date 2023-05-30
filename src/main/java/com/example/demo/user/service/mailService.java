package com.example.demo.user.service;

import org.springframework.stereotype.Service;

@Service
public interface mailService {
    void sendSimpleMail(String to, String subject, String content)throws Exception;

    void sendHtmlMail(String to, String subject, String content)throws Exception;

    void sendAttachmentsMail(String to, String subject, String content, String filePath)throws Exception;

    void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId)throws Exception;
}
