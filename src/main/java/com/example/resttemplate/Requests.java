package com.example.resttemplate;


import com.example.resttemplate.model.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class Requests {
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders httpHeaders = new HttpHeaders();
    private final String URL = "http://94.198.50.185:7081/api/users";

    public List<User> getAllUsers(){
        ResponseEntity<List<User>> response =
                restTemplate.exchange(URL,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {});
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return response.getBody();
    }

}