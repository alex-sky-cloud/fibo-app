package com.fibo.client.fiboclient.configuration;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.math.BigInteger;

@Configuration
public class JacksonObjectMapperConfiguration {

    @Autowired
    public void serializeBigDecimal(ObjectMapper objectMapper) {

        JsonFormat.Value formatValue =
                JsonFormat.Value.forShape(JsonFormat.Shape.STRING);

        objectMapper
                .configOverride(BigInteger.class)
                .setFormat(formatValue);
    }
}
