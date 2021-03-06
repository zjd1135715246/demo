package com.zzz.demo.controller;

import com.zzz.demo.service.MailService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@RequestMapping("/mail")
@Api(value = "邮件测试")
public class MailController {

    @Autowired
    private MailService mailService;

    @GetMapping("/sendMail")
    public void sendMail(){
        String from = "abc@qq.com";
        String to ="def@qq.com";
        String subject ="";
        String content="你xx";
        mailService.sendSimpleMail(from,to,"cc",subject,content);
    }

    @GetMapping("/sendAttachFileMail")
    public void sendAttachFileMail(){
        String from = "abc@qq.com";
        String to ="def@qq.com";
        String subject ="";
        String content="你xx";
        File file = new File("C:\\Users\\Admin\\Desktop\\ooo\\1.txt");
        mailService.sendAttachFileMail(from,to,subject,content,file);
    }

    @GetMapping("/sendMailWithImg")
    public void sendMailWithImg(){
        String from = "abc@qq.com";
        String to ="def@qq.com";
        String subject ="";
        String content="你xx";
        mailService.sendMailWithImg(from,to,subject,content,new String[]{"C:\\Users\\Admin\\Desktop\\ooo\\code1.jpg"},new String[]{"1.jpg"});
    }
}
