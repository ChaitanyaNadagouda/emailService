package com.Emailservice.Emailservice.consumers;

import com.Emailservice.Emailservice.AppConfig;
import com.Emailservice.Emailservice.dtos.SendEmailMesaageDto;
import com.Emailservice.Emailservice.utilities.EmailUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

@Service
public class EmailConsumer {

    private String smtpStarttlsEnabledKey = "mail.smtp.starttls.enable";
    private String smtpHostKey = "mail.smtp.host";
    private String smtpServer = "smtp.gmail.com";
    private String smtpPortKey = "mail.smtp.port";
    private String smtpAuthKey = "mail.smtp.auth";
    private String portNumber = "587";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private EmailUtil emailUtil;

    @KafkaListener(id = "emailServiceConsumerGroup", topics = "sendEmail")
    public void handleSendEmail(String massage) throws JsonProcessingException {

        System.out.println("got the send email message");
        System.out.println(appConfig.getUsername());
        SendEmailMesaageDto emailMessage = objectMapper.readValue(massage, SendEmailMesaageDto.class);

        Properties props = new Properties();
        props.put(smtpHostKey, smtpServer); //SMTP Host
        props.put(smtpPortKey, portNumber); //TLS Port
        props.put(smtpAuthKey, true); //enable authentication
        props.put(smtpStarttlsEnabledKey, true); //enable STARTTLS

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(appConfig.getUsername(), appConfig.getPassword());
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
