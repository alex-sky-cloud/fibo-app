package com.fibo.client.fiboclient.service.impl;

import com.fibo.client.fiboclient.cache.CacheSumValues;
import com.fibo.client.fiboclient.service.FibonacciSumValues;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * This class describes calculating sum of values
 * from fibonacci sequence
 */
@Service
@RequiredArgsConstructor
public class FibonacciSumOfValuesImpl implements FibonacciSumValues {

    private final CacheSumValues cacheSumValues;

    @Override
    public Integer calculateRangeValuesFibonacciSequence(int startRange, int endRange) {

        Map<String, Integer> cache = cacheSumValues.getCache();
      //для кэша использовать неблокирующую очередь

        return null;
    }



}
