package com.zzz.demo.service.serviceimpl;

import com.zzz.demo.service.MailService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class MailServiceImpl implements MailService {


    @Resource
    private JavaMailSender javaMailSender;

    /**
     * 最简单的邮件发送
     *
     * @param from    发送方
     * @param to      接收方
     * @param cc      第三方(非必填)
     * @param subject 标题
     * @param content 内容
     */
    @Override
    public void sendSimpleMail(String from, String to, String cc, String subject, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        //simpleMailMessage.setCc(cc);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        javaMailSender.send(simpleMailMessage);
    }


    /**
     * 代附件的邮件
     * @param from
     * @param to
     * @param subject
     * @param content
     * @param file
     */
    @Override
    public void sendAttachFileMail(String from, String to, String subject, String content, File file) {
        try{
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content);
            mimeMessageHelper.addAttachment(file.getName(),file);
            javaMailSender.send(mimeMessage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 带图片的邮件
     * @param from
     * @param to
     * @param subject
     * @param content
     * @param srePAth
     * @param resIds
     */
    @Override
    public void sendMailWithImg(String from,String to,String subject,String content,String [] srePAth,String[]resIds){
        if (srePAth.length!=resIds.length){
            System.out.println("发送失败");
            return;
        }
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content,true);
            for (int i = 0; i < srePAth.length; i++) {
                FileSystemResource res = new FileSystemResource(new File(srePAth[i]));
                mimeMessageHelper.addInline(resIds[i],res);
            }
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
