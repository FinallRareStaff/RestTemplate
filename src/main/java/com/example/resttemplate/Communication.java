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

    @Autowired
    private RestTemplate restTemplate;

    private final String URL = "http://94.198.50.185:7081/api/users";
    private final HttpHeaders httpHeaders = new HttpHeaders();

    private String responseResult = "";

    public String getResponseResult() {
        return responseResult;
    }

    public List<User> getAllUsers(){
        ResponseEntity<List<User>> response =
                restTemplate.exchange(URL,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {});
        HttpHeaders headers = response.getHeaders();
        String cookie = headers.get("Set-cookie").get(0);
        cookie = "JSESSIONID=" + cookie.substring(cookie.indexOf("=")+1, cookie.indexOf(";"));
        httpHeaders.add("Cookie", cookie);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return response.getBody();
    }

    public void saveUser(User user) {
        HttpEntity<User> request = new HttpEntity<>(user, httpHeaders);
        ResponseEntity<String> response =
                restTemplate.exchange(URL,
                        HttpMethod.POST,
                        request,
                        String.class);
        responseResult += response.getBody();
    }

    public void updateUser(User user) {
        HttpEntity<User> request = new HttpEntity<>(user, httpHeaders);
        ResponseEntity<String> response =
                restTemplate.exchange(URL,
                        HttpMethod.PUT,
                        request,
                        String.class);
        responseResult += response.getBody();
    }

    public void deleteUser(byte id) {
        HttpEntity<User> request = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> response =
                restTemplate.exchange(URL + "/" + id,
                        HttpMethod.DELETE,
                        request,
                        String.class);
        responseResult += response.getBody();
    }


}