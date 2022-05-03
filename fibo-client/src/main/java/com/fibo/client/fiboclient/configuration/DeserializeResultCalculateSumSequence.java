package com.fibo.client.fiboclient.configuration;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fibo.client.fiboclient.model.dto.ResultCalculateSumSequenceDto;

import java.io.IOException;
import java.math.BigInteger;

public class DeserializeResultCalculateSumSequence
        extends StdDeserializer<ResultCalculateSumSequenceDto> {

    public DeserializeResultCalculateSumSequence() {
        this(null);
    }

    protected DeserializeResultCalculateSumSequence(Class<?> vc) {
        super(vc);
    }

    @Override
    public ResultCalculateSumSequenceDto deserialize(JsonParser jsonParser,
                                                     DeserializationContext deserializationContext)
            throws IOException, JacksonException {

        JsonNode node = jsonParser
                .getCodec()
                .readTree(jsonParser);

        BigInteger sumSequence = node
                .get("sumSequence")
                .bigIntegerValue();

        ObjectMapper mapper = new ObjectMapper();

        String sequenceRangeStr = node.get("sequenceRange").toString();
        BigInteger[] sequenceRange = mapper
                .readValue(sequenceRangeStr, BigInteger[].class);


        boolean isCached = node
                .get("isCached")
                .asBoolean();

        return ResultCalculateSumSequenceDto
                .builder()
                .sumSequence(sumSequence)
                .sequenceRange(sequenceRange)
                .isCached(isCached)
                .build();
    }
}
