package org.aviation.projects.flightcatering.service;

import feign.RetryableException;
import lombok.RequiredArgsConstructor;
import org.aviation.projects.flightcatering.feign.UserClient;
import org.jfree.util.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MailSenderService implements ISender {
    static Logger LOG = LoggerFactory.getLogger(MailSenderService.class);

    private final JavaMailSender mailSender;
    private final UserClient userClient;

    @Value("${spring.mail.username}")
    private String username;

    @Override
    public void sendForCaterers(String subject, String message, String token) {
        try {
            List<String> catererEmailsEmails = userClient.getCatererEmailsEmails(token);
            for (String email : catererEmailsEmails) {
                try {
                    Log.info("Sending email to " + email);
                    send(email, subject, message);
                    Log.info("Email sent to " + email);
                } catch (MailException exception) {
                    LOG.error("Error sending email", exception);
                }
            }
        } catch (RetryableException ex) {
            LOG.error("Error sending email", ex);
        }
    }

    @Override
    public void send(String emailTo, String subject, String message) {
        Log.info("Sending email to " + emailTo);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(username);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }

    @Override
    public void send(String emailTo, String subject, byte[] data, String name) {
        Log.info("Sending email to " + emailTo);
        MimeMessage mail = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, MimeMessageHelper.MULTIPART_MODE_MIXED,
                    StandardCharsets.UTF_8.name());
            helper.setFrom(username);
            helper.setTo(emailTo);
            helper.setSubject(subject);
            helper.setText("");
            helper.addAttachment(name, new ByteArrayResource(data));
            mailSender.send(mail);
        } catch (MessagingException e) {
            LOG.warn(e.getMessage());
        }
    }

}
