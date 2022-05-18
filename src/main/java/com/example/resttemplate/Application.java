package com.example.resttemplate;

import com.example.resttemplate.config.AppConfig;
import com.example.resttemplate.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Communication communication = context.getBean(Communication.class);
        System.out.println(communication.getAllUsers());
    }

}
