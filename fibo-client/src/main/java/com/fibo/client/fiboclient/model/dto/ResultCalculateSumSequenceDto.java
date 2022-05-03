package com.fibo.client.fiboclient.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fibo.client.fiboclient.configuration.DeserializeResultCalculateSumSequence;
import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;

@Data
@Builder
@JsonDeserialize(using = DeserializeResultCalculateSumSequence.class)
public class ResultCalculateSumSequenceDto {

    private final BigInteger sumSequence;

    private final BigInteger [] sequenceRange;

    private final Boolean isCached;
}
