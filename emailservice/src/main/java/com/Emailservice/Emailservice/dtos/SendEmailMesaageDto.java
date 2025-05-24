package com.Emailservice.Emailservice.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendEmailMesaageDto {

    private String from;
    private String to;
    private String subject;
    private String body;
}
