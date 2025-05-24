package com.Emailservice.Emailservice.consumers;

import com.Emailservice.Emailservice.dtos.SendEmailMesaageDto;
import com.Emailservice.Emailservice.utilities.EmailUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.kafka.annotation.KafkaListener;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class EmailConsumer {

    private ObjectMapper objectMapper;
    private EmailUtil emailUtil;

    public EmailConsumer(ObjectMapper objectMapper,
                             EmailUtil emailUtil) {
        this.objectMapper = objectMapper;
        this.emailUtil = emailUtil;
    }

    @KafkaListener(id = "emailServiceConsumerGroup", topics = "sendEmail")
    public void handleSendEmail(String massage) throws JsonProcessingException {
        System.out.println("got the send email message");
        SendEmailMesaageDto emailMessage = objectMapper.readValue(massage, SendEmailMesaageDto.class);

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("namanbatch@gmail.com", "");
            }
        };
        Session session = Session.getInstance(props, auth);

        emailUtil.sendEmail(
                session,
                emailMessage.getTo(),
                emailMessage.getSubject(),
                emailMessage.getBody()
        );
    }
}
