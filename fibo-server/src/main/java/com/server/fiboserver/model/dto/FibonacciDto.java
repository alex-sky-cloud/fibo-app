package com.server.fiboserver.model.dto;

import lombok.*;

import java.util.List;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FibonacciDto {

    List<Integer> fibonacciList;

}