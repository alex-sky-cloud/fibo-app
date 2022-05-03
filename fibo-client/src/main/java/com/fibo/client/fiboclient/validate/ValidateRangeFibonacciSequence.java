package com.fibo.client.fiboclient.validate;

import com.fibo.client.fiboclient.configuration.StandardFibonacciSequence;
import com.fibo.client.fiboclient.exception.OrderValuesInRangeException;
import com.fibo.client.fiboclient.exception.RangeWrongSequenceFibonacciException;
import com.fibo.client.fiboclient.exception.ValueRangeNegativeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ValidateRangeFibonacciSequence {

    private final StandardFibonacciSequence fibonacciSequenceStandard;

    public void validateManagerGivenRange(BigInteger startRange, BigInteger endRange) {

        validateValuesInGivenRangeExcludeNegative(startRange, endRange);

        validateOrderValuesInGivenRange(startRange, endRange);

        validateRangeForFibonacciSequence(startRange, endRange);
    }

    private void validateValuesInGivenRangeExcludeNegative(BigInteger startRange,
                                                           BigInteger endRange) {

        int markerNegative = -1;

        int sigNumStartRange = startRange.signum();
        int sigNumEndRange = endRange.signum();

        if (sigNumStartRange == markerNegative)
            throw new ValueRangeNegativeException("Value of given range cannot negative.");

        if (sigNumEndRange == markerNegative)
            throw new ValueRangeNegativeException("Value of given range cannot negative.");

    }

    private void validateOrderValuesInGivenRange(BigInteger startRange,
                                                 BigInteger endRange) {

        int markerIdentity = 0;
        if (startRange.compareTo(endRange) == markerIdentity) return;

        int markerGreaterMagnitude = 1;
        if (startRange.compareTo(endRange) == markerGreaterMagnitude)
            throw new OrderValuesInRangeException("The left part of the specified range cannot " +
                    "be larger than the right part.");
    }

    private void validateRangeForFibonacciSequence(BigInteger startRange, BigInteger endRange) {

        List<BigInteger> fibonacciSequenceBig = this.fibonacciSequenceStandard
                .generateBigFibonacciSequence();

        boolean isContainsStartRange = fibonacciSequenceBig.contains(startRange);
        boolean isContainsEndRange = fibonacciSequenceBig.contains(endRange);

        boolean isValidRange = isContainsStartRange && isContainsEndRange;

        if (!isValidRange) throw new RangeWrongSequenceFibonacciException("Wrong range.");
    }
}
