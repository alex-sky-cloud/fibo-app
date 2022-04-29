package com.fibo.client.fiboclient.cache;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
@Component
public class CacheSumValues {

    private final Map<String, Integer> cache;

    public CacheSumValues() {
        cache = new ConcurrentHashMap<>();
    }
}
