package com.zzz.demo.service;

import java.io.File;

public interface MailService {

     void sendSimpleMail(String from, String to, String cc, String subject, String content);

     void sendAttachFileMail(String from, String to, String subject, String content, File file);

     void sendMailWithImg(String from,String to,String subject,String content,String [] srePAth,String[]resIds);
}
