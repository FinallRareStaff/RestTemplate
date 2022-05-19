package com.example.resttemplate;

import com.example.resttemplate.configuration.Config;
import com.example.resttemplate.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Communication communication = context.getBean(Communication.class);
        System.out.println(communication.getAllUsers());
        User user = new User(3L, "James", "Brown", (byte) 22);
        User thomas = new User(3L, "Thomas", "Shelby", (byte) 22);
        communication.saveUser(user);
        communication.updateUser(thomas);
        communication.deleteUser((byte) 3);
    }

}
