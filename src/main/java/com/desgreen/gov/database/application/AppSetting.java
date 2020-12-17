package com.desgreen.gov.database.application;

import org.springframework.stereotype.Controller;

@Controller
public class AppSetting {
    public static final String APP_BRAND_NAME 	= "SIMTARU";
    // public static final String APP_IMAGE_PATH 	= "/yhawin/gambarnya/";
    public static final String APP_IMAGE_PATH= System.getProperty("user.home") + "/simtaru-documents/";


    public static final String EMAIL_FROM_DUMMY 	= "helpdesk1@des-green.com";

     
}