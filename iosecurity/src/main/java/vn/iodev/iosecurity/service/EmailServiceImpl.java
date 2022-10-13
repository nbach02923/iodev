package vn.iodev.iosecurity.service;

import org.springframework.stereotype.Service;

import vn.iodev.iosecurity.mail.EmailDetails;
import vn.iodev.iosecurity.model.TaiKhoan;

import java.io.File;
import java.util.HashMap;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@Service
public class EmailServiceImpl implements EmailService {
    private static final String ACTIVE_USER_MAIL_TEMPLATE = "mail-active-user-template";
    @Autowired 
    private JavaMailSender javaMailSender;
 
    @Value("${spring.mail.username}") 
    private String sender;
 
    @Autowired
    ThymeleafService thymeleafService;
    
    public String sendSimpleMail(EmailDetails details)
    {
        try {
            SimpleMailMessage mailMessage
                = new SimpleMailMessage();
 
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());
 
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }
 
        catch (Exception e) {
            return "Error while Sending Mail";
        }
    }
 
    public String sendMailWithAttachment(EmailDetails details)
    {
        MimeMessage mimeMessage
            = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
 
        try {
            mimeMessageHelper
                = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setText(details.getMsgBody());
            mimeMessageHelper.setSubject(
                details.getSubject());
 
            FileSystemResource file
                = new FileSystemResource(
                    new File(details.getAttachment()));
 
            mimeMessageHelper.addAttachment(
                file.getFilename(), file);
 
            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";
        }
 
        catch (MessagingException e) {
            return "Error while sending mail!!!";
        }
    }

    @Override
    public String sendActiveUserHtmlMail(TaiKhoan taiKhoan) {
        try {
            SimpleMailMessage mailMessage
                = new SimpleMailMessage();
 
            mailMessage.setFrom(sender);
            mailMessage.setTo(taiKhoan.getEmail());
            HashMap<String, String> activeVariables = new HashMap<>();
            activeVariables.put("MaKichHoat", taiKhoan.getMaKichHoat());
            
            String msgBody = thymeleafService.getContent(ACTIVE_USER_MAIL_TEMPLATE, activeVariables);

            mailMessage.setText(msgBody);
            mailMessage.setSubject("Xin chào mừng đến với hệ thống IO");
 
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }
 
        catch (Exception e) {
            return "Error while Sending Mail";
        }
    }
}
