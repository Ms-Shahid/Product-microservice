package com.productservice.configs;

import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.reactive.HttpComponentsClientHttpConnector;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.Configuration;
//import org.apache.http.impl.client.CloseableHttpClient;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplateBuilder().build();
    }

    /*
    * By default, RestTemplate may use SimpleClientHttpRequestFactory,
    *  which relies on HttpURLConnection that doesn't support PATCH.
    * You can configure RestTemplate to use Apache’s HttpClient, which does support PATCH
    * */

    @Bean
    public RestTemplate restTemplate(){
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        return new RestTemplate(factory);
    }
}
