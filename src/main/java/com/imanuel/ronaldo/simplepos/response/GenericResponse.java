package com.imanuel.ronaldo.simplepos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class GenericResponse <T>{
    private T data;
    private String message;
    private String statusCode;
}
