package com.fibo.client.fiboclient.configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;

@Configuration
public class RSocketConfiguration {

    @Value("${rsocket.server.host}")
    private String localhost;

    @Value("${rsocket.server.port}")
    private Integer serverPort;

    @Bean
    public RSocketRequester rSocketRequester(RSocketRequester.Builder builder) {

        return builder.tcp(this.localhost, this.serverPort);
    }
}
