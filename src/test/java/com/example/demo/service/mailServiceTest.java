package com.example.demo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.example.demo.user.service.mailService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class mailServiceTest {
    private String destMail="1306512118@qq.com";
    
    @Autowired
    private mailService mailService;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void testSimpleMail() throws Exception {
        mailService.sendSimpleMail(destMail,"test simple mail"," hello this is simple mail");
    }

    @Test
    public void testHtmlMail() throws Exception {
        String content="<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail(destMail,"test simple mail",content);
    }

    @Test
    public void sendAttachmentsMail() throws Exception {
        String filePath="D:\\devops\\devops_2\\demo\\testresource\\attachmentMail.txt";
        mailService.sendAttachmentsMail(destMail, "主题：带附件的邮件", "有附件，请查收！", filePath);
    }


    @Test
    public void sendInlineResourceMail() throws Exception {
        String rscId = "neo006";
        String content="<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "D:\\devops\\devops_2\\demo\\testresource\\attachment.png";

        mailService.sendInlineResourceMail(destMail, "主题：这是有图片的邮件", content, imgPath, rscId);
    }

    @Test
    public void sendTemplateMail() throws Exception {
        //创建邮件正文
        Context context = new Context();
        context.setVariable("id", "006");
        String emailContent = templateEngine.process("emailTemplate", context);

        mailService.sendHtmlMail(destMail,"主题：这是模板邮件",emailContent);
    }
}
