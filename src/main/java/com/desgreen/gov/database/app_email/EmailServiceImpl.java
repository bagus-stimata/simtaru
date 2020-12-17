package com.desgreen.gov.database.app_email;

import com.desgreen.gov.database.application.AppSetting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements EmailService{
  
    @Autowired
    public JavaMailSender javaMailSender;
 
    public void sendSimpleMessage(String to, String subject, String textMesage) {
      
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(AppSetting.EMAIL_FROM_DUMMY); //Dummy aslinya ada di setting: Tapi harus ada       
        // msg.setTo("bagus.stimata@gmail.com", "des.jatim1@gmail.com", "helpdesk1@des-green.com");
        msg.setTo(to);        
        msg.setSubject(AppSetting.APP_BRAND_NAME);
        
        // msg.setText("Hello World \n Spring Boot Email");
        msg.setText(textMesage);

        javaMailSender.send(msg);

        
    }



}