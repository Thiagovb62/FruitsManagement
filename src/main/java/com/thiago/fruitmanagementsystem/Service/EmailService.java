package com.thiago.fruitmanagementsystem.Service;

import com.thiago.fruitmanagementsystem.Model.EmailModelDTO;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class EmailService {
    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(EmailModelDTO  Dto) throws MessagingException {

        javaMailSender.send(mimeMessage -> {
            mimeMessage.setFrom(Dto.emailFrom());
            mimeMessage.setRecipients(Message.RecipientType.TO, Dto.emailTo());
            mimeMessage.setSubject(Dto.subject());
            mimeMessage.setText(Dto.message());
        });

    }

    public void sendEmailWithPdf(EmailModelDTO dto, byte[] pdfStream) throws MessagingException, IOException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(dto.emailFrom());
        helper.setTo(dto.emailTo());
        helper.setSubject(dto.subject());
        helper.setText(dto.message());

        InputStreamSource attachment = new ByteArrayResource(pdfStream);
        helper.addAttachment("document.pdf", attachment);

        javaMailSender.send(message);
    }
}

