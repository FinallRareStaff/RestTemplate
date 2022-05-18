package com.example.resttemplate;


import com.example.resttemplate.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class Communication {
    RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders httpHeaders;
    private final String url = "http://94.198.50.185:7081/api/users";
    private String resultStr;

    public String getRes(){
        return resultStr;
    }

    public Communication(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        httpHeaders = new HttpHeaders();
        resultStr = "";
    }

    public List<User> getAllUsers(){
        ResponseEntity<List<User>> response =
                restTemplate.exchange(url, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<User>>() {});

        HttpHeaders headers = response.getHeaders();
        String cookies = headers.get("set-cookie").get(0);
        int start = cookies.indexOf('=')+1;
        int end = cookies.indexOf(';');
        cookies = "JSESSIONID=" + cookies.substring(start, end);
        httpHeaders.add("Cookie", cookies);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        List<User> result = response.getBody();
        return result;
    }

}