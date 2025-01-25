//package com.productservice.services;
//
//import com.productservice.dtos.UserResponseDto;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//@Service
//public class TokenService {
//
//    private RestTemplate restTemplate;
//
//    public TokenService(RestTemplate restTemplate){
//        this.restTemplate = restTemplate;
//    }
//
//    public boolean validateToken(String token){
//
//        UserResponseDto userResponseDto = restTemplate.getForObject(
//                "http://localhost:8080/user/validate/" + token, UserResponseDto.class
//        );
//        System.out.println("Ping.... " + userResponseDto);
//
//        return userResponseDto != null
//                && !userResponseDto.getEmail().isEmpty()
//                && !userResponseDto.getName().isEmpty();
//
//    }
//}
