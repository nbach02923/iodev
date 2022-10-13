package vn.iodev.iosecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.iodev.iosecurity.mail.EmailDetails;
import vn.iodev.iosecurity.service.EmailService;

@RestController
@RequestMapping("/api")
public class EmailController {
    @Autowired 
    private EmailService emailService;

    @PostMapping("/mail/sendMail")
    public String sendMail(@RequestBody EmailDetails details)
    {
        String status
            = emailService.sendSimpleMail(details);
 
        return status;
    }
 
    @PostMapping("/mail/sendMailWithAttachment")
    public String sendMailWithAttachment(
        @RequestBody EmailDetails details)
    {
        String status
            = emailService.sendMailWithAttachment(details);
 
        return status;
    }    
}
